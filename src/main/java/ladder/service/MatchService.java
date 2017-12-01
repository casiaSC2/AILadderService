package ladder.service;

import ladder.dao.model.Account;
import org.springframework.stereotype.Component;

@Component
public class MatchService {
    public native int match(String sc2Path,
                            String pathA,String configA,String raceA,
                            String pathB,String configB,String raceB,
                            String mapString, String replayPath);

}
