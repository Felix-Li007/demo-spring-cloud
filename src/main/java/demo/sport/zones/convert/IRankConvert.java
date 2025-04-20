package demo.sport.zones.convert;

import demo.sport.domain.cms.theme.ThemeAddIn;
import demo.sport.domain.theme.ThemeRankCmsOUT;
import demo.sport.zones.domain.ZonesThemeRankPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author: houpengyi
 * @date: 2022/10/17 21:45
 * @description:
 */
@Mapper(componentModel = "spring")
public interface IRankConvert
{


    @Mapping(target = "themeNo", source = "themeNo")
    @Mapping(target = "rankCode", source = "data.rankCode")
    ZonesThemeRankPO convert2ThemeRankRelList(ThemeAddIn.ThemeRankAddIn data, String themeNo);

    List<ThemeRankCmsOUT> convert2ThemeRankCmsOUTS(List<ZonesThemeRankPO> zonesThemeRankPOS);
}
