package demo.sport.zones.convert;

import demo.sport.domain.theme.ThemeMedalInfoOUT;
import demo.sport.domain.zones.ThemeRankInfoOUT;
import demo.sport.zones.domain.RankGameInfoPO;
import demo.sport.zones.domain.ZonesThemeRankPO;
import demo.sport.zones.entity.RankGameInfoBO;
import demo.sport.zones.entity.ThemeMedalInfoBO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IZonesRankConvert
{


    List<ThemeRankInfoOUT> convert2ZonesRankOUTS(List<ZonesThemeRankPO> zonesThemeRankPOS);

    RankGameInfoBO convertGameInfoPO(RankGameInfoPO rankGameInfoPO);

    List<RankGameInfoBO> convertGameInfoPOS(List<RankGameInfoPO> rankGameInfoPOS);

    List<ThemeMedalInfoOUT> convertMedalInfoOUTS(List<ThemeMedalInfoBO> themeMedalInfoBOS);
}
