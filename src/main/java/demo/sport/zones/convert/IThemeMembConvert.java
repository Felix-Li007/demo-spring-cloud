package demo.sport.zones.convert;

import demo.sport.domain.zones.ThemeMembeInfoOUT;
import demo.sport.zones.domain.ThemeZoneInfoPO;
import demo.sport.zones.domain.ZonesThemeMembPO;
import demo.sport.zones.entity.ThemeZoneInfoBO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IThemeMembConvert
{


    ThemeZoneInfoBO convertZoneInfoPO(ThemeZoneInfoPO themeZoneInfoPO);

    List<ThemeZoneInfoBO> convertZoneInfoPOS(List<ThemeZoneInfoPO> themeZoneInfoPOS);

    ThemeMembeInfoOUT convertThemeMembPO(ZonesThemeMembPO zonesThemeMembPO);

    List<ThemeMembeInfoOUT> convertThemeMembPOS(List<ZonesThemeMembPO> zonesThemeMembPOS);
}
