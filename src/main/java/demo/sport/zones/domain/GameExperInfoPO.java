package demo.sport.zones.domain;


public class GameExperInfoPO extends ZonesZoneBasePO
{
    private static final long serialVersionUID = 1L;
    /**
     * 主题编号
     */
    private String themeNo;

    private String zoneNo;
    /**
     * 玩法代码
     */
    private String playCode;
    /**
     * 玩法名称
     */
    private String playName;
    /**
     * 经验点数
     */
    private Integer experPoint;

    public String getThemeNo()
    {
        return themeNo;
    }

    public void setThemeNo(String themeNo)
    {
        this.themeNo = themeNo;
    }

    public String getZoneNo()
    {
        return zoneNo;
    }

    public void setZoneNo(String zoneNo)
    {
        this.zoneNo = zoneNo;
    }

    public String getPlayCode()
    {
        return playCode;
    }

    public void setPlayCode(String playCode)
    {
        this.playCode = playCode;
    }

    public String getPlayName()
    {
        return playName;
    }

    public void setPlayName(String playName)
    {
        this.playName = playName;
    }

    public Integer getExperPoint()
    {
        return experPoint;
    }

    public void setExperPoint(Integer experPoint)
    {
        this.experPoint = experPoint;
    }


}