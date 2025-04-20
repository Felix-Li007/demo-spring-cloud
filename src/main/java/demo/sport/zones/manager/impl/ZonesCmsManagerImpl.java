package demo.sport.zones.manager.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.core.domian.PageRequest;
import com.hrms.core.domian.PageResponse;
import com.hrms.core.domian.ResultOut;
import demo.sport.domain.cms.theme.ThemeAddIn;
import demo.sport.domain.cms.theme.ThemeZonePageIn;
import demo.sport.domain.zones.ZonesZoneInfoOUT;
import demo.sport.zones.convert.IZonesZoneConvert;
import demo.sport.zones.domain.PlaceThemeZonePO;
import demo.sport.zones.domain.ZonesZoneInfoPO;
import demo.sport.zones.manager.IZonesCmsManager;
import demo.sport.zones.mapper.IThemeZoneMapper;
import demo.sport.zones.mapper.IZoneInfoMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


@Component
public class ZonesCmsManagerImpl implements IZonesCmsManager
{

    @Resource
    private IZoneInfoMapper zonesZoneInfoMapper;

    @Resource
    private IZonesZoneConvert zonesZoneConvert;

    @Resource
    private IThemeZoneMapper themeZoneMapper;

    @Override
    public ZonesZoneInfoPO selectByZoneNo(String zoneNo)
    {
        return zonesZoneInfoMapper.selectZoneDetail(zoneNo);
    }


    @Override
    public int insertSelective(ZonesZoneInfoPO zonesZoneInfoPO)
    {
        return zonesZoneInfoMapper.insertSelective(zonesZoneInfoPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateZoneDelFlag(String zoneNo)
    {
        return zonesZoneInfoMapper.updateZoneDelFlag(zoneNo);
    }

    @Override
    public IPage<ZonesZoneInfoPO> selectParentZonePage(Page<ZonesZoneInfoPO> page, String zoneNo, String zoneName, String startTime, String endTime)
    {
        return zonesZoneInfoMapper.selectParentZoneList(page, zoneNo, zoneName, startTime, endTime);
    }

    @Override
    public List<ZonesZoneInfoPO> selectZoneChildList(String parentId)
    {
        return zonesZoneInfoMapper.selectChildZoneList(parentId);
    }

    @Override
    public List<ZonesZoneInfoPO> selectAllParentZoneList()
    {
        return zonesZoneInfoMapper.selectAllParentZoneList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateZoneState(String zoneNo, String zoneState)
    {
        zonesZoneInfoMapper.updateZoneState(zoneNo, zoneState);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateZoneInfo(ZonesZoneInfoPO po)
    {
        return zonesZoneInfoMapper.updateZoneInfo(po);
    }

    @Override
    public ResultOut<PageResponse<ZonesZoneInfoOUT>> queryZoneList(ThemeZonePageIn themeZonePageIn)
    {
        PageResponse<ZonesZoneInfoOUT> pageResponse = new PageResponse<>();
        int pageNum = Optional.ofNullable(themeZonePageIn).map(ThemeZonePageIn::getPageNum).orElse(1);
        int pageSize = Optional.ofNullable(themeZonePageIn).map(ThemeZonePageIn::getPageSize).orElse(PageRequest.PAGE_SIZE_MAX_LIMIT);
        pageResponse.setPageNum(pageNum);
        pageResponse.setPageSize(pageSize);
        int sequenceId = Optional.ofNullable(themeZonePageIn).map(ThemeZonePageIn::getSequenceId).orElse(-1);
        IPage<ZonesZoneInfoPO> page = zonesZoneInfoMapper.queryZoneList(new Page<ZonesZoneInfoPO>(pageNum, pageSize), sequenceId);
        pageResponse.setTotalNum((int) page.getTotal());
        List<ZonesZoneInfoPO> records = page.getRecords();
        List<ZonesZoneInfoOUT> zonesZoneInfoOUTS = zonesZoneConvert.convert2ZonesZoneOUTS(records);
        pageResponse.setRecords(zonesZoneInfoOUTS);
        return new ResultOut<PageResponse<ZonesZoneInfoOUT>>().buildSuccess(pageResponse);
    }

    @Override
    public void modifyZoneThemeRel(ThemeAddIn themeAddIn, String themeNo)
    {
        themeZoneMapper.deleteByThemeNo(themeNo);
        List<ThemeAddIn.ThemeZoneAddIn> zoneList = themeAddIn.getZoneList();

        zoneList.stream().forEach(data -> {
            PlaceThemeZonePO zonePO = zonesZoneConvert.convert2ThemeZoneRelList(data.getZoneNo(), themeNo);
            themeZoneMapper.insertSelective(zonePO);
        });
    }
}
