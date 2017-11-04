package ladder.utils;

/**
 * Created by wangjian on 17-10-30.
 */
public class EnumUtils {
    public static String getRace(Integer i){
        switch (i){
            case 0:
                return "Terran";
            case 1:
                return "Protoss";
            case 2:
                return "Zerg";
            default:
                return "Unknown";
        }
    }

}
