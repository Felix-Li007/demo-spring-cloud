package demo.sport.zones.domain;

/**
 * 用户竞技档案
 *
 * @TableName t_place_pfile_info
 */
public class ZonesPfileInfoPO extends ZonesZoneBasePO
{
    private static final long serialVersionUID = 1L;
    /**
     * 团队编号
     */
    private String teamNo;
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
    private String proteState;
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
    private String headType;
    /**
     * 头像模式
     */
    private String headMode;

    @Override
    public String toString()
    {
        return "ZonesPfileInfoPO{" +
                "teamNo='" + teamNo + '\'' +
                ", headNo='" + headNo + '\'' +
                ", pfileNo='" + pfileNo + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", experPoint=" + experPoint +
                ", proteState='" + proteState + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headImage='" + headImage + '\'' +
                ", headType='" + headType + '\'' +
                ", headMode='" + headMode + '\'' +
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

    public String getHeadType()
    {
        return headType;
    }

    public void setHeadType(String headType)
    {
        this.headType = headType;
    }

    public String getHeadMode()
    {
        return headMode;
    }

    public void setHeadMode(String headMode)
    {
        this.headMode = headMode;
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

    public String getProteState()
    {
        return proteState;
    }

    public void setProteState(String proteState)
    {
        this.proteState = proteState;
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