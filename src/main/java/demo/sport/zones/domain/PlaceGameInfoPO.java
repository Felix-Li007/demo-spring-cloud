package demo.sport.zones.domain;

import java.io.Serializable;
import java.util.Date;


public class PlaceGameInfoPO implements Serializable
{
    private static final long serialVersionUID = 1L;
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
     * 玩法名称
     */
    private String gameName;
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
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除时间
     */
    private Date delTime;
    /**
     * 删除标记
     */
    private Boolean delFlag;

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
     * 玩法代码
     */
    public String getPlayCode()
    {
        return playCode;
    }

    /**
     * 玩法代码
     */
    public void setPlayCode(String playCode)
    {
        this.playCode = playCode;
    }

    /**
     * 玩法名称
     */
    public String getPlayName()
    {
        return playName;
    }

    /**
     * 玩法名称
     */
    public void setPlayName(String playName)
    {
        this.playName = playName;
    }

    /**
     * 玩法名称
     */
    public String getGameName()
    {
        return gameName;
    }

    /**
     * 玩法名称
     */
    public void setGameName(String gameName)
    {
        this.gameName = gameName;
    }

    /**
     * 备注说明
     */
    public String getMemoDesc()
    {
        return memoDesc;
    }

    /**
     * 备注说明
     */
    public void setMemoDesc(String memoDesc)
    {
        this.memoDesc = memoDesc;
    }

    /**
     * 选项数量
     */
    public Integer getOptionNum()
    {
        return optionNum;
    }

    /**
     * 选项数量
     */
    public void setOptionNum(Integer optionNum)
    {
        this.optionNum = optionNum;
    }

    /**
     * 难度级别
     */
    public Integer getGameLevel()
    {
        return gameLevel;
    }

    /**
     * 难度级别
     */
    public void setGameLevel(Integer gameLevel)
    {
        this.gameLevel = gameLevel;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime()
    {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime()
    {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    /**
     * 删除时间
     */
    public Date getDelTime()
    {
        return delTime;
    }

    /**
     * 删除时间
     */
    public void setDelTime(Date delTime)
    {
        this.delTime = delTime;
    }

    /**
     * 删除标记
     */
    public Boolean getDelFlag()
    {
        return delFlag;
    }

    /**
     * 删除标记
     */
    public void setDelFlag(Boolean delFlag)
    {
        this.delFlag = delFlag;
    }

    @Override
    public boolean equals(Object that)
    {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PlaceGameInfoPO other = (PlaceGameInfoPO) that;
        return (this.getSequenceId() == null ? other.getSequenceId() == null : this.getSequenceId().equals(other.getSequenceId()))
                && (this.getPlayCode() == null ? other.getPlayCode() == null : this.getPlayCode().equals(other.getPlayCode()))
                && (this.getPlayName() == null ? other.getPlayName() == null : this.getPlayName().equals(other.getPlayName()))
                && (this.getGameName() == null ? other.getGameName() == null : this.getGameName().equals(other.getGameName()))
                && (this.getMemoDesc() == null ? other.getMemoDesc() == null : this.getMemoDesc().equals(other.getMemoDesc()))
                && (this.getOptionNum() == null ? other.getOptionNum() == null : this.getOptionNum().equals(other.getOptionNum()))
                && (this.getGameLevel() == null ? other.getGameLevel() == null : this.getGameLevel().equals(other.getGameLevel()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getDelTime() == null ? other.getDelTime() == null : this.getDelTime().equals(other.getDelTime()))
                && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSequenceId() == null) ? 0 : getSequenceId().hashCode());
        result = prime * result + ((getPlayCode() == null) ? 0 : getPlayCode().hashCode());
        result = prime * result + ((getPlayName() == null) ? 0 : getPlayName().hashCode());
        result = prime * result + ((getGameName() == null) ? 0 : getGameName().hashCode());
        result = prime * result + ((getMemoDesc() == null) ? 0 : getMemoDesc().hashCode());
        result = prime * result + ((getOptionNum() == null) ? 0 : getOptionNum().hashCode());
        result = prime * result + ((getGameLevel() == null) ? 0 : getGameLevel().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDelTime() == null) ? 0 : getDelTime().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sequenceId=").append(sequenceId);
        sb.append(", playCode=").append(playCode);
        sb.append(", playName=").append(playName);
        sb.append(", gameName=").append(gameName);
        sb.append(", memoDesc=").append(memoDesc);
        sb.append(", optionNum=").append(optionNum);
        sb.append(", gameLevel=").append(gameLevel);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", delTime=").append(delTime);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}