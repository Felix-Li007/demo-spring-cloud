package demo.sport.zones.domain;

import java.util.List;

/**
 * 场馆基本数据
 *
 * @TableName t_place_zone_info
 */
public class ZonesZoneInfoPO extends ZonesZoneBasePO
{

    private static final long serialVersionUID = 1L;
    /**
     * 子场馆列表
     */
    List<ZonesZoneInfoPO> childZonesList;
    /**
     * 场馆编号
     */
    private String zoneNo;
    /**
     * 场馆名称
     */
    private String zoneName;
    /**
     * 场馆图标: 资源的URI地址（区别于URL，不包括主机地址）
     */
    private String zoneIcon;
    /**
     * 场馆容量
     */
    private Integer zoneSize;
    /**
     * 场馆说明
     */
    private String zoneDesc;
    /**
     * 00-待开放，10-开放中，20-已关闭
     */
    private String zoneState;
    /**
     * 资源链接
     */
    private String zoneStyle;
    /**
     * 入口地址
     */
    private String linkHref;
    /**
     * 限制次数
     */
    private Integer limitNum;
    /**
     * 父id
     */
//    private Integer parentId;

    private String parentId;


    public Integer getLimitNum()
    {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum)
    {
        this.limitNum = limitNum;
    }

    public String getLinkHref()
    {
        return linkHref;
    }

    public void setLinkHref(String linkHref)
    {
        this.linkHref = linkHref;
    }

    public String getParentId()
    {
        return parentId;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
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
     * 场馆名称
     */
    public String getZoneName()
    {
        return zoneName;
    }

    /**
     * 场馆名称
     */
    public void setZoneName(String zoneName)
    {
        this.zoneName = zoneName;
    }

    /**
     * 场馆图标: 资源的URI地址（区别于URL，不包括主机地址）
     */
    public String getZoneIcon()
    {
        return zoneIcon;
    }

    /**
     * 场馆图标: 资源的URI地址（区别于URL，不包括主机地址）
     */
    public void setZoneIcon(String zoneIcon)
    {
        this.zoneIcon = zoneIcon;
    }

    /**
     * 场馆容量
     */
    public Integer getZoneSize()
    {
        return zoneSize;
    }

    /**
     * 场馆容量
     */
    public void setZoneSize(Integer zoneSize)
    {
        this.zoneSize = zoneSize;
    }

    /**
     * 场馆说明
     */
    public String getZoneDesc()
    {
        return zoneDesc;
    }

    /**
     * 场馆说明
     */
    public void setZoneDesc(String zoneDesc)
    {
        this.zoneDesc = zoneDesc;
    }

    /**
     * 00-待开放，10-开放中，20-已关闭
     */
    public String getZoneState()
    {
        return zoneState;
    }

    /**
     * 00-待开放，10-开放中，20-已关闭
     */
    public void setZoneState(String zoneState)
    {
        this.zoneState = zoneState;
    }

    public String getZoneStyle()
    {
        return zoneStyle;
    }

    public void setZoneStyle(String zoneStyle)
    {
        this.zoneStyle = zoneStyle;
    }

//    public Integer getParentId() {
//        return parentId;
//    }
//
//    public void setParentId(Integer parentId) {
//        this.parentId = parentId;
//    }

    public List<ZonesZoneInfoPO> getChildZonesList()
    {
        return childZonesList;
    }

    public void setChildZonesList(List<ZonesZoneInfoPO> childZonesList)
    {
        this.childZonesList = childZonesList;
    }

}