package ladder.dao;

import ladder.dao.model.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by yt476 on 2017/12/2.
 */
@Mapper
public interface InsertAccountMapper {
    @Insert("insert into account (username, email, password, bot_name, bot_type, race, description, bot_path, config_path, update_time) values (#{username}," +
            "#{email}," +
            "#{password}," +
            "#{bot_name}," +
            "#{bot_type}," +
            "#{race}," +
            "#{description}," +
            "#{bot_path}," +
            "#{config_path}," +
            "#{update_time});")
    void insertAccount(@Param("username") String username,
                       @Param("email") String email,
                       @Param("password") String password,
                       @Param("bot_name") String botName,
                       @Param("bot_type") Integer botType,
                       @Param("race") Integer race,
                       @Param("description") String description,
                       @Param("bot_path")String botPath,
                       @Param("config_path")String config_path,
                       @Param("update_time")Date updateTime);
}
