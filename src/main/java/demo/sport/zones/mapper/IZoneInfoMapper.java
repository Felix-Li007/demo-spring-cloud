package demo.sport.zones.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import demo.sport.zones.domain.ZonesZoneInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Entity com.hrms.sport.zones.entity.po.PlaceZoneInfoPO
 */
@Mapper
public interface IZoneInfoMapper
{


    IPage<ZonesZoneInfoPO> selectZoneList(IPage<ZonesZoneInfoPO> page, @Param("themeNo") String themeNo);

    ZonesZoneInfoPO selectZoneDetail(@Param("zoneNo") String zoneNo);

    int insertSelective(ZonesZoneInfoPO zonesZoneInfoPO);

    List<ZonesZoneInfoPO> selectChildZoneList(@Param("parentId") String parentId);

    int updateZoneDelFlag(@Param("zoneNo") String zoneNo);

    IPage<ZonesZoneInfoPO> selectParentZoneList(Page<ZonesZoneInfoPO> page, @Param("zoneNo") String zoneNo, @Param("zoneName") String zoneName,
                                                @Param("startTime") String startTime, @Param("endTime") String endTime);

    List<ZonesZoneInfoPO> selectAllParentZoneList();

    void updateZoneState(@Param("zoneNo") String zoneNo, @Param("zoneState") String zoneState);

    int updateZoneInfo(ZonesZoneInfoPO po);

    IPage<ZonesZoneInfoPO> queryZoneList(Page<ZonesZoneInfoPO> zonesZoneInfoPOPage, @Param("sequenceId") Integer sequenceId);

    /**
     * 分页查询场馆信息
     *
     * @param page    分页查询数据
     * @param nowTime 查询时间
     * @return 场馆信息
     */
    IPage<ZonesZoneInfoPO> pageListZoneInfo(Page<ZonesZoneInfoPO> page, @Param("nowTime") LocalDateTime nowTime);

//    /**
//     * 批量更新场馆状态
//     *
//     * @param updateList 需要更新的列表
//     * @param zoneStatus     状态
//     */
//    void batchUpdateZoneStatus(@Param("updateList") List<ZonesZoneInfoPO> updateList, @Param("zoneStatus") String zoneStatus);
}
