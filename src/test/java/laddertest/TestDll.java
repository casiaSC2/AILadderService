package laddertest;

import org.junit.Test;

public class TestDll {

    @Test
    public void test(){
        System.load("D:\\StarcraftAI\\commandcenter\\bin\\CasiaCenter.dll");
        //MatchService matchService = new MatchService();
        String botAPath = "D:\\scbots\\AILadderService\\Zeratul.dll";
        String botBPath = "D:\\scbots\\AILadderService\\Zeratul.dll";
        String mapPath = "D:\\game\\StarCraft II\\maps\\Ladder2017Season1\\AbyssalReefLE.SC2Map";
//        int res = matchService.match("D:\\game\\StarCraft II\\Versions\\Base58400\\SC2_x64.exe",botAPath,"Zerg",botBPath,"Zerg", mapPath, "D:\\temp");
//        System.out.println(res);
    }
}
