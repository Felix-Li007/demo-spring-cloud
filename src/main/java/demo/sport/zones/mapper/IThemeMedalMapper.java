package demo.sport.zones.mapper;

import demo.sport.zones.domain.ThemeMedalInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.hrms.sport.zones.entity.po.PlaceThemeMedalPO
 */
@Mapper
public interface IThemeMedalMapper
{


//    Page<ZonesThemeMedalPO> selectMedalList(Page<ZonesThemeMedalPO> zonesThemeMedalPOPage, @Param("themeNo") String themeNo, @Param("sequenceId") Integer sequenceId);

    List<ThemeMedalInfoPO> selectMedalList(@Param("themeNo") String themeNo);
}
