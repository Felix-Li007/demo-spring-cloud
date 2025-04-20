package demo.sport.zones.mapper;

import demo.sport.zones.domain.PlayExperInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface IPlayExperMapper
{

    int insertExperInfo(@Param("experInfo") PlayExperInfoPO profileExperInfoPO);

    PlayExperInfoPO selectExperInfo(@Param("themeNo") String themeNo, @Param("accntNo") String accntNo, @Param("playMode") String playMode);

    int updateExperInfo(@Param("sequenceId") Integer sequenceId, @Param("experPoint") Integer experPoint, @Param("updateTime") LocalDateTime updateTime);

}
