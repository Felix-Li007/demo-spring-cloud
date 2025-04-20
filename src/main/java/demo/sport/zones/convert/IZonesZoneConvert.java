package demo.sport.zones.convert;

import demo.sport.domain.theme.ThemeZoneCmsOUT;
import demo.sport.domain.zones.ThemeHeadInfoOUT;
import demo.sport.domain.zones.ZonesZoneDetailOUT;
import demo.sport.domain.zones.ZonesZoneInfoOUT;
import demo.sport.zones.domain.GameExperInfoPO;
import demo.sport.zones.domain.PlaceThemeZonePO;
import demo.sport.zones.domain.ThemeHeadInfoPO;
import demo.sport.zones.domain.ZonesZoneInfoPO;
import demo.sport.zones.entity.GameExperInfoBO;
import demo.sport.zones.entity.ThemeHeadInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IZonesZoneConvert
{


    @Named(value = "convert2ZonesZoneOUTS")
    List<ZonesZoneInfoOUT> convert2ZonesZoneOUTS(List<ZonesZoneInfoPO> records);


    /**
     * ZonesZoneInfoPO -> ZonesZoneOUT
     *
     * @param zonesZoneInfoPO
     * @return 响应
     */
    @Mapping(target = "openList", ignore = true)
    @Named(value = "convert2ZonesZoneOUT")
    ZonesZoneDetailOUT convert2ZonesZoneOUT(ZonesZoneInfoPO zonesZoneInfoPO);

    ThemeHeadInfoBO convertHeadInfoPO(ThemeHeadInfoPO themeHeadInfoPO);

    List<ThemeHeadInfoBO> convertHeadInfoPOS(List<ThemeHeadInfoPO> themeHeadInfoPOS);

    ThemeHeadInfoOUT convertHeadInfoBO(ThemeHeadInfoBO themeHeadInfoBO);

    List<ThemeHeadInfoOUT> convertHeadInfoBOS(List<ThemeHeadInfoBO> themeHeadInfoBOS);


    GameExperInfoBO convertExperInfoBO(GameExperInfoPO gameExperInfoPO);

    List<GameExperInfoBO> convertExperInfoBOS(List<GameExperInfoPO> gameExperInfoPOS);

    @Mapping(target = "zoneNo", source = "zoneNo")
    @Mapping(target = "zoneName", source = "zoneName")
    ThemeZoneCmsOUT convert2CmsZone(ZonesZoneInfoPO zonesZoneInfoPO);

    @Mapping(target = "zoneNo", source = "zoneNo")
    @Mapping(target = "themeNo", source = "themeNo")
    PlaceThemeZonePO convert2ThemeZoneRelList(String zoneNo, String themeNo);
}
