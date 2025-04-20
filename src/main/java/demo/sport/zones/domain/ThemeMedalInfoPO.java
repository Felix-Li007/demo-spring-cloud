package demo.sport.zones.domain;

/**
 * 主题勋章数据
 *
 * @TableName t_place_theme_medal
 */
public class ThemeMedalInfoPO extends ZonesZoneBasePO
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