package laddertest;

import ladder.service.MatchService;
import org.junit.Test;

public class TestDll {

    @Test
    public void test(){
        System.load("D:\\StarcraftAI\\commandcenter\\bin\\CommandCenter.dll");
        MatchService matchService = new MatchService();
        matchService.match("hello", "hello","hi","hi");
    }
}
