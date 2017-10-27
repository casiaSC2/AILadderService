package ladder.commons;

/**
 * Created by wangjian on 17-10-27.
 */
public class Sc2Exception extends Exception {
    private int err_no;

    public Sc2Exception(String s, int err_no) {
        super(s);
        this.err_no = err_no;
    }

    public int getErr_no() {
        return err_no;
    }

    public void setErr_no(int err_no) {
        this.err_no = err_no;
    }
}
