package demo.sport.zones.mapper;

import demo.sport.zones.domain.PlaceThemeZonePO;
import demo.sport.zones.domain.ThemeZoneInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IThemeZoneMapper
{

    List<ThemeZoneInfoPO> selectZoneList(@Param("themeNo") String themeNo, @Param("zoneNo") String zoneNo);

    void deleteByThemeNo(String themeNo);

    void insertSelective(PlaceThemeZonePO zonePO);
}
