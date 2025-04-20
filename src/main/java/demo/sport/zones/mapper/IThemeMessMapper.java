package demo.sport.zones.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import demo.sport.zones.domain.ZonesMessInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.hrms.sport.zones.entity.po.PlaceMessInfoPO
 */
@Mapper
public interface IThemeMessMapper
{


    Page<ZonesMessInfoPO> selectMessPOS(Page<ZonesMessInfoPO> zonesThemePageINPage, @Param("themeNo") String themeNo, @Param("sequenceId") Integer sequenceId);

    List<ZonesMessInfoPO> selectMessAllList(@Param("themeNo") String themeNo);

    void deleteByThemeNo(@Param("themeNo") String themeNo);

    void insertSelective(ZonesMessInfoPO zonesMessInfoPO);
}
