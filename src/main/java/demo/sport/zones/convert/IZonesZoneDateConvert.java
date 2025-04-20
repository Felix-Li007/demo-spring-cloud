package demo.sport.zones.convert;

import demo.sport.domain.zones.ZonesZoneDateOUT;
import demo.sport.zones.domain.ZonesZoneDatePO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IZonesZoneDateConvert
{


    List<ZonesZoneDateOUT> convert2ZoneDateOUTS(List<ZonesZoneDatePO> zonesZoneDatePOS);
}
