package demo.sport.zones.convert;

import demo.sport.domain.theme.ThemeMessCmsOUT;
import demo.sport.domain.zones.ZonesMessInfoOUT;
import demo.sport.zones.domain.ZonesMessInfoPO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IZoneMessConvert
{


    List<ZonesMessInfoOUT> convertMessOUTS(List<ZonesMessInfoPO> records);

    List<ThemeMessCmsOUT> convert2ThemeCmsMessOUT(List<ZonesMessInfoPO> zonesMessInfoPOS);
}
