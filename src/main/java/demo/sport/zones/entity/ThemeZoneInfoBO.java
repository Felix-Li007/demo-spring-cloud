package demo.sport.zones.entity;

import java.io.Serializable;

/**
 * 场馆基本数据
 *
 * @TableName t_place_theme_zone
 */
public class ThemeZoneInfoBO implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    private Integer sequenceId;
    /**
     * 场馆编号
     */
    private String zoneNo;
    /**
     * 主题编号
     */
    private String themeNo;

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


}