package ladder.commons;

/**
 * Created by wangjian on 17-10-27.
 */
public class Constants {
    public static String SALT = "7027A3B06EB927824F12AD27A4A2ACFC";
    public static String getBotPath(){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            return "C:\\temp";
        }
        return "/home/wangjian/temp";
    }
    public static Integer SEASON = 1;
}
