package demo.sport.zones.domain;

public class ZonesThemeMembPO extends ZonesZoneBasePO
{


    private static final long serialVersionUID = 1L;
    /**
     * 主题编号
     */
    private String themeNo;
    /**
     * 体系编号
     */
    private String compoNo;

    public String getCompoNo()
    {
        return compoNo;
    }

    public void setCompoNo(String compoNo)
    {
        this.compoNo = compoNo;
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