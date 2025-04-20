package demo.sport.zones.entity.bo;

import demo.sport.domain.zones.ZonesZoneBaseOUT;
import demo.sport.domain.zones.ZonesZoneDetailOUT;

import java.util.List;

/**
 * @author: houpengyi
 * @date: 2022/10/08 16:03
 * @description:
 */
public class ZonesZoneInfoCmsBO extends ZonesZoneBaseOUT
{
    private static final long serialVersionUID = 1L;
    private List<ZonesZoneDetailOUT> childZonesList;

    public List<ZonesZoneDetailOUT> getChildZonesList()
    {
        return childZonesList;
    }

    public void setChildZonesList(List<ZonesZoneDetailOUT> childZonesList)
    {
        this.childZonesList = childZonesList;
    }

}
