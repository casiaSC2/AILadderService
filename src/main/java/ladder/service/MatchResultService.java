package ladder.service;

import ladder.vos.MatchList;
import org.springframework.stereotype.Service;

/**
 * Created by wangjian on 17-10-28.
 */
@Service
public interface MatchResultService {
    MatchList getMatches(String username) throws Exception;
}
