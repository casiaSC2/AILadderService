package ladder.service.impl;

import ladder.dao.AccountMapper;
import ladder.service.SignUpService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by wangjian on 17-10-27.
 */
public class SignUpServiceImpl implements SignUpService {
    @Resource
    AccountMapper accountMapper;
    @Override
    public void signUp(String email, String username, String password, String botName, Integer botType, Integer race, String description, MultipartFile bot) throws Exception {

    }
}
