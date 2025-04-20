package demo.sport.zones.domain;

import java.time.LocalTime;

/**
 * 场馆开放时段
 *
 * @TableName t_place_zone_time
 */
public class ZonesZoneTimePO extends ZonesZoneBasePO
{

    private static final long serialVersionUID = 1L;
    /**
     * 日期编号
     */
    private String dateNo;
    /**
     * 开放周数，00-周日，01-周一，02-周二，03-周三，04-周四，05-周五，06-周六
     */
    private String weekCode;
    /**
     * 时段开始
     */
    private LocalTime startTime;
    /**
     * 时段结束
     */
    private LocalTime closeTime;

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
     * 开放周数，00-周日，01-周一，02-周二，03-周三，04-周四，05-周五，06-周六
     */
    public String getWeekCode()
    {
        return weekCode;
    }

    /**
     * 开放周数，00-周日，01-周一，02-周二，03-周三，04-周四，05-周五，06-周六
     */
    public void setWeekCode(String weekCode)
    {
        this.weekCode = weekCode;
    }

    public LocalTime getStartTime()
    {
        return startTime;
    }

    public void setStartTime(LocalTime startTime)
    {
        this.startTime = startTime;
    }

    public LocalTime getCloseTime()
    {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime)
    {
        this.closeTime = closeTime;
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
        ZonesZoneTimePO other = (ZonesZoneTimePO) that;
        return (this.getSequenceId() == null ? other.getSequenceId() == null : this.getSequenceId().equals(other.getSequenceId()))
                && (this.getDateNo() == null ? other.getDateNo() == null : this.getDateNo().equals(other.getDateNo()))
                && (this.getWeekCode() == null ? other.getWeekCode() == null : this.getWeekCode().equals(other.getWeekCode()))
                && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
                && (this.getCloseTime() == null ? other.getCloseTime() == null : this.getCloseTime().equals(other.getCloseTime()));
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSequenceId() == null) ? 0 : getSequenceId().hashCode());
        result = prime * result + ((getDateNo() == null) ? 0 : getDateNo().hashCode());
        result = prime * result + ((getWeekCode() == null) ? 0 : getWeekCode().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getCloseTime() == null) ? 0 : getCloseTime().hashCode());
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
        sb.append(", dateWeek=").append(weekCode);
        sb.append(", startTime=").append(startTime);
        sb.append(", closeTime=").append(closeTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}