package ladder.controllers;


import ladder.commons.BaseResponse;
import ladder.commons.ErrorJson;
import ladder.commons.SuccessJson;
import ladder.service.SignUpService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by wangjian on 17-10-24.
 */
@RestController
public class Controller {
    @Resource
    SignUpService signUpService;
    @RequestMapping("/sign_up")
    @ResponseBody
    public BaseResponse signUp(@RequestParam("email") String email,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("botName") String botName,
                               @RequestParam("botType") Integer botType,
                               @RequestParam("race") Integer race,
                               @RequestParam("description") String description,
                               @RequestParam("bot")MultipartFile bot) {
        try {
            signUpService.signUp(email, username, password, botName, botType, race, description, bot);
            return new SuccessJson();
        } catch (Exception e){
            return new ErrorJson(0, e.getMessage());
        }

    }
}
