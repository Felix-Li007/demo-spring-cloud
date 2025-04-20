package demo.sport.zones.mapper;


import demo.sport.zones.domain.ZonesZoneDatePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.hrms.sport.zones.entity.po.PlaceZoneDatePO
 */
@Mapper
public interface IZoneDateMapper
{


    List<ZonesZoneDatePO> selectZoneDatePOS(@Param("zoneNo") String zoneNo);

    int insertSelective(ZonesZoneDatePO date);

    int deleteDateByZoneNo(@Param("zoneNo") String zoneNo);
}
