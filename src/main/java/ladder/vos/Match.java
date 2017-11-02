package ladder.vos;

import java.util.Date;

/**
 * Created by wangjian on 17-10-28.
 */
public class Match {
    private Integer id;
    private String time;
    private String opponent_bot;
    private String race;
    private String map;
    private String result;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOpponent_bot() {
        return opponent_bot;
    }

    public void setOpponent_bot(String opponent_bot) {
        this.opponent_bot = opponent_bot;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
