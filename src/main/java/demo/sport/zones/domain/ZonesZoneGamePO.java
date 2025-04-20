package demo.sport.zones.domain;

/**
 * 场馆玩法列表
 *
 * @TableName t_place_zone_game
 */
public class ZonesZoneGamePO extends ZonesZoneBasePO
{

    private static final long serialVersionUID = 1L;
    /**
     * 场馆编号
     */
    private String zoneNo;
    /**
     * 游戏种类
     */
    private String gameKind;
    /**
     * 玩法编号
     */
    private String gameCode;
    /**
     * 游戏类别
     */
    private String gameClass;
    /**
     * 游戏名称
     */
    private String gameName;

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
     * 游戏种类
     */
    public String getGameKind()
    {
        return gameKind;
    }

    /**
     * 游戏种类
     */
    public void setGameKind(String gameKind)
    {
        this.gameKind = gameKind;
    }

    /**
     * 玩法编号
     */
    public String getGameCode()
    {
        return gameCode;
    }

    /**
     * 玩法编号
     */
    public void setGameCode(String gameCode)
    {
        this.gameCode = gameCode;
    }

    /**
     * 游戏类别
     */
    public String getGameClass()
    {
        return gameClass;
    }

    /**
     * 游戏类别
     */
    public void setGameClass(String gameClass)
    {
        this.gameClass = gameClass;
    }

    /**
     * 游戏名称
     */
    public String getGameName()
    {
        return gameName;
    }

    /**
     * 游戏名称
     */
    public void setGameName(String gameName)
    {
        this.gameName = gameName;
    }


    @Override
    public boolean equals(Object that)
    {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ZonesZoneGamePO other = (ZonesZoneGamePO) that;
        return (this.getSequenceId() == null ? other.getSequenceId() == null : this.getSequenceId().equals(other.getSequenceId()))
                && (this.getZoneNo() == null ? other.getZoneNo() == null : this.getZoneNo().equals(other.getZoneNo()))
                && (this.getGameKind() == null ? other.getGameKind() == null : this.getGameKind().equals(other.getGameKind()))
                && (this.getGameCode() == null ? other.getGameCode() == null : this.getGameCode().equals(other.getGameCode()))
                && (this.getGameClass() == null ? other.getGameClass() == null : this.getGameClass().equals(other.getGameClass()))
                && (this.getGameName() == null ? other.getGameName() == null : this.getGameName().equals(other.getGameName()));
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSequenceId() == null) ? 0 : getSequenceId().hashCode());
        result = prime * result + ((getZoneNo() == null) ? 0 : getZoneNo().hashCode());
        result = prime * result + ((getGameKind() == null) ? 0 : getGameKind().hashCode());
        result = prime * result + ((getGameCode() == null) ? 0 : getGameCode().hashCode());
        result = prime * result + ((getGameClass() == null) ? 0 : getGameClass().hashCode());
        result = prime * result + ((getGameName() == null) ? 0 : getGameName().hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", zoneNo=").append(zoneNo);
        sb.append(", gameKind=").append(gameKind);
        sb.append(", gameCode=").append(gameCode);
        sb.append(", gameClass=").append(gameClass);
        sb.append(", gameName=").append(gameName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}