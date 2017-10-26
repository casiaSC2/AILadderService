package ladder.commons;

/**
 * Created by wangjian on 17-10-27.
 */
public class ErrorJson extends BaseResponse {
    private int err_no;
    private String message;

    public ErrorJson(int err_no, String message) {
        this.err_no = err_no;
        this.message = message;
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
