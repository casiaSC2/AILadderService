package ladder.service.impl;

import ladder.dao.AccountMapper;
import ladder.dao.MatchResultMapper;
import ladder.dao.model.Account;
import ladder.dao.model.MatchResult;
import ladder.dao.model.MatchResultExample;
import ladder.service.MatchResultService;
import ladder.utils.EnumUtils;
import ladder.vos.Match;
import ladder.vos.MatchList;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangjian on 17-10-30.
 */
@Service
public class MatchResultServiceImpl implements MatchResultService {
    @Resource
    private MatchResultMapper matchResultMapper;
    @Resource
    private AccountMapper accountMapper;
    @Override
    public MatchList getMatches(String username) throws Exception{
        MatchList matchList = new MatchList();
        List<Match> matches = new ArrayList<Match>();
        MatchResultExample matchResultExample = new MatchResultExample();
        matchResultExample.or().andUsernameAEqualTo(username);
        matchResultExample.or().andUsernameBEqualTo(username);
        List<MatchResult> matchResultList = matchResultMapper.selectByExample(matchResultExample);
        for(MatchResult matchResult: matchResultList){
            Match match = new Match();
            match.setMap(matchResult.getMap());
            String A = matchResult.getUsernameA();
            String B = matchResult.getUsernameB();
            Integer win = matchResult.getWin();
            String result = "";
            if((A.equals(username) && win == 1 )||(B.equals(username) && win == -1)){
                result = "Win";
            }else if(win == 0){
                result = "Draw";
            }else{
                result = "lose";
            }
            String opponent = A.equals(username)? B : A;
            Account account = accountMapper.selectByPrimaryKey(opponent);
            match.setOpponent_bot(account.getBotName());
            match.setRace(EnumUtils.getRace(account.getRace()));
            match.setTime(dateToString(matchResult.getTime()));
            match.setId(matchResult.getId());
            match.setPath(matchResult.getReplayPath());
            match.setResult(result);
            matches.add(match);
        }
        matchList.setMatches(matches);
        return matchList;
    }
    @NotNull
    public static String dateToString(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentTime);
    }
}
