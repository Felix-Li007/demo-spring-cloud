package demo.sport.zones.entity;

import com.psdn.review.constants.rank.RankTypeEnum;

import java.io.Serializable;
import java.util.Date;


public class RankTaskInfoBO implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String rangCode;
    private String rangValue;
    /**
     * 时间代码
     */
    private String metriCode;
    /**
     * 榜单名次
     */
    private Integer rankSeat = -1;
    private String rankCode;
    /**
     * 变化名次
     */
    private Integer diffSeat = 0;
    private String rankObject;
    private Date rankTime;
    private RankTypeEnum rankType;

    public String getRangCode()
    {
        return rangCode;
    }

    public void setRangCode(String rangCode)
    {
        this.rangCode = rangCode;
    }

    public String getRangValue()
    {
        return rangValue;
    }

    public void setRangValue(String rangValue)
    {
        this.rangValue = rangValue;
    }

    public String getMetriCode()
    {
        return metriCode;
    }

    public void setMetriCode(String metriCode)
    {
        this.metriCode = metriCode;
    }

    public Integer getRankSeat()
    {
        return rankSeat;
    }

    public void setRankSeat(Integer rankSeat)
    {
        this.rankSeat = rankSeat;
    }

    public String getRankCode()
    {
        return rankCode;
    }

    public void setRankCode(String rankCode)
    {
        this.rankCode = rankCode;
    }

    public Integer getDiffSeat()
    {
        return diffSeat;
    }

    public void setDiffSeat(Integer diffSeat)
    {
        this.diffSeat = diffSeat;
    }

    public String getRankObject()
    {
        return rankObject;
    }

    public void setRankObject(String rankObject)
    {
        this.rankObject = rankObject;
    }

    public Date getRankTime()
    {
        return rankTime;
    }

    public void setRankTime(Date rankTime)
    {
        this.rankTime = rankTime;
    }

    public RankTypeEnum getRankType()
    {
        return rankType;
    }

    public void setRankType(RankTypeEnum rankType)
    {
        this.rankType = rankType;
    }

}