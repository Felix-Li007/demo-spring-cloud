package demo.sport.zones.mapper;


import demo.sport.zones.domain.PlaceCionInfoPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hrms.sport.zones.entity.po.PlaceCionInfoPO
 */
@Mapper
public interface ICionInfoMapper
{


    List<PlaceCionInfoPO> queryCionList();
}
