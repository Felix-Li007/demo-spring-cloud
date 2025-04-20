package demo.sport.zones.domain;

import java.math.BigDecimal;

public class ZonesThemeCionPO extends ZonesZoneBasePO
{
    private static final long serialVersionUID = 1L;
    /**
     * 主题编号
     */
    private String themeNo;
    /**
     * 货币代码
     */
    private String cionCode;

    /**
     * 货币名称
     */
    private String cionName;
    /**
     * 初始金额
     */
    private BigDecimal initiValue;

    public String getCionName()
    {
        return cionName;
    }

    public void setCionName(String cionName)
    {
        this.cionName = cionName;
    }

    public BigDecimal getInitiValue()
    {
        return initiValue;
    }

    public void setInitiValue(BigDecimal initiValue)
    {
        this.initiValue = initiValue;
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

    /**
     * 货币代码
     */
    public String getCionCode()
    {
        return cionCode;
    }

    /**
     * 货币代码
     */
    public void setCionCode(String cionCode)
    {
        this.cionCode = cionCode;
    }


}