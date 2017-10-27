package ladder.vos;

import ladder.commons.BaseResponse;

import java.util.List;

/**
 * Created by wangjian on 17-10-28.
 */
public class LadderList extends BaseResponse {
    private List<LadderSingleInfo> ladderSingleInfos;

    public List<LadderSingleInfo> getLadderSingleInfos() {
        return ladderSingleInfos;
    }

    public void setLadderSingleInfos(List<LadderSingleInfo> ladderSingleInfos) {
        this.ladderSingleInfos = ladderSingleInfos;
    }
}
