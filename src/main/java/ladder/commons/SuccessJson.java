package ladder.commons;

/**
 * Created by wangjian on 17-10-27.
 */
public class SuccessJson extends BaseResponse{
    private int success;

    public SuccessJson() {
        this.success = 1;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
