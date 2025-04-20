package demo.sport.zones.domain;

/**
 * 竞技场管消息信息表
 *
 * @TableName t_place_mess_info
 */
public class ZonesMessInfoPO extends ZonesZoneBasePO
{


    private static final long serialVersionUID = 1L;
    /**
     * 主题编号
     */
    private String themeNo;
    /**
     * 消息类型
     */
    private String messType;
    /**
     * 消息权重
     */
    private Integer messNum;

    /**
     * 排序
     */
    private String sortMode;

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

    public String getMessType()
    {
        return messType;
    }

    public void setMessType(String messType)
    {
        this.messType = messType;
    }

    /**
     * 消息权重
     */
    public Integer getMessNum()
    {
        return messNum;
    }

    /**
     * 消息权重
     */
    public void setMessNum(Integer messNum)
    {
        this.messNum = messNum;
    }

    public String getSortMode()
    {
        return sortMode;
    }

    public void setSortMode(String sortMode)
    {
        this.sortMode = sortMode;
    }
}