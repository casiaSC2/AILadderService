package ladder.service;

import ladder.dao.model.Account;
import org.springframework.stereotype.Component;

@Component
public class MatchService {
    public native int match(String botNameA, String botNameB, String pathA, String pathB);

}