package demo.sport.zones.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


public class PlaceExperFlowPO implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    private Integer sequenceId;

    /**
     * 游戏代码
     */
    private String gameCode;

    /**
     * 经验编号
     */
    private String experNo;
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
    private Date fetchTime;
    /**
     * 数据创建的日期和时间
     */
    private LocalDateTime createTime;
    /**
     * 数据更新的日期和时间
     */
    private LocalDateTime updateTime;

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
     * 自增序号，作为主键使用，无实际业务含义
     */
    public Integer getSequenceId()
    {
        return sequenceId;
    }

    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    public void setSequenceId(Integer sequenceId)
    {
        this.sequenceId = sequenceId;
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

    /**
     * 获得时间
     */
    public Date getFetchTime()
    {
        return fetchTime;
    }

    /**
     * 获得时间
     */
    public void setFetchTime(Date fetchTime)
    {
        this.fetchTime = fetchTime;
    }

    public LocalDateTime getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime)
    {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime)
    {
        this.updateTime = updateTime;
    }
}