package demo.sport.zones.entity;

import java.io.Serializable;

/**
 * 榜单游戏配置
 *
 * @TableName t_review_rank_game
 */
public class RankGameInfoBO implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    private Integer sequenceId;
    /**
     * 榜单代码
     */
    private String rankCode;
    /**
     * 游戏代码
     */
    private String gameCode;
    /**
     * 玩法编号
     */
    private String playCode;


    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    public Integer getSequenceId()
    {
        return sequenceId;
    }

    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    public void setSequenceId(Integer sequenceId)
    {
        this.sequenceId = sequenceId;
    }

    /**
     * 榜单代码
     */
    public String getRankCode()
    {
        return rankCode;
    }

    /**
     * 榜单代码
     */
    public void setRankCode(String rankCode)
    {
        this.rankCode = rankCode;
    }

    /**
     * 游戏代码
     */
    public String getGameCode()
    {
        return gameCode;
    }

    /**
     * 游戏代码
     */
    public void setGameCode(String gameCode)
    {
        this.gameCode = gameCode;
    }

    /**
     * 玩法编号
     */
    public String getPlayCode()
    {
        return playCode;
    }

    /**
     * 玩法编号
     */
    public void setPlayCode(String playCode)
    {
        this.playCode = playCode;
    }

}