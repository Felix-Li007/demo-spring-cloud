package demo.sport.zones.mapper;

import demo.sport.zones.domain.GameExperInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Freddy Li
 * @description 针对表【T_PLACE_ZONE_GAME(经验基础数据)】的数据库操作Mapper
 * @createDate 2022-10-15 11:19:52
 * @Entity generator.domain.TPlaceZoneGame
 */
@Mapper
public interface IGameExperMapper
{

    List<GameExperInfoPO> selectExperList(@Param("themeNo") String themeNo, @Param("zoneNo") String zoneNo);

}




