# AILadderService
The StarCraft2 AI ladder service

### 页面设计

参照 <a href="http://sc2ai.net">http://sc2ai.net</a>

分为
1. Home
1. Ladder Seasons
1. Learning
1. Information

等几个页面，除了Ladder Seasons以外全部为静态页面

### 服务流程设计

-----------

#### 新建账号

1. 服务层接受到前端的新建账号请求
1. 服务层对新建账号请求参数进行验证
1. 将新账号信息存储于数据库中
1. 在文件系统中新建账号名对应的文件夹，用于放置bot文件
1. 服务层将bot文件存放到相应的文件夹中，将bot的用户信息，路径信息，提交时间等存放到数据库中
1. 将bot加入比赛池

#### 更新bot

1. 服务层接受到上传bot的请求
1. 服务层读取session信息，进行验证
1. 服务层将bot文件存放到相应的文件夹中，覆盖之前的bot，将相应信息存放到数据库中

#### 比赛
1. 启动一个循环线程，不断从比赛池中随机取出两个bot，进行比赛
1. 比赛完成之后，将和录像地址信息存入数据库，将比赛录像存入比赛录像文件夹，起名为A_B_时间戳
1. 更新比赛结果信息，计算胜率和天梯得分

#### 天梯页面
1. 服务层接受到获取天梯信息的请求
1. 获取所有用户数据，并打包返回

#### 获取单个用户比赛结果页面
1. 服务层接受到获取replay的post请求
1. 根据post中的用户名，去往比赛录像数据表中搜索对应用户名的所有比赛记录，包括对手名称，对战种族，地图和比赛结果，并打包返回

#### 获取replay接口
1. 服务层接受到获取replay的请求
1. 根据比赛ID信息，去往比赛录像数据表中获取对应比赛ID的replay储存地址
1. 服务层读取储存地址的文件，以文件的形式Response

### 接口详细设计

#### 失败返回
接口调用失败统一返回如下json，其中包括如下对象：

|名称|类型|是否必须|描述|
|---|---|---|----|
|err_no|int|是|错误的id|
|message|string|是|错误信息|

例子：
```json
{
    "err_no":1,
    "message":"Cannot find the replay file."
}
```
#### 新建账号
方法：post

地址：/sign_up

content-type：form-data

参数：

|名称|类型|是否必须|描述|
|---|---|---|----|
|email|string|是|用户的Email，在服务层对其进行格式校验|
|username|string|是|用户名|
|password|string|是|用户的密码|
|botName|string|是|用户提交bot的名字|
|botType|int|是|bot类型，考虑支持c++和python，暂时只支持c++，0：c++，1：python|
|race|int|是|bot种族，0T1P2Z|
|description|string|否|对bot的描述|
|bot|multipartFile|是|bot文件（json文件）现在暂时支持通过commandcenter读取配置文件对战，未来考虑使用dll或so文件|

例子：

    "email":"123@126.com",
    "username":"bcd",
    "password":"123",
    "botName":"abc",
    "botType":0,
    "race":1,
    "description":"hahaha",
    "bot":multipart file

返回值：

|名称|类型|是否必须|描述|
|---|---|---|----|
|success|int|是|值固定为0|



#### 更新bot

方法：post

地址：/update

content-type：form-data

参数：
|名称|类型|是否必须|描述|
|---|---|---|----|
|username|string|是|用户名|
|password|string|是|用户密码|
|bot|multipartFile|是|用户bot文件|

返回值：

|名称|类型|是否必须|描述|
|---|---|---|----|
|success|int|是|值固定为0|

#### 天梯页面

方法：get

地址：/ladder

参数：
|名称|类型|是否必须|描述|
|---|---|---|----|
|season|int|是|赛季|

例子：
```html
sc2.com/index?season=1
```

返回值：
|名称|类型|是否必须|描述|
|---|---|---|----|
|botName|string|是|bot的名字|
|username|string|是|用户名|
|race|int|是|种族|
|matches|int|是|比赛场次|
|wins|int|是|胜利次数|
|winRate|float|是|胜率|

例子：
[
    {
        "botName":"abc",
        "username":"bcd",
        "race":"Protoss",
        "matches":1,
        "wins":0,
        "winRate":0.0
    }
]


#### 获取单个用户比赛结果页面
方法：post

地址：/view_result

类型：application/json

参数：

|名称|类型|是否必须|描述|
|---|---|---|----|
|season|int|yes|season|
|username|string|是|用户名|

返回值：
|名称|类型|是否必须|描述|
|---|---|---|----|
|time|string|是|比赛时间|
|opponentBot|string|是|对手名称|
|race|string|是|对手种族|
|Map|string|是|比赛地图|
|result|string|是|比赛结果|

#### 获取replay
方法：post

地址：/get_replay

类型：application/json

|名称|类型|是否必须|描述|
|---|---|---|----|
|username|string|是|用户名|
|replayId|int|是|replay的Id|

返回：multipart file

### 数据库设计

#### 账号信息表
|名称|类型|是否必须|描述|
|---|---|---|---|
|id|int|是|用户id，auto increment|
|email|char(100)|是|用户email|
|username|char(30)|yes|user name|
|password|char(50)|yes|password.md5 + salt|
|bot_name|char(30)|yes|bot name|
|bot_type|int|yes|bot type|
|update_time|Date|yes|update bot的时间|
|race|int|yes|race|
|description|varchar|no|description|
|bot_path|char(50)|yes|file path of bot|

#### 比赛队列
|名称|类型|是否必须|描述|
|---|---|---|---|
|username|char(30)|yes|username, pk|
|bot_name|char(50)|yes|bot name|
|bot_path|char(50)|yes|bot path|

#### 比赛结果
|名称|类型|是否必须|描述|
|---|---|---|---|
|id|int|yes|auto increment|
|username_A|
|username_B|
|bot_name_A|char(50)|yes|其中一人的bot的名字|
|bot_name_B|char(50)|yes|另外一人的bot的名字|
|win|int|yes|0：平局，1：A胜利，-1：B胜利|
|replay_path|char(50)|yes|录像的保存路径，录像名字：A bot名+B bot名+时间戳|
 
#### 赛季记录表
|名称|类型|是否必须|描述|
|---|---|---|---|
|season|int|yes|第几赛季|
|username|string|yes|username|
|bot_name|char(50)|yes|bot的名字|
|matches|int|yes|比赛场数目|
|wins|int|yes|胜利数|
|win_rate|float|yes|胜率|

