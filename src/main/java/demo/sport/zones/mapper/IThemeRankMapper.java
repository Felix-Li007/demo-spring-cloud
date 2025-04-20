package demo.sport.zones.mapper;

import demo.sport.zones.domain.ZonesThemeRankPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.hrms.sport.zones.entity.po.PlaceThemeRankPO
 */
@Mapper
public interface IThemeRankMapper
{


    List<ZonesThemeRankPO> selectRankList(@Param("themeNo") String themeNo);


    void deleteByThemeNo(String themeNo);

    void insertSelective(ZonesThemeRankPO data);
}
