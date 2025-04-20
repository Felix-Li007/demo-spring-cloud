package demo.sport.zones.domain;

/**
 * 竞技场管主题排名表
 *
 * @TableName t_place_theme_rank
 */
public class ZonesThemeRankPO extends ZonesZoneBasePO
{


    private static final long serialVersionUID = 1L;
    /**
     * 主题编号
     */
    private String themeNo;
    /**
     * 榜单编码
     */
    private String rankCode;

    /**
     * 位置
     */
    private Integer orderPosi;


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
     * 榜单编码
     */
    public String getRankCode()
    {
        return rankCode;
    }

    /**
     * 榜单编码
     */
    public void setRankCode(String rankCode)
    {
        this.rankCode = rankCode;
    }


    /**
     * 位置
     */
    public Integer getOrderPosi()
    {
        return orderPosi;
    }

    /**
     * 位置
     */
    public void setOrderPosi(Integer orderPosi)
    {
        this.orderPosi = orderPosi;
    }


}