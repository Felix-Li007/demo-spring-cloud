package demo.sport.zones.manager;

import demo.sport.zones.domain.ZonesZoneTimePO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 接口描述
 * @author: houpengyi
 * @since: 从XXX版本引入的接口
 */

public interface ITimeCmsManager
{

    /**
     * 查询场馆开放周时间信息
     *
     * @return 查询场馆开放周时间信息
     */
    @Transactional(readOnly = true)
    List<ZonesZoneTimePO> selectZonesTimeList(String dateNo);

    /**
     * 插入场馆开放周时间信息
     *
     * @return 插入场馆开放周时间信息
     */
    int insertSelective(ZonesZoneTimePO time);
}
