package demo.sport.zones.convert.cms;

import demo.sport.domain.cms.ZoneDateAddIN;
import demo.sport.domain.cms.ZonesDateCmsOUT;
import demo.sport.zones.domain.ZonesZoneDatePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @description: 接口描述
 * @author: houpengyi
 * @since: 从XXX版本引入的接口
 */
@Mapper(componentModel = "spring")
public interface IDateCmsConvert
{

    /**
     * ZonesZoneDatePO -> ZonesZoneCmsDateOUT
     *
     * @param zonesZoneDatePO
     * @return 响应
     */
    @Mapping(target = "closeTime", ignore = true)
    @Mapping(target = "openTime", ignore = true)
    ZonesDateCmsOUT convert2ZonesCmsDateOUT(ZonesZoneDatePO zonesZoneDatePO);

    /**
     * ZoneDateAddIN -> ZonesZoneDatePO
     *
     * @param zoneDateAddIN
     * @return 响应
     */
//     @Mapping(target = "closeTime", ignore = true)
//     @Mapping(target = "openTime", ignore = true)
    ZonesZoneDatePO convert2DatePO(ZoneDateAddIN zoneDateAddIN);
}
