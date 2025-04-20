package demo.sport.zones.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 国家基础数据
 *
 * @TableName t_place_nation_info
 */
public class ZonesNationInfoPO implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    private Integer sequenceId;
    /**
     * 国家代码
     */
    private String nationCode;
    /**
     * 国家名称
     */
    private String nationName;
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
     * 国家名称
     */
    public String getNationName()
    {
        return nationName;
    }

    /**
     * 国家名称
     */
    public void setNationName(String nationName)
    {
        this.nationName = nationName;
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

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

}