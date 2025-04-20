package demo.sport.zones.entity;

import demo.sport.constants.zones.HeadModeEnum;
import demo.sport.constants.zones.HeadTypeEnum;
import demo.sport.constants.zones.ProteStateEnum;

import java.io.Serializable;


public class ZonesPfileInfoBO implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Integer sequenceId;
    /**
     * 团队编号
     */
    private String teamNo;
    /**
     * 头像编号
     */
    private String headNo;
    /**
     * 档案编号
     */
    private String pfileNo;
    /**
     * 用户编号
     */
    private String accountNo;
    /**
     * 竞技经验
     */
    private Integer experPoint;
    /**
     * 保护状态
     */
    private ProteStateEnum proteState;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 资源的URI地址（区别于URL，不包括主机地址）
     */
    private String headImage;
    /**
     * 头像类型
     */
    private HeadTypeEnum headType;
    /**
     * 头像模式
     */
    private HeadModeEnum headMode;

    public ProteStateEnum getProteState()
    {
        return proteState;
    }

    public void setProteState(ProteStateEnum proteState)
    {
        this.proteState = proteState;
    }

    public Integer getSequenceId()
    {
        return sequenceId;
    }

    public void setSequenceId(Integer sequenceId)
    {
        this.sequenceId = sequenceId;
    }

    @Override
    public String toString()
    {
        return "ZonesPfileInfoBO{" +
                "sequenceId=" + sequenceId +
                ", teamNo='" + teamNo + '\'' +
                ", headNo='" + headNo + '\'' +
                ", pfileNo='" + pfileNo + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", experPoint=" + experPoint +
                ", proteState='" + proteState + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headImage='" + headImage + '\'' +
                ", headType=" + headType +
                ", headMode=" + headMode +
                '}';
    }

    public String getTeamNo()
    {
        return teamNo;
    }

    public void setTeamNo(String teamNo)
    {
        this.teamNo = teamNo;
    }

    public String getHeadNo()
    {
        return headNo;
    }

    public void setHeadNo(String headNo)
    {
        this.headNo = headNo;
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

    public HeadTypeEnum getHeadType()
    {
        return headType;
    }

    public void setHeadType(HeadTypeEnum headType)
    {
        this.headType = headType;
    }

    public HeadModeEnum getHeadMode()
    {
        return headMode;
    }

    public void setHeadMode(HeadModeEnum headMode)
    {
        this.headMode = headMode;
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
     * 用户昵称
     */
    public String getNickName()
    {
        return nickName;
    }

    /**
     * 用户昵称
     */
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    /**
     * 资源的URI地址（区别于URL，不包括主机地址）
     */
    public String getHeadImage()
    {
        return headImage;
    }

    /**
     * 资源的URI地址（区别于URL，不包括主机地址）
     */
    public void setHeadImage(String headImage)
    {
        this.headImage = headImage;
    }


}