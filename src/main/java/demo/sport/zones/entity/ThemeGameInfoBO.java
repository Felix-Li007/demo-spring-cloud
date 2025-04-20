package demo.sport.zones.entity;

import demo.sport.constants.zones.PlayModeEnum;

import java.io.Serializable;

/**
 * 经验基础数据
 *
 * @TableName T_PLACE_GAME_INFO
 */
public class ThemeGameInfoBO implements Serializable
{

    /**
     * 自增序号，作为主键使用，无实际业务含义
     */

    private Integer sequenceId;
    /**
     * 玩法代码
     */
    private String playCode;
    /**
     * 玩法名称
     */
    private String playName;

    /**
     * 玩法模式
     */
    private PlayModeEnum playMode;
    /**
     * 备注说明
     */
    private String memoDesc;
    /**
     * 选项数量
     */

    private Integer optionNum;
    /**
     * 难度级别
     */

    private Integer gameLevel;

    public PlayModeEnum getPlayMode()
    {
        return playMode;
    }

    public void setPlayMode(PlayModeEnum playMode)
    {
        this.playMode = playMode;
    }

    public Integer getSequenceId()
    {
        return sequenceId;
    }

    public void setSequenceId(Integer sequenceId)
    {
        this.sequenceId = sequenceId;
    }

    public String getPlayCode()
    {
        return playCode;
    }

    public void setPlayCode(String playCode)
    {
        this.playCode = playCode;
    }

    public String getPlayName()
    {
        return playName;
    }

    public void setPlayName(String playName)
    {
        this.playName = playName;
    }

    public String getMemoDesc()
    {
        return memoDesc;
    }

    public void setMemoDesc(String memoDesc)
    {
        this.memoDesc = memoDesc;
    }

    public Integer getOptionNum()
    {
        return optionNum;
    }

    public void setOptionNum(Integer optionNum)
    {
        this.optionNum = optionNum;
    }

    public Integer getGameLevel()
    {
        return gameLevel;
    }

    public void setGameLevel(Integer gameLevel)
    {
        this.gameLevel = gameLevel;
    }
}
