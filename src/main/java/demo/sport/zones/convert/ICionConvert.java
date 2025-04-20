package demo.sport.zones.convert;

import demo.sport.domain.cms.theme.ThemeAddIn;
import demo.sport.domain.theme.ThemeCionInfoOUT;
import demo.sport.zones.domain.CionLimitInfoPO;
import demo.sport.zones.domain.PlaceCionInfoPO;
import demo.sport.zones.domain.ZonesThemeCionPO;
import demo.sport.zones.entity.PlaceCionInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author: houpengyi
 * @date: 2022/10/17 21:45
 * @description:
 */
@Mapper(componentModel = "spring")
public interface ICionConvert
{

    PlaceCionInfoBO convertCionInfoPO(PlaceCionInfoPO placeCionInfoPO);

    List<PlaceCionInfoBO> convertCionInfoPOS(List<PlaceCionInfoPO> placeCionInfoPOS);

    List<ThemeCionInfoOUT> convertCionInfoBOS(List<PlaceCionInfoBO> placeCionInfoBOS);

    @Mapping(target = "themeNo", source = "themeNo")
    @Mapping(target = "cionCode", source = "data.cionCode")
    @Mapping(target = "initiValue", source = "data.initiValue")
    ZonesThemeCionPO convert2ThemeCionRelList(ThemeAddIn.ThemeCionAddIn data, String themeNo);

    @Mapping(target = "themeNo", source = "themeNo")
    @Mapping(target = "cionCode", source = "limitPO.cionCode")
    @Mapping(target = "limitValue", source = "limitPO.limitValue")
    @Mapping(target = "valueMode", source = "limitPO.valueMode")
    CionLimitInfoPO convert2ThemeCionLimit(ThemeAddIn.ThemeCionAddIn limitPO, String themeNo);
}
