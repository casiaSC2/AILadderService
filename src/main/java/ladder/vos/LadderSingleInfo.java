package ladder.vos;

import ladder.commons.BaseResponse;

/**
 * Created by wangjian on 17-10-28.
 */
public class LadderSingleInfo {
    private String botName;
    private String userName;
    private Integer race;
    private Integer matches;
    private Integer wins;
    private Float winRate;

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRace() {
        return race;
    }

    public void setRace(Integer race) {
        this.race = race;
    }

    public Integer getMatches() {
        return matches;
    }

    public void setMatches(Integer matches) {
        this.matches = matches;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Float getWinRate() {
        return winRate;
    }

    public void setWinRate(Float winRate) {
        this.winRate = winRate;
    }
}
