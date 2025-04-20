package demo.sport.zones.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 货币使用限制
 *
 * @TableName t_place_cion_limit
 */
public class CionLimitInfoPO implements Serializable
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
     * 货币代码
     */
    private String cionCode;
    /**
     * 最高
     */
    private Integer limitValue;
    /**
     * 00-分钟，10-小时，20-天数，30-月数，40-年数
     */
    private String timeUnit;
    /**
     * 00-固定模式，10-比例模式
     */
    private String valueMode;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

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
     * 最高
     */
    public Integer getLimitValue()
    {
        return limitValue;
    }

    /**
     * 最高
     */
    public void setLimitValue(Integer limitValue)
    {
        this.limitValue = limitValue;
    }

    /**
     * 00-分钟，10-小时，20-天数，30-月数，40-年数
     */
    public String getTimeUnit()
    {
        return timeUnit;
    }

    /**
     * 00-分钟，10-小时，20-天数，30-月数，40-年数
     */
    public void setTimeUnit(String timeUnit)
    {
        this.timeUnit = timeUnit;
    }

    /**
     * 00-固定模式，10-比例模式
     */
    public String getValueMode()
    {
        return valueMode;
    }

    /**
     * 00-固定模式，10-比例模式
     */
    public void setValueMode(String valueMode)
    {
        this.valueMode = valueMode;
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
        CionLimitInfoPO other = (CionLimitInfoPO) that;
        return (this.getSequenceId() == null ? other.getSequenceId() == null : this.getSequenceId().equals(other.getSequenceId()))
                && (this.getThemeNo() == null ? other.getThemeNo() == null : this.getThemeNo().equals(other.getThemeNo()))
                && (this.getCionCode() == null ? other.getCionCode() == null : this.getCionCode().equals(other.getCionCode()))
                && (this.getLimitValue() == null ? other.getLimitValue() == null : this.getLimitValue().equals(other.getLimitValue()))
                && (this.getTimeUnit() == null ? other.getTimeUnit() == null : this.getTimeUnit().equals(other.getTimeUnit()))
                && (this.getValueMode() == null ? other.getValueMode() == null : this.getValueMode().equals(other.getValueMode()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSequenceId() == null) ? 0 : getSequenceId().hashCode());
        result = prime * result + ((getThemeNo() == null) ? 0 : getThemeNo().hashCode());
        result = prime * result + ((getCionCode() == null) ? 0 : getCionCode().hashCode());
        result = prime * result + ((getLimitValue() == null) ? 0 : getLimitValue().hashCode());
        result = prime * result + ((getTimeUnit() == null) ? 0 : getTimeUnit().hashCode());
        result = prime * result + ((getValueMode() == null) ? 0 : getValueMode().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
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
        sb.append(", cionCode=").append(cionCode);
        sb.append(", limitValue=").append(limitValue);
        sb.append(", timeUnit=").append(timeUnit);
        sb.append(", valueMode=").append(valueMode);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}