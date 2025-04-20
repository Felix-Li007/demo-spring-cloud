package demo.sport.zones.mapper;


import demo.sport.zones.domain.PlaceZoneGamePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.hrms.sport.zones.entity.po.PlaceZoneGamePO
 */
public interface IZoneGameMapper
{
    void deleteByThemeNo(@Param("themeNo") String themeNo);

    void insertSelective(PlaceZoneGamePO placeZoneGamePO);

    List<PlaceZoneGamePO> selectByThemeNo(@Param("themeNo") String themeNo);
}
