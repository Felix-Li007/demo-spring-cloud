package demo.sport.zones.mapper;

import demo.sport.zones.domain.ZonesThemeCionPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hrms.sport.zones.entity.po.PlaceThemeCionPO
 */
@Mapper
public interface IThemeCionMapper
{


//    IPage<ZonesThemeCionPO> selectCionList(Page<ZonesThemeCionPO> zonesThemeMedalPOPage, @Param("themeNo")String themeNo, @Param("sequenceId")Integer sequenceId);

    List<ZonesThemeCionPO> selectCionList(String themeNo);

    void deleteByThemeNo(String themeNo);

    void insertSelective(ZonesThemeCionPO data);
}
