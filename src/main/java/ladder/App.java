package ladder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by wangjian on 17-10-27.
 */
@MapperScan(basePackages = "ladder.dao")
@SpringBootApplication
public class App {
    public static void main(String args[]){
        System.loadLibrary("CommandCenter");
        // System.load("D:\\StarcraftAI\\commandcenter\\bin\\CommandCenter.dll");
        SpringApplication.run(App.class, args);
    }
}
