package demo.sport.zones.mapper;

import demo.sport.zones.domain.PlaceExperFlowPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IExperFlowMapper
{

    int insertExperFlow(@Param("experFlow") PlaceExperFlowPO placeExperFlowPO);

    PlaceExperFlowPO selectByZoneNo(@Param("zoneNo") String zoneNo);

    List<PlaceExperFlowPO> selectExperFlow(@Param("accntNo") String accntNo, @Param("experNos") List<String> experNos);

}
