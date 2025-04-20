package demo.sport.zones.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.core.domian.PageRequest;
import com.hrms.core.domian.PageResponse;
import com.hrms.core.exception.HrmsException;
import com.hrms.frame.cache.HrmsCacheUtil;
import com.hrms.sport.domain.theme.*;
import com.hrms.sport.domain.zones.*;
import demo.sport.domain.theme.*;
import demo.sport.domain.zones.*;
import demo.sport.zones.common.constant.RedisCacheEnum;
import demo.sport.zones.common.exception.ErrorMessageEnum;
import com.hrms.sport.zones.convert.*;
import com.hrms.sport.zones.domain.*;
import com.hrms.sport.zones.entity.*;
import com.hrms.sport.zones.mapper.*;
import demo.sport.zones.convert.*;
import demo.sport.zones.domain.*;
import demo.sport.zones.entity.*;
import demo.sport.zones.mapper.*;
import demo.sport.zones.service.IPlaceInfoService;
import demo.sport.zones.service.IThemeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


/**
 *
 */
@Service
public class ThemeInfoServiceImpl implements IThemeInfoService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ThemeInfoServiceImpl.class);

    @Resource
    private IThemeInfoMapper themeInfoMapper;

    @Resource
    private IThemeInfoConvert themeInfoConvert;

    @Resource
    private IZoneMessConvert zonesMessConvert;

    @Resource
    private IZonesMedalConvert zonesMedalConvert;

    @Resource
    private IZonesCionConvert zonesCionConvert;

    @Resource
    private IThemeMedalMapper themeMedalMapper;

    @Resource
    private IThemeCionMapper zonesThemeCionMapper;

    @Resource
    private IThemeMessMapper zonesMessInfoMapper;

    @Resource
    private IThemeMembMapper zonesThemeMembMapper;

    @Resource
    private IThemeMembConvert themeMembConvert;

    @Resource
    private ICionLimitMapper cionLimitMapper;


    @Resource
    private IThemeRankMapper zonesThemeRankMapper;

    @Resource
    private IThemeZoneMapper themeZoneMapper;

    @Resource
    private IZoneInfoMapper zonesZoneInfoMapper;

    @Resource
    private IZonesZoneConvert zonesZoneConvert;


    @Resource
    private IRankConvert rankConvert;

    @Resource
    private IZoneGameMapper zoneGameMapper;

    @Resource
    private IGameConvert gameConvert;

    @Resource
    private IGameInfoMapper gameInfoMapper;

    @Resource
    private IRankGameMapper gameMapper;

    @Resource
    private IZonesRankConvert zonesRankConvert;

    @Resource
    private IThemeTaskMapper themeTaskMapper;

    @Resource
    private IPlaceInfoService placenfoService;


    public List<ThemeZoneInfoBO> queryZoneList(String zoneNo)
    {
        List<ThemeZoneInfoPO> placeThemeZonePOS = themeZoneMapper.selectZoneList(null, zoneNo);
        List<ThemeZoneInfoBO> themeZoneInfoBOS = Optional.ofNullable(placeThemeZonePOS).map(e -> themeMembConvert.convertZoneInfoPOS(e)).orElse(null);
        return themeZoneInfoBOS;
    }


    public List<RankGameInfoBO> queryGameList()
    {
        List<RankGameInfoBO> rankGameInfoBOS = HrmsCacheUtil.cacheGet(RedisCacheEnum.CACHE_KEY_ZONE_RANK_GAME);
        if (!ObjectUtils.isEmpty(rankGameInfoBOS)) {
            return rankGameInfoBOS;
        }
        List<RankGameInfoPO> reviewRankGamePOS = gameMapper.selectGameList();
        if (ObjectUtils.isEmpty(reviewRankGamePOS)) {
            return Collections.emptyList();
        }
        rankGameInfoBOS = zonesRankConvert.convertGameInfoPOS(reviewRankGamePOS);
        if (!ObjectUtils.isEmpty(rankGameInfoBOS)) {
            HrmsCacheUtil.cachePut(RedisCacheEnum.CACHE_KEY_ZONE_RANK_GAME, rankGameInfoBOS);
        }
        return rankGameInfoBOS;
    }

    public List<RankGameInfoBO> queryGameList(String playCode)
    {
        List<RankGameInfoBO> reviewGameInfoBOS = this.queryGameList();
        reviewGameInfoBOS = reviewGameInfoBOS.stream().filter(e -> playCode.equals(e.getPlayCode())).collect(Collectors.toList());
        return reviewGameInfoBOS;
    }

    @Override
    public List<ZonesThemeRankPO> queryRankList(ZonesThemeBaseIN zonesThemeBaseIN)
    {
        String strThemeNo = Optional.ofNullable(zonesThemeBaseIN).map(ZonesThemeBaseIN::getThemeNo).orElseThrow(() -> new HrmsException(ErrorMessageEnum.RESULT_CODE_997));
        List<ZonesThemeRankPO> zonesThemeRankPOS = zonesThemeRankMapper.selectRankList(strThemeNo);
        LOGGER.info("主题编号查询榜单列表：THEME_NO={},THEME_RANK={}", strThemeNo, JSONObject.toJSONString(zonesThemeRankPOS));
        return zonesThemeRankPOS;
    }

    public List<ZonesMessInfoOUT> queryMessList(String themeNo)
    {
        HashMap<String, Object> mapKeyParam = new HashMap<>();
        mapKeyParam.put("themeNo", themeNo);
        List<ZonesMessInfoOUT> zonesMessInfoOUTS = HrmsCacheUtil.cacheGet(RedisCacheEnum.CACHE_KEY_PLACE_THEME_MESS, mapKeyParam);
        if (!ObjectUtils.isEmpty(zonesMessInfoOUTS)) {
            return zonesMessInfoOUTS;
        }
        List<ZonesMessInfoPO> zonesMessInfoPOS = zonesMessInfoMapper.selectMessAllList(themeNo);
        zonesMessInfoOUTS = Optional.ofNullable(zonesMessInfoPOS).map(zonesMessConvert::convertMessOUTS).orElse(Collections.emptyList());
        if (!ObjectUtils.isEmpty(zonesMessInfoOUTS)) {
            HrmsCacheUtil.cachePut(RedisCacheEnum.CACHE_KEY_PLACE_THEME_MESS, zonesMessInfoOUTS, mapKeyParam);
        }
        return zonesMessInfoOUTS;
    }

    @Override
    public List<ThemeMedalInfoBO> queryMedalList(String themeNo)
    {
        HashMap<String, Object> mapKeyParam = new HashMap<>();
        mapKeyParam.put("themeNo", themeNo);
        List<ThemeMedalInfoBO> themeMedalInfoBOS = HrmsCacheUtil.cacheGet(RedisCacheEnum.CACHE_KEY_PLACE_THEME_MEDAL, mapKeyParam);
        if (!ObjectUtils.isEmpty(themeMedalInfoBOS)) {
            return themeMedalInfoBOS;
        }
        List<ThemeMedalInfoPO> themeMedalInfoPOS = themeMedalMapper.selectMedalList(themeNo);
        themeMedalInfoBOS = Optional.ofNullable(themeMedalInfoPOS).map(zonesMedalConvert::convertMedalInfoPOS).orElse(null);
        if (!ObjectUtils.isEmpty(themeMedalInfoBOS)) {
            HrmsCacheUtil.cachePut(RedisCacheEnum.CACHE_KEY_PLACE_THEME_MEDAL, themeMedalInfoBOS, mapKeyParam);
        }
        return themeMedalInfoBOS;
    }

    public List<ThemeTaskInfoOUT> queryTaskList(String themeNo)
    {
        HashMap<String, Object> mapKeyParam = new HashMap<>();
        mapKeyParam.put("themeNo", themeNo);
        List<ThemeTaskInfoOUT> themeTaskInfoOUTS = HrmsCacheUtil.cacheGet(RedisCacheEnum.CACHE_KEY_PLACE_THEME_TASK, mapKeyParam);
        if (!ObjectUtils.isEmpty(themeTaskInfoOUTS)) {
            return themeTaskInfoOUTS;
        }
        List<ThemeTaskInfoPO> themeTaskInfoPOS = themeTaskMapper.selectTaskList(themeNo);
        themeTaskInfoOUTS = Optional.ofNullable(themeTaskInfoPOS).map(zonesMedalConvert::convertTaskInfoOUTS).orElse(Collections.emptyList());
        if (!ObjectUtils.isEmpty(themeTaskInfoOUTS)) {
            HrmsCacheUtil.cachePut(RedisCacheEnum.CACHE_KEY_PLACE_THEME_TASK, themeTaskInfoOUTS, mapKeyParam);
        }
        return themeTaskInfoOUTS;
    }


    public List<ZonesCionInfoOUT> queryCionList(String themeNo)
    {
        List<ZonesThemeCionPO> zonesThemeCionPOS = zonesThemeCionMapper.selectCionList(themeNo);
        if (ObjectUtils.isEmpty(zonesThemeCionPOS)) {
            return Collections.emptyList();
        }
        List<PlaceCionInfoBO> placeCionInfoBOS = placenfoService.queryCionList();
        Map<String, PlaceCionInfoBO> mapPlaceCion = Optional.ofNullable(placeCionInfoBOS).map(e -> e.stream().collect(Collectors.toMap(key -> key.getCionCode(), val -> val))).orElse(Collections.EMPTY_MAP);
        List<ZonesCionInfoOUT> zonesThemeCionOUTS = zonesThemeCionPOS.stream().map(zonesThemeCionPO -> {
            String strCionCode = zonesThemeCionPO.getCionCode();
            ZonesCionInfoOUT zonesCionInfoOUT = zonesCionConvert.convertThemeCionPO(zonesThemeCionPO);
            String strCionIcon = Optional.ofNullable(mapPlaceCion.get(strCionCode)).map(e -> e.getCionIcon()).orElse(null);
            zonesCionInfoOUT.setCionIcon(strCionIcon);
            return zonesCionInfoOUT;
        }).collect(Collectors.toList());
        return zonesThemeCionOUTS;
    }

    @Override
    public PageResponse<PlaceThemeInfoOUT> queryThemeList(ZoneThemePageIN zoneThemePageIN)
    {
        PageResponse<PlaceThemeInfoOUT> pageResponse = new PageResponse<>();
        int nPageNum = Optional.ofNullable(zoneThemePageIN).map(ZoneThemePageIN::getPageNum).orElse(1);
        int nPageSize = Optional.ofNullable(zoneThemePageIN).map(ZoneThemePageIN::getPageSize).orElse(PageRequest.PAGE_SIZE_MAX_LIMIT);
        if (nPageSize <= 0) {
            nPageSize = PageRequest.PAGE_SIZE_MAX_LIMIT;
        }
        String strThemeDesc = Optional.ofNullable(zoneThemePageIN).map(ZoneThemePageIN::getThemeDesc).orElse(null);
        Page<PlaceThemeInfoPO> pageInfo = themeInfoMapper.queryThemeList(new Page<PlaceThemeInfoPO>(nPageNum, nPageSize), strThemeDesc);
        if (ObjectUtils.isEmpty(pageInfo)) {
            return pageResponse;
        }
        pageResponse.setPageNum(nPageNum);
        pageResponse.setPageSize(nPageSize);
        pageResponse.setTotalNum((int) pageInfo.getTotal());
        List<PlaceThemeInfoOUT> placeThemeInfoOUTS = pageInfo.getRecords().stream().map(placeThemeInfoPO -> {
            PlaceThemeInfoOUT placeThemeInfoOUT = themeInfoConvert.convert2ThemeInfoOUT(placeThemeInfoPO);
            return placeThemeInfoOUT;
        }).collect(Collectors.toList());
        pageResponse.setRecords(placeThemeInfoOUTS);
        return pageResponse;
    }

    @Override
    public void addThemeInfo(PlaceThemeInfoPO zonesThemeInfoPO)
    {
        themeInfoMapper.insertSelective(zonesThemeInfoPO);
    }

    @Override
    public PlaceThemeInfoPO selectDetailByThemeNo(String themeNo)
    {
        return themeInfoMapper.selectThemeInfo(themeNo);
    }

    @Override
    public void updateThemeInfo(PlaceThemeInfoPO zonesThemeInfoPO)
    {
        themeInfoMapper.updateThemeInfo(zonesThemeInfoPO);
    }


    public List<ThemeMembeInfoOUT> queryMembList(String themeNo)
    {
        List<ZonesThemeMembPO> zonesThemeMembPOS = zonesThemeMembMapper.selectMembList(themeNo);
        List<ThemeMembeInfoOUT> themeMembeInfoOUTS = Optional.ofNullable(zonesThemeMembPOS).map(themeMembConvert::convertThemeMembPOS).orElse(Collections.emptyList());
        return themeMembeInfoOUTS;
    }

    @Override
    public void evictThemeInfo(String themeNo)
    {
        HashMap<String, Object> mapKeyParam = new HashMap<>();
        mapKeyParam.put("themeNo", themeNo);
        HrmsCacheUtil.cacheEvict(RedisCacheEnum.CACHE_KEY_PLACE_THEME_INFO, mapKeyParam);
    }

    @Override
    public PlaceThemeInfoBO queryThemeInfo(String themeNo)
    {
        HashMap<String, Object> mapKeyParam = new HashMap<>();
        mapKeyParam.put("themeNo", themeNo);
        PlaceThemeInfoBO placeThemeInfoBO = HrmsCacheUtil.cacheGet(RedisCacheEnum.CACHE_KEY_PLACE_THEME_INFO, mapKeyParam);
        if (!ObjectUtils.isEmpty(placeThemeInfoBO)) {
            LOGGER.info("从缓存中加载主题明细数据：THEME_NO={}, THEME_INFO={}", themeNo, JSON.toJSONString(placeThemeInfoBO));
            return placeThemeInfoBO;
        }
        PlaceThemeInfoPO placeThemeInfoPO = themeInfoMapper.selectThemeInfo(themeNo);
        placeThemeInfoBO = Optional.ofNullable(placeThemeInfoPO).map(e -> themeInfoConvert.convertThemeInfoPO(e)).orElse(null);

        if (!ObjectUtils.isEmpty(placeThemeInfoBO)) {
            HrmsCacheUtil.cachePut(RedisCacheEnum.CACHE_KEY_PLACE_THEME_INFO, placeThemeInfoBO, mapKeyParam);
            LOGGER.info("写入主题明细数据到缓存：THEME_NO={}, THEME_INFO={}", themeNo, JSON.toJSONString(placeThemeInfoBO));
        }
        return placeThemeInfoBO;
    }

    @Override
    public PlaceThemeInfoOUT queryThemeInfo(ZonesThemeBaseIN zonesThemeBaseIN)
    {
        String strThemeNo = Optional.ofNullable(zonesThemeBaseIN).map(ZonesThemeBaseIN::getThemeNo).orElseThrow(() -> new HrmsException(ErrorMessageEnum.RESULT_CODE_997));
        //查询主题信息
        PlaceThemeInfoBO placeThemeInfoBO = this.queryThemeInfo(strThemeNo);
        LOGGER.info("主题编号查询主题详情：THEME_NO={}", strThemeNo);
        if (ObjectUtils.isEmpty(placeThemeInfoBO)) {
            LOGGER.error("指定主题编号的没有找到配置数据：THEME_NO={}", strThemeNo);
            return null;
        }
        PlaceThemeInfoOUT placeThemeInfoOUT = themeInfoConvert.convertThemeInfoBO(placeThemeInfoBO);
        LOGGER.debug("找到主题编号的主题配置数据：THEME_NO={}, THEME_INFO={}", strThemeNo, JSONObject.toJSONString(placeThemeInfoOUT));
        //查询消息列表
        List<ZonesMessInfoOUT> zonesMessInfoOUTS = this.queryMessList(strThemeNo);
        //查询勋章列表

        List<ThemeMedalInfoOUT> themeMedalInfoOUTS = Optional.ofNullable(this.queryMedalList(strThemeNo)).map(e -> themeInfoConvert.convertMedalInfoOUTS(e)).orElse(null);
        //查询场馆资金列表
        List<ZonesCionInfoOUT> zonesThemeCionOUTS = this.queryCionList(strThemeNo);
        //查询主题会员列表
        List<ThemeMembeInfoOUT> themeMembeInfoOUTS = this.queryMembList(strThemeNo);
        //
        List<ThemeTaskInfoOUT> themeTaskInfoOUTS = this.queryTaskList(strThemeNo);
        placeThemeInfoOUT.setMembeList(themeMembeInfoOUTS);
        placeThemeInfoOUT.setMessList(zonesMessInfoOUTS);
        placeThemeInfoOUT.setMedalList(themeMedalInfoOUTS);
        placeThemeInfoOUT.setCionList(zonesThemeCionOUTS);
        placeThemeInfoOUT.setTaskList(themeTaskInfoOUTS);
        return placeThemeInfoOUT;

    }

    /**
     * 根据主题编号查询货币限制数据
     *
     * @param cionCode
     * @return
     */
    @Override
    public List<CionLimitInfoBO> queryLimitList(String cionCode)
    {
        List<CionLimitInfoPO> cionLimitInfoPOS = cionLimitMapper.selectLimitList(cionCode);
        List<CionLimitInfoBO> cionLimitInfoBOS = Optional.ofNullable(cionLimitInfoPOS).map(zonesCionConvert::convertLimitInfoPOS).orElse(null);
        return cionLimitInfoBOS;
    }

    @Override
    public ThemeDetailCmsOUT queryCmsThemeDetail(ZonesThemeBaseIN zonesThemeBaseIN)
    {

        String strThemeNo = Optional.ofNullable(zonesThemeBaseIN).map(ZonesThemeBaseIN::getThemeNo).orElseThrow(() -> new HrmsException(ErrorMessageEnum.RESULT_CODE_997));
        //查询主题信息
        PlaceThemeInfoPO zonesThemeInfoPO = themeInfoMapper.selectThemeInfo(strThemeNo);
        LOGGER.info("主题编号查询主题详情：THEME_NO={}", strThemeNo);
        if (Objects.isNull(zonesThemeInfoPO)) {
            return new ThemeDetailCmsOUT();
        }
        //构建zoneList
        ThemeDetailCmsOUT zonesThemeInfoOUT = themeInfoConvert.convert2ThemeCmsInfo(zonesThemeInfoPO);
        List<ThemeZoneInfoPO> themeZoneInfoPOS = themeZoneMapper.selectZoneList(zonesThemeBaseIN.getThemeNo(), null);
        List<ThemeZoneCmsOUT> themeZoneCmsOUTList = new ArrayList<>();
        themeZoneInfoPOS.stream().forEach(zonePo -> {
            ZonesZoneInfoPO zonesZoneInfoPO = zonesZoneInfoMapper.selectZoneDetail(zonePo.getZoneNo());
            ThemeZoneCmsOUT themeZoneCmsOUT = zonesZoneConvert.convert2CmsZone(zonesZoneInfoPO);
            themeZoneCmsOUTList.add(themeZoneCmsOUT);
        });
        zonesThemeInfoOUT.setZoneList(themeZoneCmsOUTList);

        List<ThemeCionCmsOUT> cionList = this.queryCmsCionList(strThemeNo);
        zonesThemeInfoOUT.setCionList(cionList);

        List<ThemeExperCmsOUT> experList = this.queryCmsExperList(strThemeNo);
        zonesThemeInfoOUT.setExperList(experList);

        ThemeMessCmsOUT messInfo = this.queryCmsMessList(strThemeNo);
        zonesThemeInfoOUT.setMessInfo(messInfo);

        List<ZonesThemeRankPO> zonesThemeRankPOS = zonesThemeRankMapper.selectRankList(strThemeNo);
        List<ThemeRankCmsOUT> rankList = rankConvert.convert2ThemeRankCmsOUTS(zonesThemeRankPOS);
        zonesThemeInfoOUT.setRankList(rankList);
        LOGGER.info("主题编号查询主题详情：THEME_NO={}", JSONObject.toJSON(zonesThemeInfoOUT));
        return zonesThemeInfoOUT;
    }

    private ThemeMessCmsOUT queryCmsMessList(String strThemeNo)
    {
        List<ZonesMessInfoPO> zonesMessInfoPOS = zonesMessInfoMapper.selectMessAllList(strThemeNo);
        if (Objects.isNull(zonesMessInfoPOS)) {
            return null;
        }
        ThemeMessCmsOUT themeMessCmsOUT = new ThemeMessCmsOUT();
        List<String> strList = new ArrayList<>();
        for (ZonesMessInfoPO messInfoPO : zonesMessInfoPOS) {
            String messType = messInfoPO.getMessType() + ":" + messInfoPO.getMessNum();
            strList.add(messType);
        }
        String messConf = strList.stream().collect(Collectors.joining(";"));
        themeMessCmsOUT.setMessConf(messConf);
        themeMessCmsOUT.setSortMode(zonesMessInfoPOS.get(0).getSortMode());
        return themeMessCmsOUT;
    }

    private List<ThemeExperCmsOUT> queryCmsExperList(String themeNo)
    {
        List<PlaceZoneGamePO> themeGameZonePOS = zoneGameMapper.selectByThemeNo(themeNo);
        List<ThemeExperCmsOUT> themeExperCmsOUTS = new ArrayList<>();
        if (CollectionUtils.isEmpty(themeGameZonePOS)) {
            return null;
        }
        if (themeGameZonePOS.size() == 1) {
            PlaceZoneGamePO placeZoneGamePO = themeGameZonePOS.get(0);
            ThemeExperCmsOUT themeExperCmsOUT = gameConvert.convertThemeExperCmsOUT(placeZoneGamePO);
            themeExperCmsOUTS.add(themeExperCmsOUT);
            return themeExperCmsOUTS;
        }
        Map<String, List<PlaceZoneGamePO>> placeZoneGameMap = themeGameZonePOS.stream().collect(Collectors.groupingBy(PlaceZoneGamePO::getPlayCode));

        for (Map.Entry<String, List<PlaceZoneGamePO>> entry : placeZoneGameMap.entrySet()) {
            List<PlaceZoneGamePO> placeZoneGamePOS = entry.getValue();
            PlaceZoneGamePO placeZoneGamePO = placeZoneGamePOS.get(0);
            ThemeExperCmsOUT themeExperCmsOUT = gameConvert.convertThemeExperCmsOUT(placeZoneGamePO);
            String zoneNo = placeZoneGamePOS.stream().map(PlaceZoneGamePO::getZoneNo).collect(Collectors.joining(","));
            themeExperCmsOUT.setZoneNo(zoneNo);
            ThemeGameInfoPO themeGameInfoPO = gameInfoMapper.selectGameInfoByPlayCode(placeZoneGamePO.getPlayCode());
            if (!Objects.isNull(themeGameInfoPO)) {
                themeExperCmsOUT.setPlayName(themeGameInfoPO.getPlayName());
            }
            themeExperCmsOUTS.add(themeExperCmsOUT);
        }
        return themeExperCmsOUTS;
    }

    private List<ThemeCionCmsOUT> queryCmsCionList(String strThemeNo)
    {
        List<ZonesThemeCionPO> zonesThemeCionPOS = zonesThemeCionMapper.selectCionList(strThemeNo);
        List<ThemeCionCmsOUT> cionList = new ArrayList<>();
        zonesThemeCionPOS.stream().forEach(themeCionPO -> {
            List<CionLimitInfoPO> cionLimitInfoPOS = cionLimitMapper.selectLimitList(themeCionPO.getCionCode());
            cionLimitInfoPOS.stream().forEach(cionLimit -> {
                ThemeCionCmsOUT themeCionCmsOUT = zonesCionConvert.convertThemeCmsCionOUT(cionLimit, themeCionPO);
                ThemeCionCmsOUT divide = this.divide(themeCionCmsOUT);
                cionList.add(divide);
            });
        });
        return cionList;
    }

    private ThemeCionCmsOUT divide(ThemeCionCmsOUT themeCionCmsOUT)
    {
        if (themeCionCmsOUT.getValueMode().equals("00")) {
            Integer limitValue = themeCionCmsOUT.getLimitValue();
            Integer limitResult = limitValue / 100;
            themeCionCmsOUT.setLimitValue(limitResult);
        }
        String initiValue = themeCionCmsOUT.getInitiValue();
        Integer integer = Integer.valueOf(initiValue);
        Integer initResult = integer / 100;
        String initResultStr = Integer.toString(initResult);
        themeCionCmsOUT.setInitiValue(initResultStr);
        return themeCionCmsOUT;
    }

    @Override
    public void closePlaceTheme(LocalDateTime now, String status)
    {
        //TODO 考虑到缓存的情况，此处需要调整，需要明确变更的是哪个主题，清除哪一个主题数据
        themeInfoMapper.closePlaceTheme(now, status);
    }

    @Override
    public void openPlaceTheme(LocalDateTime now, String status)
    {
        //TODO 考虑到缓存的情况，此处需要调整，需要明确变更的是哪个主题，清除哪一个主题数据
        themeInfoMapper.openPlaceTheme(now, status);
    }
}
