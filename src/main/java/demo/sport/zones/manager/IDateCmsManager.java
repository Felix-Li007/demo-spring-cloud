package demo.sport.zones.manager;

import demo.sport.zones.domain.ZonesZoneDatePO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 接口描述
 * @author: houpengyi
 * @since: 从XXX版本引入的接口
 */

public interface IDateCmsManager
{

    /**
     * 查询场馆开放时间
     *
     * @return 查询场馆开放时间
     */
    @Transactional(readOnly = true)
    List<ZonesZoneDatePO> selectZonesDateList(String zoneNo);

    /**
     * 插入场馆开放时间
     *
     * @return 插入场馆开放时间
     */
    int insertSelective(ZonesZoneDatePO date);

    /**
     * 根据场馆编号删除开放日期信息
     *
     * @return 插入场馆开放时间
     */
    int deleteDateByZoneNo(String zoneNo);
}
