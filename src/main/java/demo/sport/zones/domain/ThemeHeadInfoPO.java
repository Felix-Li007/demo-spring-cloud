package demo.sport.zones.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 默认头像列表
 *
 * @TableName t_place_head_info
 */
public class ThemeHeadInfoPO implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    private Integer sequenceId;
    /**
     * 头像编号
     */
    private String headNo;
    /**
     *
     */
    private String themeNo;
    /**
     * 头像地址
     */
    private String headImage;
    /**
     * 国家代码
     */
    private String nationCode;
    /**
     * 数据创建的日期和时间
     */
    private Date createTime;
    /**
     * 数据更新的日期和时间
     */
    private Date updateTime;

    /**
     * 排序位置
     */
    private Integer orderPos;

    public Integer getOrderPos()
    {
        return orderPos;
    }

    public void setOrderPos(Integer orderPos)
    {
        this.orderPos = orderPos;
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
     * 头像编号
     */
    public String getHeadNo()
    {
        return headNo;
    }

    /**
     * 头像编号
     */
    public void setHeadNo(String headNo)
    {
        this.headNo = headNo;
    }

    /**
     *
     */
    public String getThemeNo()
    {
        return themeNo;
    }

    /**
     *
     */
    public void setThemeNo(String themeNo)
    {
        this.themeNo = themeNo;
    }

    /**
     * 头像地址
     */
    public String getHeadImage()
    {
        return headImage;
    }

    /**
     * 头像地址
     */
    public void setHeadImage(String headImage)
    {
        this.headImage = headImage;
    }

    /**
     * 国家代码
     */
    public String getNationCode()
    {
        return nationCode;
    }

    /**
     * 国家代码
     */
    public void setNationCode(String nationCode)
    {
        this.nationCode = nationCode;
    }

    /**
     * 数据创建的日期和时间
     */
    public Date getCreateTime()
    {
        return createTime;
    }

    /**
     * 数据创建的日期和时间
     */
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    /**
     * 数据更新的日期和时间
     */
    public Date getUpdateTime()
    {
        return updateTime;
    }

    /**
     * 数据更新的日期和时间
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
        ThemeHeadInfoPO other = (ThemeHeadInfoPO) that;
        return (this.getSequenceId() == null ? other.getSequenceId() == null : this.getSequenceId().equals(other.getSequenceId()))
                && (this.getHeadNo() == null ? other.getHeadNo() == null : this.getHeadNo().equals(other.getHeadNo()))
                && (this.getThemeNo() == null ? other.getThemeNo() == null : this.getThemeNo().equals(other.getThemeNo()))
                && (this.getHeadImage() == null ? other.getHeadImage() == null : this.getHeadImage().equals(other.getHeadImage()))
                && (this.getNationCode() == null ? other.getNationCode() == null : this.getNationCode().equals(other.getNationCode()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSequenceId() == null) ? 0 : getSequenceId().hashCode());
        result = prime * result + ((getHeadNo() == null) ? 0 : getHeadNo().hashCode());
        result = prime * result + ((getThemeNo() == null) ? 0 : getThemeNo().hashCode());
        result = prime * result + ((getHeadImage() == null) ? 0 : getHeadImage().hashCode());
        result = prime * result + ((getNationCode() == null) ? 0 : getNationCode().hashCode());
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
        sb.append(", headNo=").append(headNo);
        sb.append(", themeNo=").append(themeNo);
        sb.append(", headImage=").append(headImage);
        sb.append(", nationCode=").append(nationCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}