package ladder.commons;

/**
 * Created by wangjian on 17-10-27.
 */
public class SuccessJson extends BaseResponse{
    private int err_no;
    private String message;

    public SuccessJson() {
        this.err_no = 0;
        this.message = "success";
    }

    public int getErr_no() {
        return err_no;
    }

    public void setErr_no(int err_no) {
        this.err_no = err_no;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
