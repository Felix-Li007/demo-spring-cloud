package demo.sport.zones.manager.impl;

import demo.sport.zones.domain.ZonesZoneTimePO;
import demo.sport.zones.manager.ITimeCmsManager;
import demo.sport.zones.mapper.IZoneTimeMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: houpengyi
 * @date: 2022/10/08 18:03
 * @description:
 */
@Component
public class TimeCmsManagerImpl implements ITimeCmsManager
{

    @Resource
    private IZoneTimeMapper zonesZoneTimeMapper;

    @Override
    public List<ZonesZoneTimePO> selectZonesTimeList(String dateNo)
    {
        return zonesZoneTimeMapper.selectAllTimePOList(dateNo);
    }

    @Override
    public int insertSelective(ZonesZoneTimePO time)
    {
        return zonesZoneTimeMapper.insertSelective(time);
    }
}
