package demo.sport.zones.mapper;

import demo.sport.zones.domain.ThemeGameInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Freddy Li
 * @description 针对表【T_PLACE_GAME_INFO(经验基础数据)】的数据库操作Mapper
 * @createDate 2022-10-15 11:19:52
 * @Entity generator.domain.TPlaceGameInfo
 */
@Mapper
public interface IGameInfoMapper
{
    List<ThemeGameInfoPO> selectGameList();

    ThemeGameInfoPO selectGameInfoByPlayCode(@Param("playCode") String playCode);


}




