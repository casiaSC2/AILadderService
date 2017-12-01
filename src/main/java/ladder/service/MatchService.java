package ladder.service;

import org.springframework.stereotype.Component;

/**
 * @author wangjian
 */
@Component
public class MatchService {
    public native int match(String sc2Path,
                            String pathA,String configA,String raceA,
                            String pathB,String configB,String raceB,
                            String mapString, String replayPath);

}
