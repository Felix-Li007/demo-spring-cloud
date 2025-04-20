package demo.sport.zones.convert;

import demo.sport.domain.cms.theme.ThemeAddIn;
import demo.sport.zones.domain.PlaceZoneGamePO;
import demo.sport.zones.domain.ThemeGameInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface IZonesGameConvert
{

    @Mapping(target = "zoneNo", source = "data.zoneNo")
    @Mapping(target = "experPoint", source = "data.experPoint")
    @Mapping(target = "playCode", source = "data.playCode")
    @Mapping(target = "themeNo", source = "themeNo")
    PlaceZoneGamePO convert2ThemeExperRelList(ThemeAddIn.ThemeExperAddIn data, String themeNo);

    @Mapping(target = "zoneNo", source = "zoneStr")
    @Mapping(target = "experPoint", source = "experPoint")
    @Mapping(target = "playCode", source = "gamePo.playCode")
    @Mapping(target = "themeNo", source = "themeNo")
    PlaceZoneGamePO convertGamePo2ThemeExperRel(ThemeGameInfoPO gamePo, String zoneStr, String themeNo, Integer experPoint);
}
