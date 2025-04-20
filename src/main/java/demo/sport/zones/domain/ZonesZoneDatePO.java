package demo.sport.zones.domain;


import java.util.Date;

/**
 * 场馆开放日期
 *
 * @TableName t_place_zone_date
 */
public class ZonesZoneDatePO extends ZonesZoneBasePO
{


    private static final long serialVersionUID = 1L;
    /**
     * 日期编号
     */
    private String dateNo;
    /**
     * 开放时间
     */


    private Date openTime;
    /**
     * 关闭时间
     */

    private Date closeTime;
    /**
     * 场馆编号
     */
    private String zoneNo;

    /**
     * 日期编号
     */
    public String getDateNo()
    {
        return dateNo;
    }

    /**
     * 日期编号
     */
    public void setDateNo(String dateNo)
    {
        this.dateNo = dateNo;
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


    /**
     * 大厅编号
     */
    public String getZoneNo()
    {
        return zoneNo;
    }

    /**
     * 大厅编号
     */
    public void setZoneNo(String zoneNo)
    {
        this.zoneNo = zoneNo;
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
        ZonesZoneDatePO other = (ZonesZoneDatePO) that;
        return (this.getSequenceId() == null ? other.getSequenceId() == null : this.getSequenceId().equals(other.getSequenceId()))
                && (this.getDateNo() == null ? other.getDateNo() == null : this.getDateNo().equals(other.getDateNo()))
                && (this.getOpenTime() == null ? other.getOpenTime() == null : this.getOpenTime().equals(other.getOpenTime()))
                && (this.getCloseTime() == null ? other.getCloseTime() == null : this.getCloseTime().equals(other.getCloseTime()))
                && (this.getZoneNo() == null ? other.getZoneNo() == null : this.getZoneNo().equals(other.getZoneNo()));
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSequenceId() == null) ? 0 : getSequenceId().hashCode());
        result = prime * result + ((getDateNo() == null) ? 0 : getDateNo().hashCode());
        result = prime * result + ((getOpenTime() == null) ? 0 : getOpenTime().hashCode());
        result = prime * result + ((getCloseTime() == null) ? 0 : getCloseTime().hashCode());
        result = prime * result + ((getZoneNo() == null) ? 0 : getZoneNo().hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dateNo=").append(dateNo);
        sb.append(", openTime=").append(openTime);
        sb.append(", closeTime=").append(closeTime);
        sb.append(", zoneNo=").append(zoneNo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}