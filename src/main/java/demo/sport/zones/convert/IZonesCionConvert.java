package demo.sport.zones.convert;

import demo.sport.domain.theme.ThemeCionCmsOUT;
import demo.sport.domain.zones.CionLimitInfoOUT;
import demo.sport.domain.zones.ZonesCionInfoOUT;
import demo.sport.zones.domain.CionLimitInfoPO;
import demo.sport.zones.domain.ZonesThemeCionPO;
import demo.sport.zones.entity.CionLimitInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IZonesCionConvert
{

    @Mappings({
            //转化道具状态枚举值
    })
    ZonesCionInfoOUT convertThemeCionPO(ZonesThemeCionPO zonesThemeCionPO);

    List<ZonesCionInfoOUT> convertThemeCionPOS(List<ZonesThemeCionPO> zonesThemeCionPOS);

    @Mappings({
            @Mapping(target = "timeUnit", expression = "java(com.hrms.sport.constants.TimeUnitEnum.getTimeUnit(cionLimitInfoPO.getTimeUnit()))"),
            @Mapping(target = "valueMode", expression = "java(com.hrms.sport.constants.zones.ValueModeEnum.getValueMode(cionLimitInfoPO.getValueMode()))")
    })
    CionLimitInfoBO convertLimitInfoPO(CionLimitInfoPO cionLimitInfoPO);

    List<CionLimitInfoBO> convertLimitInfoPOS(List<CionLimitInfoPO> cionLimitInfoPOS);

    CionLimitInfoOUT converLimitInfoBO(CionLimitInfoBO cionLimitInfoBO);

    List<CionLimitInfoOUT> convertLimitInfoBOS(List<CionLimitInfoBO> cionLimitInfoBOS);


    @Mapping(target = "cionCode", source = "cionLimit.cionCode")
    @Mapping(target = "cionName", source = "themeCionPO.cionName")
    @Mapping(target = "limitValue", source = "cionLimit.limitValue")
    @Mapping(target = "valueMode", source = "cionLimit.valueMode")
    @Mapping(target = "initiValue", source = "themeCionPO.initiValue")
    ThemeCionCmsOUT convertThemeCmsCionOUT(CionLimitInfoPO cionLimit, ZonesThemeCionPO themeCionPO);
}
