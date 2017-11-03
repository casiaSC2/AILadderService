package laddertest;

import ladder.service.MatchService;
import org.junit.Test;

public class TestDll {

    @Test
    public void test(){
        System.load("D:\\StarcraftAI\\commandcenter\\bin\\CommandCenter.dll");
        MatchService matchService = new MatchService();
        String botAPath = "D:\\StarcraftAI\\commandcenter\\bin\\BotConfig.txt";
        String botBPath = "D:\\StarcraftAI\\commandcenter\\bin\\BotConfig.txt";
        String mapPath = "D:\\game\\StarCraft II\\maps\\Ladder2017Season1\\AbyssalReefLE.SC2Map";
        int res = matchService.match("D:\\game\\StarCraft II\\Versions\\Base58400\\SC2_x64.exe",botAPath,botBPath, mapPath);
        System.out.println(res);
    }
}
