package demo.sport.zones.entity;

import demo.sport.zones.domain.ZonesZoneBasePO;

public class ThemeMedalInfoBO extends ZonesZoneBasePO
{


    private static final long serialVersionUID = 1L;
    /**
     * 主题编号
     */
    private String themeNo;
    /**
     * 分组编号
     */
    private String medalNo;

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
     * 分组编号
     */
    public String getMedalNo()
    {
        return medalNo;
    }

    /**
     * 分组编号
     */
    public void setMedalNo(String medalNo)
    {
        this.medalNo = medalNo;
    }


}