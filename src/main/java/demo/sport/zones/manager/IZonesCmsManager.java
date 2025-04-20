package demo.sport.zones.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.core.domian.PageResponse;
import com.hrms.core.domian.ResultOut;
import demo.sport.domain.cms.theme.ThemeAddIn;
import demo.sport.domain.cms.theme.ThemeZonePageIn;
import demo.sport.domain.zones.ZonesZoneInfoOUT;
import demo.sport.zones.domain.ZonesZoneInfoPO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 接口描述
 * @author: houpengyi
 * @since: 从XXX版本引入的接口
 */

public interface IZonesCmsManager
{

    /**
     * 查询场馆信息
     *
     * @return 查询场馆信息(包括开放时间)
     */
    @Transactional(readOnly = true)
    ZonesZoneInfoPO selectByZoneNo(String zoneNo);

    /**
     * 新增场馆基础信息
     *
     * @return 新增场馆基础信息
     */
    int insertSelective(ZonesZoneInfoPO zonesZoneInfoPO);

    /**
     * 删除场馆信息
     *
     * @return 删除场馆信息
     */
    int updateZoneDelFlag(String zoneNo);


    /**
     * 分页查询顶级场馆列表
     *
     * @return 分页查询顶级场馆列表
     */
    @Transactional(readOnly = true)
    IPage<ZonesZoneInfoPO> selectParentZonePage(Page<ZonesZoneInfoPO> zonesZoneInfoPOPage, String zoneNo, String zoneName, String startTime, String endTime);

    /**
     * 根据场馆主键id查询子场馆列表(全量)
     *
     * @return 分页查询顶级场馆列表
     */
    @Transactional(readOnly = true)
    List<ZonesZoneInfoPO> selectZoneChildList(String zoneNo);

    /**
     * 全量查询一级场馆列表
     *
     * @return 全量查询一级场馆列表
     */
    @Transactional(readOnly = true)
    List<ZonesZoneInfoPO> selectAllParentZoneList();

    /**
     * 修改场馆状态
     *
     * @return 修改场馆状态
     */
    void updateZoneState(String zoneNo, String zoneState);

    /**
     * 修改场馆详情
     *
     * @return 修改场馆详情
     */
    int updateZoneInfo(ZonesZoneInfoPO po);


    ResultOut<PageResponse<ZonesZoneInfoOUT>> queryZoneList(ThemeZonePageIn themeZonePageIn);

    void modifyZoneThemeRel(ThemeAddIn themeAddIn, String themeNo);
}
