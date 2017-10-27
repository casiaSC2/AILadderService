package ladder.service;

import ladder.vos.LadderList;
import ladder.vos.LadderSingleInfo;
import org.springframework.stereotype.Service;

/**
 * Created by wangjian on 17-10-28.
 */
@Service
public interface LadderListService {
    public LadderList getLadderList(Integer season) throws Exception;
}
