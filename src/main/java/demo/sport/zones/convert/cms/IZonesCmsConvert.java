package demo.sport.zones.convert.cms;

import demo.sport.domain.cms.ZoneAddIN;
import demo.sport.domain.cms.ZoneCmsOUT;
import demo.sport.domain.cms.ZoneEditIN;
import demo.sport.domain.zones.ZonesZoneDetailOUT;
import demo.sport.zones.domain.ZonesZoneInfoPO;
import demo.sport.zones.entity.PlaceZoneInfoBO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @description: 接口描述
 * @author: houpengyi
 * @since: 从XXX版本引入的接口
 */
@Mapper(componentModel = "spring")
public interface IZonesCmsConvert
{

    /**
     * ZonesZoneInfoPO -> ZonesZoneCmsOUT
     *
     * @param zonesZoneInfoPO
     * @return 响应
     */
    ZoneCmsOUT convert2ZonesDetail(ZonesZoneInfoPO zonesZoneInfoPO);

    /**
     * ZonesZoneAddIn -> ZonesZoneDatePO
     *
     * @param zonesZoneAddIn
     * @return 响应
     */
    ZonesZoneInfoPO convert2ZonesZonePO(ZoneAddIN zonesZoneAddIn);

    /**
     * ZonesZoneInfoPO -> ZonesZoneInfoCmsBO
     *
     * @param parentList
     * @return 响应
     */
    List<PlaceZoneInfoBO> convert2ZoneBOS(List<ZonesZoneInfoPO> parentList);

    /**
     * ZonesZoneInfoCmsBO -> ZonesZoneCmsOUT
     *
     * @param zoneCmsBOList
     * @return 响应
     */
    List<ZoneCmsOUT> convert2ZoneOUTS(List<PlaceZoneInfoBO> zoneCmsBOList);

    /**
     * ZonesZoneInfoPO -> ZonesZoneCmsOUT
     *
     * @param zoneInfoPOS
     * @return 响应
     */
    List<ZoneCmsOUT> convert2ZonesOUTS(List<ZonesZoneInfoPO> zoneInfoPOS);

    /**
     * ZoneEditIN -> ZonesZoneInfoPO
     *
     * @param zoneEditIN
     * @return 响应
     */
    ZonesZoneInfoPO convertEditIn2ZonePO(ZoneEditIN zoneEditIN);

    List<ZonesZoneDetailOUT> convert2ZoneDetailOUTS(List<PlaceZoneInfoBO> zoneInfoCmsBOS);

    PlaceZoneInfoBO convert2ZoneBO(ZonesZoneInfoPO zonePo);
}
