package laddertest;

import ladder.service.MatchService;
import org.junit.Test;

public class TestDll {

    @Test
    public void test(){
        System.load("D:\\StarcraftAI\\commandcenter\\bin\\CommandCenter.dll");
        MatchService matchService = new MatchService();
        int res = matchService.match("hi","hi", "map");
        System.out.println(res);
    }
}
