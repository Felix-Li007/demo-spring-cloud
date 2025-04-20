package demo.sport.zones.manager.impl;

import demo.sport.zones.domain.ZonesZoneDatePO;
import demo.sport.zones.manager.IDateCmsManager;
import demo.sport.zones.mapper.IZoneDateMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: houpengyi
 * @date: 2022/10/0 18:03
 * @description:
 */
@Component
public class DateCmsManagerImpl implements IDateCmsManager
{

    @Resource
    private IZoneDateMapper zonesZoneDateMapper;

    @Override
    public List<ZonesZoneDatePO> selectZonesDateList(String zoneNo)
    {
        return zonesZoneDateMapper.selectZoneDatePOS(zoneNo);
    }

    @Override
    public int insertSelective(ZonesZoneDatePO date)
    {
        return zonesZoneDateMapper.insertSelective(date);
    }

    @Override
    public int deleteDateByZoneNo(String zoneNo)
    {
        return zonesZoneDateMapper.deleteDateByZoneNo(zoneNo);
    }
}
