package demo.sport.zones.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;


public class PlaceExperFlowBO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 游戏代码
     */
    private String gameCode;

    /**
     * 经验编号
     */
    private String experNo;

    /**
     * 主题编号
     */
    private String themeNo;
    /**
     * 场馆编号
     */
    private String zoneNo;
    /**
     * 玩法代码
     */
    private String playCode;
    /**
     * 用户编号
     */
    private String accountNo;
    /**
     * 竞技积分
     */
    private Integer experPoint;
    /**
     * 获得时间
     */
    private LocalDateTime fetchTime;

    public String getThemeNo()
    {
        return themeNo;
    }

    public void setThemeNo(String themeNo)
    {
        this.themeNo = themeNo;
    }

    public String getGameCode()
    {
        return gameCode;
    }

    public void setGameCode(String gameCode)
    {
        this.gameCode = gameCode;
    }

    public String getExperNo()
    {
        return experNo;
    }

    public void setExperNo(String experNo)
    {
        this.experNo = experNo;
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
     * 玩法代码
     */
    public String getPlayCode()
    {
        return playCode;
    }

    /**
     * 玩法代码
     */
    public void setPlayCode(String playCode)
    {
        this.playCode = playCode;
    }

    /**
     * 用户编号
     */
    public String getAccountNo()
    {
        return accountNo;
    }

    /**
     * 用户编号
     */
    public void setAccountNo(String accountNo)
    {
        this.accountNo = accountNo;
    }

    /**
     * 竞技积分
     */
    public Integer getExperPoint()
    {
        return experPoint;
    }

    /**
     * 竞技积分
     */
    public void setExperPoint(Integer experPoint)
    {
        this.experPoint = experPoint;
    }

    public LocalDateTime getFetchTime()
    {
        return fetchTime;
    }

    public void setFetchTime(LocalDateTime fetchTime)
    {
        this.fetchTime = fetchTime;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}