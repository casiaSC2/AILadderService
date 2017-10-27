package ladder.service;

import ladder.commons.Sc2Exception;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by wangjian on 17-10-27.
 */
@Service
public interface SignUpService {
    void signUp(String email, String username, String password, String botName, Integer botType, Integer race,
                String description, MultipartFile bot) throws Exception;
}
