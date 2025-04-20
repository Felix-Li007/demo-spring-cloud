package demo.sport.zones.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 经验基础数据
 *
 * @TableName t_place_zone_game
 */
public class PlaceZoneGamePO implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    private Integer sequenceId;
    /**
     * 主题编号
     */
    private String themeNo;
    /**
     * 场馆编号
     */
    private String zoneNo;
    /**
     * 玩法代码
     */
    private String playCode;
    /**
     * 经验点数
     */
    private Integer experPoint;
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
     * 主题编号
     */
    public String getThemeNo()
    {
        return themeNo;
    }

    /**
     * 主题编号
     */
    public void setThemeNo(String themeNo)
    {
        this.themeNo = themeNo;
    }

    /**
     * 场馆编号
     */
    public String getZoneNo()
    {
        return zoneNo;
    }

    /**
     * 场馆编号
     */
    public void setZoneNo(String zoneNo)
    {
        this.zoneNo = zoneNo;
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
     * 经验点数
     */
    public Integer getExperPoint()
    {
        return experPoint;
    }

    /**
     * 经验点数
     */
    public void setExperPoint(Integer experPoint)
    {
        this.experPoint = experPoint;
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
        PlaceZoneGamePO other = (PlaceZoneGamePO) that;
        return (this.getSequenceId() == null ? other.getSequenceId() == null : this.getSequenceId().equals(other.getSequenceId()))
                && (this.getThemeNo() == null ? other.getThemeNo() == null : this.getThemeNo().equals(other.getThemeNo()))
                && (this.getZoneNo() == null ? other.getZoneNo() == null : this.getZoneNo().equals(other.getZoneNo()))
                && (this.getPlayCode() == null ? other.getPlayCode() == null : this.getPlayCode().equals(other.getPlayCode()))
                && (this.getExperPoint() == null ? other.getExperPoint() == null : this.getExperPoint().equals(other.getExperPoint()))
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
        result = prime * result + ((getThemeNo() == null) ? 0 : getThemeNo().hashCode());
        result = prime * result + ((getZoneNo() == null) ? 0 : getZoneNo().hashCode());
        result = prime * result + ((getPlayCode() == null) ? 0 : getPlayCode().hashCode());
        result = prime * result + ((getExperPoint() == null) ? 0 : getExperPoint().hashCode());
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
        sb.append(", themeNo=").append(themeNo);
        sb.append(", zoneNo=").append(zoneNo);
        sb.append(", playCode=").append(playCode);
        sb.append(", experPoint=").append(experPoint);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", delTime=").append(delTime);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}