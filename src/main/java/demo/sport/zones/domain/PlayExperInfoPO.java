package demo.sport.zones.domain;

import java.io.Serializable;
import java.time.LocalDateTime;


public class PlayExperInfoPO implements Serializable
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
//    /**
//     * 档案编号
//     */
//    private String pfileNo;
    /**
     * 用户编号
     */
    private String accountNo;
    /**
     *
     */
    private String playMode;
    /**
     * 竞技经验
     */
    private Integer experPoint;
    /**
     * 创建时间：数据创建的日期和时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间：数据更新的日期和时间
     */
    private LocalDateTime updateTime;
    /**
     * 删除标志
     */
    private Integer delFlag;

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

//    /**
//     * 档案编号
//     */
//    public String getPfileNo() {
//        return pfileNo;
//    }
//
//    /**
//     * 档案编号
//     */
//    public void setPfileNo(String pfileNo) {
//        this.pfileNo = pfileNo;
//    }

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
     *
     */
    public String getPlayMode()
    {
        return playMode;
    }

    /**
     *
     */
    public void setPlayMode(String playMode)
    {
        this.playMode = playMode;
    }

    /**
     * 竞技经验
     */
    public Integer getExperPoint()
    {
        return experPoint;
    }

    /**
     * 竞技经验
     */
    public void setExperPoint(Integer experPoint)
    {
        this.experPoint = experPoint;
    }


    /**
     * 删除标志
     */
    public Integer getDelFlag()
    {
        return delFlag;
    }

    /**
     * 删除标志
     */
    public void setDelFlag(Integer delFlag)
    {
        this.delFlag = delFlag;
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