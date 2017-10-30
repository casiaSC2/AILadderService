package ladder.vos;

import ladder.commons.BaseResponse;

import java.util.List;

/**
 * Created by wangjian on 17-10-28.
 */
public class LadderList extends BaseResponse {
    private List<LadderSingleInfo> infos;

    public List<LadderSingleInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<LadderSingleInfo> infos) {
        this.infos = infos;
    }
}
