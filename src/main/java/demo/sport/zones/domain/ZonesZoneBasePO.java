package demo.sport.zones.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: houpengyi
 * @date: 2022/9/26 14:28
 * @description:
 */
public class ZonesZoneBasePO implements Serializable
{
    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    private Integer sequenceId;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;
    /**
     * 删除标志 0：未删除 1：删除
     */
    private Integer delFlag = 0;
    /**
     * 删除时间
     */
    private LocalDateTime delTime;

    public Integer getSequenceId()
    {
        return sequenceId;
    }

    public void setSequenceId(Integer sequenceId)
    {
        this.sequenceId = sequenceId;
    }

    public Integer getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag)
    {
        this.delFlag = delFlag;
    }

    public LocalDateTime getDelTime()
    {
        return delTime;
    }

    public void setDelTime(LocalDateTime delTime)
    {
        this.delTime = delTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }
}
