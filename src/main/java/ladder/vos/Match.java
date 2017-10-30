package ladder.vos;

import java.util.Date;

/**
 * Created by wangjian on 17-10-28.
 */
public class Match {
    private Date time;
    private String opponent_bot;
    private String race;
    private String map;
    private String result;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
