package demo.sport.zones.domain;

import java.util.Date;

/**
 * 场馆用户列表
 *
 * @TableName t_place_zone_user
 */
public class ZonesZoneUserPO extends ZonesZoneBasePO
{


    private static final long serialVersionUID = 1L;
    /**
     * 唯一的UUID编号
     */
    private String zoneNo;
    /**
     * 用户编号
     */
    private String accountNo;
    /**
     * 加入时间
     */
    private Date enterTime;

    /**
     * 唯一的UUID编号
     */
    public String getZoneNo()
    {
        return zoneNo;
    }

    /**
     * 唯一的UUID编号
     */
    public void setZoneNo(String zoneNo)
    {
        this.zoneNo = zoneNo;
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
     * 加入时间
     */
    public Date getEnterTime()
    {
        return enterTime;
    }

    /**
     * 加入时间
     */
    public void setEnterTime(Date enterTime)
    {
        this.enterTime = enterTime;
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
        ZonesZoneUserPO other = (ZonesZoneUserPO) that;
        return (this.getSequenceId() == null ? other.getSequenceId() == null : this.getSequenceId().equals(other.getSequenceId()))
                && (this.getZoneNo() == null ? other.getZoneNo() == null : this.getZoneNo().equals(other.getZoneNo()))
                && (this.getAccountNo() == null ? other.getAccountNo() == null : this.getAccountNo().equals(other.getAccountNo()))
                && (this.getEnterTime() == null ? other.getEnterTime() == null : this.getEnterTime().equals(other.getEnterTime()));
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSequenceId() == null) ? 0 : getSequenceId().hashCode());
        result = prime * result + ((getZoneNo() == null) ? 0 : getZoneNo().hashCode());
        result = prime * result + ((getAccountNo() == null) ? 0 : getAccountNo().hashCode());
        result = prime * result + ((getEnterTime() == null) ? 0 : getEnterTime().hashCode());
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
        sb.append(", accountNo=").append(accountNo);
        sb.append(", enterTime=").append(enterTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}