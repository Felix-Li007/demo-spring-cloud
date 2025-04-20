package demo.sport.zones.entity;

import demo.sport.constants.zones.PlayModeEnum;

import java.io.Serializable;
import java.math.BigDecimal;


public class PlayExperInfoBO implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    private Integer sequenceId;
    /**
     * 主题编号
     */
    private String themeNo;
    /**
     * 档案编号
     */
    private String pfileNo;
    /**
     * 用户编号
     */
    private String accountNo;
    /**
     *
     */
    private PlayModeEnum playMode;
    /**
     * 竞技经验
     */
    private BigDecimal experPoint;


    public String getThemeNo()
    {
        return themeNo;
    }

    public void setThemeNo(String themeNo)
    {
        this.themeNo = themeNo;
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
     * 档案编号
     */
    public String getPfileNo()
    {
        return pfileNo;
    }

    /**
     * 档案编号
     */
    public void setPfileNo(String pfileNo)
    {
        this.pfileNo = pfileNo;
    }

    public BigDecimal getExperPoint()
    {
        return experPoint;
    }

    public void setExperPoint(BigDecimal experPoint)
    {
        this.experPoint = experPoint;
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

    public PlayModeEnum getPlayMode()
    {
        return playMode;
    }

    public void setPlayMode(PlayModeEnum playMode)
    {
        this.playMode = playMode;
    }


}