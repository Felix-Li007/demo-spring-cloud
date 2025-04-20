package demo.sport.zones.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import demo.sport.zones.domain.ZonesPfileInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Entity com.hrms.sport.zones.entity.po.PlacePfileInfoPO
 */
@Mapper
public interface IPFileInfoMapper
{

    Page<ZonesPfileInfoPO> paingPFileList(Page<ZonesPfileInfoPO> page, @Param("themeNo") String themeNo, @Param("teamNo") String teamNo, @Param("sequenceId") Integer sequenceId);

    List<ZonesPfileInfoPO> selectPFileList(@Param("accntNos") List<String> accntNos);

    int insertPfileDetail(ZonesPfileInfoPO zonesPfileInfoPO);

    int updatePfileDetail(ZonesPfileInfoPO zonesPfileInfoPO);

    int updateExperPoint(@Param("pFileNo") String pFileNo, @Param("experPoint") Integer experPoint, @Param("updateTime") Date updateTime);
}
