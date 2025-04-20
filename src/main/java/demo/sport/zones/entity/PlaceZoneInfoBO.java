package demo.sport.zones.entity;

import demo.sport.domain.zones.ZonesZoneBaseOUT;
import demo.sport.domain.zones.ZonesZoneDetailOUT;

import java.util.List;


public class PlaceZoneInfoBO extends ZonesZoneBaseOUT
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
