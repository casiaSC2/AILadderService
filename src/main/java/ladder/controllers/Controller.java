package ladder.controllers;


import ladder.commons.BaseResponse;
import ladder.commons.ErrorJson;
import ladder.commons.Sc2Exception;
import ladder.commons.SuccessJson;
import ladder.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by wangjian on 17-10-24.
 */
@RestController
public class Controller {
    private static Logger logger = LoggerFactory.getLogger(Controller.class);
    @Resource
    private AccountService accountService;

    @RequestMapping(value = "ladder", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse ladder(@RequestParam(name = "season") Integer season){
        return new SuccessJson();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestParam(name = "username") String username,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name = "bot") MultipartFile bot) {
        try {
            boolean result = accountService.updateBot(username, password, bot);
            if (result) {
                return new SuccessJson();
            } else {
                return new ErrorJson(21, "Invalid username or password");
            }
        } catch (Sc2Exception e) {
            logger.error(e.getMessage(), e);
            return new ErrorJson(e.getErr_no(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ErrorJson(0, "Unknown error");
        }
    }

    @RequestMapping("/sign_up")
    @ResponseBody
    public BaseResponse signUp(@RequestParam(name = "email") String email,
                               @RequestParam(name = "username") String username,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name = "botName") String botName,
                               @RequestParam(name = "botType") Integer botType,
                               @RequestParam(name = "race") Integer race,
                               @RequestParam(name = "description", required = false) String description,
                               @RequestParam(name = "bot") MultipartFile bot) {
        try {
            accountService.signUp(email, username, password, botName, botType, race, description, bot);
            return new SuccessJson();
        } catch (Sc2Exception e) {
            logger.error(e.getMessage(), e);
            return new ErrorJson(e.getErr_no(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ErrorJson(0, "Unknown error");
        }

    }


}
