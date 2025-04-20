package demo.sport.zones.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public class TeamExperInfoPO implements Serializable
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
     * 主题编号
     */
    private String teamNo;

    /**
     * 竞技经验
     */
    private BigDecimal experPoint;
    /**
     * 创建时间：数据创建的日期和时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间：数据更新的日期和时间
     */
    private LocalDateTime updateTime;
    /**
     * 删除标志
     */
    private Integer delFlag;

    public String getThemeNo()
    {
        return themeNo;
    }

    public void setThemeNo(String themeNo)
    {
        this.themeNo = themeNo;
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

    public String getTeamNo()
    {
        return teamNo;
    }

    public void setTeamNo(String teamNo)
    {
        this.teamNo = teamNo;
    }

    public BigDecimal getExperPoint()
    {
        return experPoint;
    }

    public void setExperPoint(BigDecimal experPoint)
    {
        this.experPoint = experPoint;
    }

    public LocalDateTime getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime)
    {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime)
    {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag)
    {
        this.delFlag = delFlag;
    }
}
