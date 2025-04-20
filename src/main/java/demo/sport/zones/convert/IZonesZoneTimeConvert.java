package demo.sport.zones.convert;

import com.hrms.core.constant.DatePattern;
import demo.sport.domain.zones.ZonesZoneTimeOUT;
import demo.sport.zones.domain.ZonesZoneTimePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;


@Mapper(componentModel = "spring")
public interface IZonesZoneTimeConvert
{


    List<ZonesZoneTimeOUT> conver2ZoneTimeOUTS(List<ZonesZoneTimePO> zonesZoneTimePOS);

    @Mapping(target = "startTime", expression = "java(toTimeStr(time.getStartTime()))")
    @Mapping(target = "closeTime", expression = "java(toTimeStr(time.getCloseTime()))")
    ZonesZoneTimeOUT conver2ZoneTimeOUT(ZonesZoneTimePO time);

    default String toTimeStr(LocalTime time)
    {
        if (Objects.isNull(time)) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN);
        return dateTimeFormatter.format(time);
    }
}
