package demo.sport.zones.convert;

import com.hrms.core.constant.DatePattern;
import demo.sport.domain.theme.ThemeCmsGameInfoOut;
import demo.sport.domain.theme.ThemeExperCmsOUT;
import demo.sport.domain.theme.ThemeGameInfoOUT;
import demo.sport.zones.domain.PlaceZoneGamePO;
import demo.sport.zones.domain.ThemeGameInfoPO;
import demo.sport.zones.entity.ThemeGameInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Mapper(componentModel = "spring")
public interface IGameConvert
{

    @Mappings({
            @Mapping(target = "playMode", expression = "java(com.hrms.sport.constants.zones.PlayModeEnum.getPlayMode(themeGameInfoPO.getPlayMode()))"),
    })
    ThemeGameInfoBO convertGameInfoPO(ThemeGameInfoPO themeGameInfoPO);


    List<ThemeGameInfoBO> convertGameInfoPOS(List<ThemeGameInfoPO> themeGameInfoPO);


    ThemeGameInfoOUT convertGameInfoBO(ThemeGameInfoBO themeGameInfoBO);

    List<ThemeGameInfoOUT> convertGameInfoBOS(List<ThemeGameInfoBO> themeGameInfoBOS);

    @Mappings({
            @Mapping(target = "playMode", expression = "java(com.hrms.sport.constants.zones.PlayModeEnum.getPlayMode(themeGameInfoPO.getPlayMode()))"),
    })
    ThemeCmsGameInfoOut convertGameInfoOUT(ThemeGameInfoPO themeGameInfoPO);

    List<ThemeCmsGameInfoOut> convertGameInfoOUTS(List<ThemeGameInfoPO> themeGameInfoPOS);


    default String toDateStr(Date date)
    {
        if (Objects.isNull(date)) {
            return null;
        }
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        return dateTimeFormatter.format(date);
    }

    ThemeExperCmsOUT convertThemeExperCmsOUT(PlaceZoneGamePO placeZoneGamePO);
}
