package demo.sport.zones.manager.impl;

import com.hrms.frame.cache.HrmsCacheUtil;
import demo.sport.domain.cms.theme.ThemeAddIn;
import demo.sport.zones.common.constant.RedisCacheEnum;
import demo.sport.zones.convert.IZonesGameConvert;
import demo.sport.zones.domain.PlaceZoneGamePO;
import demo.sport.zones.domain.ThemeGameInfoPO;
import demo.sport.zones.manager.IExperCmsManager;
import demo.sport.zones.mapper.IGameInfoMapper;
import demo.sport.zones.mapper.IZoneGameMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author: houpengyi
 * @date: 2022/10/0 18:03
 * @description:
 */
@Component
public class IExperCmsManagerImpl implements IExperCmsManager
{

    @Resource
    private IZoneGameMapper placeZoneGameMapper;

    @Resource
    private IGameInfoMapper gameInfoMapper;

    @Resource
    private IZonesGameConvert zonesGameConvert;


    @Override
    public void modifyExperThemeRel(ThemeAddIn themeAddIn, String themeNo)
    {

        List<ThemeAddIn.ThemeExperAddIn> experList = themeAddIn.getExperList();
        placeZoneGameMapper.deleteByThemeNo(themeNo);
        if (experList.size() == 1) {
            ThemeAddIn.ThemeExperAddIn themeExperAddIn = experList.get(0);
            Integer experPoint = themeExperAddIn.getExperPoint();
            if (Objects.isNull(themeExperAddIn.getPlayCode())) {
                List<ThemeGameInfoPO> themeGameInfoPOS = gameInfoMapper.selectGameList();
                String zoneNo = themeExperAddIn.getZoneNo();
                List<String> zoneNoStrList = Arrays.asList(zoneNo.split(","));
                zoneNoStrList.stream().forEach(zoneStr -> {
                    themeGameInfoPOS.stream().forEach(gamePo -> {
                        PlaceZoneGamePO cionPO = zonesGameConvert.convertGamePo2ThemeExperRel(gamePo, zoneStr, themeNo, experPoint);
                        placeZoneGameMapper.insertSelective(cionPO);
                    });
                });
            }
        } else {

            experList.stream().forEach(data -> {
                String zoneNo = data.getZoneNo();
                List<String> zoneNoStrList = Arrays.asList(zoneNo.split(","));
                zoneNoStrList.stream().forEach(zoneStr -> {
                    Map<String, Object> mapKeyParam = new HashMap<>();
                    mapKeyParam.put("themeNo", themeNo);
                    mapKeyParam.put("zoneNo", zoneStr);
                    HrmsCacheUtil.cacheEvict(RedisCacheEnum.CACHE_KEY_ZONE_GAME_EXPER, mapKeyParam);
                    PlaceZoneGamePO cionPO = zonesGameConvert.convert2ThemeExperRelList(data, themeNo);
                    cionPO.setZoneNo(zoneStr);
                    placeZoneGameMapper.insertSelective(cionPO);
                });
            });
        }
    }
}
