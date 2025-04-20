package demo.sport.zones.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName t_place_cion_info
 */
public class PlaceCionInfoBO implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    private Integer sequenceId;
    /**
     * 货币代码
     */
    private String cionCode;
    /**
     * 货币名称
     */
    private String cionName;
    /**
     * 货币说明
     */
    private String cionDesc;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 货币图标
     */
    private String cionIcon;
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

    public String getCionIcon()
    {
        return cionIcon;
    }

    public void setCionIcon(String cionIcon)
    {
        this.cionIcon = cionIcon;
    }

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
     * 货币代码
     */
    public String getCionCode()
    {
        return cionCode;
    }

    /**
     * 货币代码
     */
    public void setCionCode(String cionCode)
    {
        this.cionCode = cionCode;
    }

    /**
     * 货币名称
     */
    public String getCionName()
    {
        return cionName;
    }

    /**
     * 货币名称
     */
    public void setCionName(String cionName)
    {
        this.cionName = cionName;
    }

    /**
     * 货币说明
     */
    public String getCionDesc()
    {
        return cionDesc;
    }

    /**
     * 货币说明
     */
    public void setCionDesc(String cionDesc)
    {
        this.cionDesc = cionDesc;
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
        PlaceCionInfoBO other = (PlaceCionInfoBO) that;
        return (this.getSequenceId() == null ? other.getSequenceId() == null : this.getSequenceId().equals(other.getSequenceId()))
                && (this.getCionCode() == null ? other.getCionCode() == null : this.getCionCode().equals(other.getCionCode()))
                && (this.getCionName() == null ? other.getCionName() == null : this.getCionName().equals(other.getCionName()))
                && (this.getCionDesc() == null ? other.getCionDesc() == null : this.getCionDesc().equals(other.getCionDesc()))
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
        result = prime * result + ((getCionCode() == null) ? 0 : getCionCode().hashCode());
        result = prime * result + ((getCionName() == null) ? 0 : getCionName().hashCode());
        result = prime * result + ((getCionDesc() == null) ? 0 : getCionDesc().hashCode());
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
        sb.append(", cionCode=").append(cionCode);
        sb.append(", cionName=").append(cionName);
        sb.append(", cionDesc=").append(cionDesc);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", delTime=").append(delTime);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}