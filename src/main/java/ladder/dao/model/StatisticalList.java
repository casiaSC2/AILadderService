package ladder.dao.model;

public class StatisticalList {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column statistical_list.season
     *
     * @mbg.generated
     */
    private Integer season;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column statistical_list.bot_id
     *
     * @mbg.generated
     */
    private Integer botId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column statistical_list.bot_name
     *
     * @mbg.generated
     */
    private String botName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column statistical_list.matches
     *
     * @mbg.generated
     */
    private Integer matches;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column statistical_list.wins
     *
     * @mbg.generated
     */
    private Integer wins;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column statistical_list.win_rate
     *
     * @mbg.generated
     */
    private Float winRate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column statistical_list.season
     *
     * @return the value of statistical_list.season
     *
     * @mbg.generated
     */
    public Integer getSeason() {
        return season;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column statistical_list.season
     *
     * @param season the value for statistical_list.season
     *
     * @mbg.generated
     */
    public void setSeason(Integer season) {
        this.season = season;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column statistical_list.bot_id
     *
     * @return the value of statistical_list.bot_id
     *
     * @mbg.generated
     */
    public Integer getBotId() {
        return botId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column statistical_list.bot_id
     *
     * @param botId the value for statistical_list.bot_id
     *
     * @mbg.generated
     */
    public void setBotId(Integer botId) {
        this.botId = botId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column statistical_list.bot_name
     *
     * @return the value of statistical_list.bot_name
     *
     * @mbg.generated
     */
    public String getBotName() {
        return botName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column statistical_list.bot_name
     *
     * @param botName the value for statistical_list.bot_name
     *
     * @mbg.generated
     */
    public void setBotName(String botName) {
        this.botName = botName == null ? null : botName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column statistical_list.matches
     *
     * @return the value of statistical_list.matches
     *
     * @mbg.generated
     */
    public Integer getMatches() {
        return matches;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column statistical_list.matches
     *
     * @param matches the value for statistical_list.matches
     *
     * @mbg.generated
     */
    public void setMatches(Integer matches) {
        this.matches = matches;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column statistical_list.wins
     *
     * @return the value of statistical_list.wins
     *
     * @mbg.generated
     */
    public Integer getWins() {
        return wins;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column statistical_list.wins
     *
     * @param wins the value for statistical_list.wins
     *
     * @mbg.generated
     */
    public void setWins(Integer wins) {
        this.wins = wins;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column statistical_list.win_rate
     *
     * @return the value of statistical_list.win_rate
     *
     * @mbg.generated
     */
    public Float getWinRate() {
        return winRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column statistical_list.win_rate
     *
     * @param winRate the value for statistical_list.win_rate
     *
     * @mbg.generated
     */
    public void setWinRate(Float winRate) {
        this.winRate = winRate;
    }
}