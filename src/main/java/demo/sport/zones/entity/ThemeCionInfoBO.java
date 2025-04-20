package demo.sport.zones.entity;

import demo.sport.zones.domain.ZonesZoneBasePO;

import java.math.BigDecimal;

/**
 * 场馆资金账户
 *
 * @TableName t_place_theme_cion
 */
public class ThemeCionInfoBO extends ZonesZoneBasePO
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
     * 初始金额
     */
    private BigDecimal initiValue;


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