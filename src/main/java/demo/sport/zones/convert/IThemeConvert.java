package demo.sport.zones.convert;

import com.hrms.core.constant.DatePattern;
import demo.sport.domain.cms.theme.ThemeAddIn;
import demo.sport.domain.theme.PlaceThemeInfoOUT;
import demo.sport.domain.zones.ZoneThemeInfoOUT;
import demo.sport.zones.domain.PlaceThemeInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: houpengyi
 * @date: 2022/10/17 21:45
 * @description:
 */
@Mapper(componentModel = "spring")
public interface IThemeConvert
{


    PlaceThemeInfoOUT zonesThemeInfoOut2ThemeDeatailOut(ZoneThemeInfoOUT zonesThemeInfoOUT);

    @Mapping(target = "openTime", expression = "java(toDate(themeAddIn.getOpenTime()))")
    @Mapping(target = "closeTime", expression = "java(toDate(themeAddIn.getCloseTime()))")
    PlaceThemeInfoPO convertAddIn2PO(ThemeAddIn themeAddIn);

    default Date toDate(String dateTime)
    {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        Date date = new Date();
        try {
            date = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
