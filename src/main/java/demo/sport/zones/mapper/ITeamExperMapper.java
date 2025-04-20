package demo.sport.zones.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import demo.sport.zones.domain.TeamExperInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ITeamExperMapper
{

    int insertExperInfo(@Param("experInfo") TeamExperInfoPO teamExperInfoPO);

    TeamExperInfoPO selectExperInfo(@Param("themeNo") String themeNo, @Param("teamNo") String teamNo);

    int updateExperInfo(TeamExperInfoPO teamExperInfoPO);

    IPage<TeamExperInfoPO> selectExperList(Page<TeamExperInfoPO> page, @Param("themeNo") String themeNo);

}
