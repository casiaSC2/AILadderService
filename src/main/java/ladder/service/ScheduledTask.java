package ladder.service;

import ladder.commons.Sc2Exception;
import ladder.dao.AccountMapper;
import ladder.dao.MatchPoolMapper;
import ladder.dao.model.Account;
import ladder.dao.model.MatchPool;
import ladder.dao.model.MatchPoolExample;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * Created by wangjian on 17-10-30.
 */
@Configuration
@EnableScheduling
public class ScheduledTask {
    @Resource
    private MatchPoolMapper matchPoolMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private MatchService matchService;
    @Value("${map.path}")
    private String mapPath;
    @Value("${sc2.path}")
    private String sc2Path;
    @Scheduled(cron = "0/10 * * * * ? ")
    public void match() throws Exception {
        MatchPoolExample matchPoolExample = new MatchPoolExample();
        matchPoolExample.createCriteria();
        List<MatchPool> matchPoolList = matchPoolMapper.selectByExample(matchPoolExample);
        if(matchPoolList.size() < 2){
            throw new Sc2Exception("Not Enough Bots", 23);
        }
        Collections.shuffle(matchPoolList);
        MatchPool A = matchPoolList.get(0);
        MatchPool B = matchPoolList.get(1);
        if(A.getInMatch() || B.getInMatch()){
            return;
        }
        String usernameA = A.getUsername();
        String usernameB = B.getUsername();
        Account accountA = accountMapper.selectByPrimaryKey(usernameA);
        Account accountB = accountMapper.selectByPrimaryKey(usernameB);
        matchService.match(sc2Path, accountA.getBotPath(), accountB.getBotPath(), mapPath);
    }

}
