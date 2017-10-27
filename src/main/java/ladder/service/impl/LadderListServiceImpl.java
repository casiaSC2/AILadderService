package ladder.service.impl;

import ladder.dao.AccountMapper;
import ladder.dao.StatisticalListMapper;
import ladder.dao.model.Account;
import ladder.dao.model.AccountExample;
import ladder.dao.model.StatisticalList;
import ladder.dao.model.StatisticalListExample;
import ladder.service.LadderListService;
import ladder.vos.LadderList;
import ladder.vos.LadderSingleInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjian on 17-10-28.
 */
@Service
public class LadderListServiceImpl implements LadderListService {
    @Resource
    StatisticalListMapper statisticalListMapper;
    @Resource
    AccountMapper accountMapper;

    @Override
    public LadderList getLadderList(Integer season) throws Exception{
        LadderList result = new LadderList();
        StatisticalListExample statisticalListExample = new StatisticalListExample();
        StatisticalListExample.Criteria statisticalCriteria = statisticalListExample.createCriteria();
        statisticalCriteria.andSeasonEqualTo(season);
        List<StatisticalList> statisticalLists = statisticalListMapper.selectByExample(statisticalListExample);

        List<LadderSingleInfo> ladderSingleInfoList = new ArrayList<LadderSingleInfo>();
        for(StatisticalList statisticalList : statisticalLists){
            LadderSingleInfo ladderSingleInfo = new LadderSingleInfo();
            Account account = accountMapper.selectByPrimaryKey(statisticalList.getUsername());
            ladderSingleInfo.setBotName(statisticalList.getBotName());
            ladderSingleInfo.setMatches(statisticalList.getMatches());
            ladderSingleInfo.setRace(account.getRace());
            ladderSingleInfo.setUserName(statisticalList.getUsername());
            ladderSingleInfo.setWinRate(statisticalList.getWinRate());
            ladderSingleInfo.setWins(statisticalList.getWins());
            ladderSingleInfoList.add(ladderSingleInfo);
        }
        result.setLadderSingleInfos(ladderSingleInfoList);
        return result;
    }


}
