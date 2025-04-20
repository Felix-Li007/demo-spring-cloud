package demo.sport.zones.convert;

import com.hrms.core.constant.DatePattern;
import demo.sport.domain.theme.PlaceThemeInfoOUT;
import demo.sport.domain.theme.ThemeDetailCmsOUT;
import demo.sport.domain.theme.ThemeMedalInfoOUT;
import demo.sport.zones.domain.PlaceThemeInfoPO;
import demo.sport.zones.entity.PlaceThemeInfoBO;
import demo.sport.zones.entity.ThemeMedalInfoBO;
import org.mapstruct.Mapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Mapper(componentModel = "spring")
public interface IThemeInfoConvert
{


    PlaceThemeInfoOUT convert2ThemeInfoOUT(PlaceThemeInfoPO zonesThemeInfoPO);
//
//    @Mapping(target = "openTime", expression = "java(toDateStr(data.getOpenTime()))")
//    @Mapping(target = "closeTime", expression = "java(toDateStr(data.getCloseTime()))")
//    @Mapping(target = "createTime", expression = "java(toDateStr(data.getCreateTime()))")
//    @Mapping(target = "updateTime", expression = "java(toDateStr(data.getUpdateTime()))")
//    PlaceThemeInfoOUT zonesThemePOS2ThemeDetailOUT(PlaceThemeInfoPO data);


    default String toDateStr(Date date)
    {
        if (Objects.isNull(date)) {
            return null;
        }
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        return dateTimeFormatter.format(date);
    }

    ThemeDetailCmsOUT convert2ThemeCmsInfo(PlaceThemeInfoPO zonesThemeInfoPO);

    PlaceThemeInfoBO convertThemeInfoPO(PlaceThemeInfoPO placeThemeInfoPO);

    List<PlaceThemeInfoBO> convertThemeInfoPOS(List<PlaceThemeInfoPO> placeThemeInfoPOS);

    PlaceThemeInfoOUT convertThemeInfoBO(PlaceThemeInfoBO placeThemeInfoBO);

    List<ThemeMedalInfoOUT> convertMedalInfoOUTS(List<ThemeMedalInfoBO> themeMedalInfoBOS);

}
