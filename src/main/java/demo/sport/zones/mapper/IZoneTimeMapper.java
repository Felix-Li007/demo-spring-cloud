package demo.sport.zones.mapper;


import demo.sport.zones.domain.ZonesZoneTimePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.hrms.sport.zones.entity.po.PlaceZoneTimePO
 */
@Mapper
public interface IZoneTimeMapper
{


    List<ZonesZoneTimePO> selectAllTimePOList(@Param("dateNo") String dateNo);


    int insertSelective(ZonesZoneTimePO time);
}
