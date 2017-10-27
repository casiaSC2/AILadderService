package laddertest;

import ladder.service.impl.AccountServiceImpl;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wangjian on 17-10-27.
 */
public class TestMD5 {
    @Test
    public void test() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String str = "123";
        String result = AccountServiceImpl.encoderByMd5(str);
        System.out.println(result);
        System.out.println(result.length());
    }

}
