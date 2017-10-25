package ladder.controllers;


import ladder.vos.TestVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangjian on 17-10-24.
 */
@RestController
public class Controller {
    @RequestMapping("/test")
    @ResponseBody
    TestVO test(){
        TestVO testVO = new TestVO();
        testVO.setI(1);
        testVO.setS("hello");
        return testVO;
    }
}
