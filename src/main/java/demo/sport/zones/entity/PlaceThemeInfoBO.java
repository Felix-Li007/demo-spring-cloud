package demo.sport.zones.entity;

import java.util.Date;

public class PlaceThemeInfoBO
{


    private static final long serialVersionUID = 1L;
    /**
     * 主题编号
     */
    private String themeNo;
    /**
     * 主题描述
     */
    private String themeDesc;
    /**
     * 主题标题
     */
    private String themeTitle;
    /**
     * 开放状态，00-待开放，10-开放中，20-已关闭
     */
    private String openState;
    /**
     * 开放时间
     */
    private Date openTime;
    /**
     * 关闭时间
     */
    private Date closeTime;

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
     * 主题标题
     */
    public String getThemeTitle()
    {
        return themeTitle;
    }

    /**
     * 主题标题
     */
    public void setThemeTitle(String themeTitle)
    {
        this.themeTitle = themeTitle;
    }

    /**
     * 开放状态，00-待开放，10-开放中，20-已关闭
     */
    public String getOpenState()
    {
        return openState;
    }

    /**
     * 开放状态，00-待开放，10-开放中，20-已关闭
     */
    public void setOpenState(String openState)
    {
        this.openState = openState;
    }

    /**
     * 开放时间
     */
    public Date getOpenTime()
    {
        return openTime;
    }

    /**
     * 开放时间
     */
    public void setOpenTime(Date openTime)
    {
        this.openTime = openTime;
    }

    /**
     * 关闭时间
     */
    public Date getCloseTime()
    {
        return closeTime;
    }

    /**
     * 关闭时间
     */
    public void setCloseTime(Date closeTime)
    {
        this.closeTime = closeTime;
    }


    public String getThemeDesc()
    {
        return themeDesc;
    }

    public void setThemeDesc(String themeDesc)
    {
        this.themeDesc = themeDesc;
    }
}
