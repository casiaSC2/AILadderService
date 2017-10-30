package ladder.vos;

import ladder.commons.BaseResponse;

import java.util.List;

/**
 * Created by wangjian on 17-10-28.
 */
public class MatchList extends BaseResponse{
    private List<Match> matches;

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
