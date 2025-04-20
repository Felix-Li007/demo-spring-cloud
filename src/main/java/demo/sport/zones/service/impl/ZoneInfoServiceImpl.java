package demo.sport.zones.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.core.domian.PageRequest;
import com.hrms.core.domian.PageResponse;
import com.hrms.core.domian.ResultOut;
import com.hrms.core.exception.HrmsException;
import com.hrms.frame.cache.HrmsCacheUtil;
import demo.sport.constants.zones.PlayModeEnum;
import com.hrms.sport.domain.zones.*;
import demo.sport.domain.zones.*;
import demo.sport.zones.common.config.StorageBaseConfig;
import demo.sport.zones.common.config.StoragePathConfig;
import demo.sport.zones.common.constant.RedisCacheEnum;
import demo.sport.zones.common.exception.ErrorMessageEnum;
import demo.sport.zones.common.utils.ThemePageUtil;
import demo.sport.zones.convert.IZonesZoneConvert;
import demo.sport.zones.convert.IZonesZoneDateConvert;
import demo.sport.zones.convert.IZonesZoneTimeConvert;
import com.hrms.sport.zones.domain.*;
import com.hrms.sport.zones.entity.*;
import com.hrms.sport.zones.mapper.*;
import demo.sport.zones.domain.*;
import demo.sport.zones.entity.*;
import demo.sport.zones.mapper.*;
import demo.sport.zones.service.IGameInfoService;
import demo.sport.zones.service.IPFileInfoService;
import demo.sport.zones.service.IThemeInfoService;
import demo.sport.zones.service.IZoneInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class ZoneInfoServiceImpl implements IZoneInfoService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ZoneInfoServiceImpl.class);

    @Resource
    private StorageBaseConfig storageBaseConfig;

    @Resource
    private StoragePathConfig storagePathConfig;

    @Resource
    private IZoneInfoMapper zonesZoneInfoMapper;

    @Resource
    private IZoneDateMapper zonesZoneDateMapper;


    @Resource
    private IZoneTimeMapper zonesZoneTimeMapper;

    @Resource
    private IZonesZoneDateConvert zonesZoneDateConvert;

    @Resource
    private IZonesZoneTimeConvert zonesZoneTimeConvert;

    @Resource
    private IThemeHeadMapper themeHeadInfoMapper;

    @Resource
    private IExperFlowMapper experFlowMapper;

    @Resource
    private IZonesZoneConvert zonesZoneConvert;

    @Resource
    private IGameExperMapper gameExperMapper;

    @Resource
    private IGameInfoService gameInfoService;
    @Resource
    private IThemeMessMapper zonesMessInfoMapper;

    @Resource
    private IPFileInfoService pFileInfoService;

    @Resource
    private IThemeInfoService themeInfoService;


    @Override
    public void modifyZoneState(String zoneNo, String zoneState)
    {
        zonesZoneInfoMapper.updateZoneState(zoneNo, zoneState);
        this.evictZoneInfo(zoneNo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PlaceExperFlowBO calcuExperPoint(PlaceExperFlowBO placeExperFlowBO)
    {
        if (ObjectUtils.isEmpty(placeExperFlowBO)) {
            return null;
        }

        String strGameCode = placeExperFlowBO.getGameCode();
        String strAccntNo = placeExperFlowBO.getAccountNo();
        String strZoneNo = placeExperFlowBO.getZoneNo();
        String strExperNo = placeExperFlowBO.getExperNo();
        //检查是否已经处理过记录
        List<PlaceExperFlowBO> placeExperFlowBOS = pFileInfoService.queryExperFlow(null, Arrays.asList(strExperNo));
        if (!ObjectUtils.isEmpty(placeExperFlowBOS)) {
            LOGGER.info("经验点数数据已经处理过一次，直接忽略处理");
            placeExperFlowBO = placeExperFlowBOS.get(0);
            return placeExperFlowBO;
        }
        //根据场馆编号查询主题编号
        List<ThemeZoneInfoBO> themeZoneInfoBOS = themeInfoService.queryZoneList(strZoneNo);
        ThemeZoneInfoBO themeZoneInfoBO = Optional.ofNullable(themeZoneInfoBOS.get(0)).orElse(null);
        String strThemeNo = Optional.ofNullable(themeZoneInfoBO).map(ThemeZoneInfoBO::getThemeNo).orElseThrow(() -> new HrmsException(ErrorMessageEnum.ERROR_THEME_NOT_EXIST));
        String strPlayCode = placeExperFlowBO.getPlayCode();
        //根据场馆编号查询场馆里的玩法的经验值设定
        List<GameExperInfoBO> gameExperInfoBOS = this.queryExperList(strThemeNo, strZoneNo);
        if (ObjectUtils.isEmpty(gameExperInfoBOS)) {
            LOGGER.info("主题场馆没有配置玩法经验点数，直接丢弃：ACCNT_NO={}, ZONE_NO={},PLAY_CODE={}", strAccntNo, strZoneNo, strPlayCode);
            return null;
        }

        placeExperFlowBO.setThemeNo(strThemeNo);

        //查询不同场馆的游经验点数
        gameExperInfoBOS = gameExperInfoBOS.stream().filter(e -> strPlayCode.equals(e.getPlayCode())).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(gameExperInfoBOS)) {
            LOGGER.info("主题场馆没有配置玩法经验点数，直接丢弃：ACCNT_NO={}, ZONE_NO={},PLAY_CODE={}", strAccntNo, strZoneNo, strPlayCode);
            return null;
        }
        GameExperInfoBO gameExperInfoBO = gameExperInfoBOS.get(0);
        int nExperPoint = Optional.ofNullable(gameExperInfoBO).map(GameExperInfoBO::getExperPoint).orElse(0);
        if (nExperPoint <= 0) {
            LOGGER.info("主题场馆没有配置玩法经验点数，直接丢弃：ACCNT_NO={}, ZONE_NO={},PLAY_CODE={}", strAccntNo, strZoneNo, strPlayCode);
            return null;
        }
        //更新用户的经验点数
        boolean bUpdate = pFileInfoService.modifyExperPoint(strThemeNo, strAccntNo, nExperPoint);
        if (!bUpdate) {
            LOGGER.error("修改用户档案经验点数失败：ACCNT_NO={},EXPER_POINT={}", strAccntNo, nExperPoint);
            return null;
        }
        placeExperFlowBO.setExperPoint(nExperPoint);
        //保存经验值获得记录
        PlaceExperFlowPO placeExperFlowPO = pFileInfoService.writeExperFlow(placeExperFlowBO);
        if (ObjectUtils.isEmpty(placeExperFlowPO)) {
            LOGGER.error("保存用户经验点数获得记录失败：ACCNT_NO={},EXPER_FLOW={}", strAccntNo, JSONObject.toJSONString(placeExperFlowPO));
            return null;
        }
        //根据游戏拆分经验点数数据
        PlayModeEnum playMode = pFileInfoService.splitExperPoint(placeExperFlowBO);
        LOGGER.debug("计算用户经验点数成功：ACCNT_NO={},EXPER_FLOW={}", strAccntNo, JSONObject.toJSONString(placeExperFlowPO));
        return placeExperFlowBO;

    }

    @Override
    public Page<ZonesMessInfoPO> queryMessList(ZonesThemePageIN zonesThemePageIN)
    {
        Page<ZonesMessInfoPO> page = new Page<>();
        int nPageNum = Optional.ofNullable(zonesThemePageIN).map(ZonesThemePageIN::getPageNum).orElse(1);
        int nPageSize = Optional.ofNullable(zonesThemePageIN).map(ZonesThemePageIN::getPageSize).orElse(ZonesThemePageIN.PAGE_SIZE_MAX_LIMIT);
        nPageSize = (nPageSize > ZonesThemePageIN.PAGE_SIZE_MAX_LIMIT) ? PageRequest.PAGE_SIZE_MAX_LIMIT : nPageSize;
        int nSequenceId = Optional.ofNullable(zonesThemePageIN).map(ZonesThemePageIN::getSequenceId).orElse(1);
        String themeNo = Optional.ofNullable(zonesThemePageIN).map(ZonesThemePageIN::getThemeNo).orElse(null);
        IPage<ZonesMessInfoPO> pageInfo = zonesMessInfoMapper.selectMessPOS(new Page<ZonesMessInfoPO>(nPageNum, nPageSize), themeNo, nSequenceId);
        LOGGER.info("主题编号查询大厅列表：THEME_NO={},PAGE_NUM={}, PAGE_SIZE={}", themeNo, nPageNum, nPageSize);
        List<ZonesMessInfoPO> zonesMessInfoPOS = pageInfo.getRecords();
        page.setCurrent(pageInfo.getCurrent());
        page.setSize(pageInfo.getSize());
        page.setTotal((int) pageInfo.getTotal());
        page.setRecords(zonesMessInfoPOS);
        return page;
    }

    @Override
    public List<GameExperInfoBO> queryExperList(String themeNo, String zoneNo)
    {
        Map<String, Object> mapKeyParam = new HashMap<>();
        mapKeyParam.put("themeNo", themeNo);
        mapKeyParam.put("zoneNo", zoneNo);
        List<GameExperInfoBO> gameExperInfoBOS = HrmsCacheUtil.cacheGet(RedisCacheEnum.CACHE_KEY_ZONE_GAME_EXPER, mapKeyParam);
        if (!ObjectUtils.isEmpty(gameExperInfoBOS)) {
            return gameExperInfoBOS;
        }
        List<GameExperInfoPO> gameExperInfoPOS = gameExperMapper.selectExperList(themeNo, zoneNo);
        if (ObjectUtils.isEmpty(gameExperInfoPOS)) {
            return null;
        }
        gameExperInfoBOS = zonesZoneConvert.convertExperInfoBOS(gameExperInfoPOS);
        gameExperInfoBOS = gameExperInfoBOS.stream().map(gameExperInfoBO -> {
            String strPlayCode = gameExperInfoBO.getPlayCode();
            ThemeGameInfoBO themeGameInfoBO = gameInfoService.queryGameInfo(strPlayCode);
            String strPlayName = Optional.ofNullable(themeGameInfoBO).map(ThemeGameInfoBO::getPlayName).orElse(null);
            gameExperInfoBO.setPlayName(strPlayName);
            return gameExperInfoBO;
        }).collect(Collectors.toList());

        if (!ObjectUtils.isEmpty(gameExperInfoBOS)) {
            HrmsCacheUtil.cachePut(RedisCacheEnum.CACHE_KEY_ZONE_GAME_EXPER, gameExperInfoBOS, mapKeyParam);
        }
        LOGGER.info("查询场馆游戏经验点数数据：THEME_NO={}, ZONE_NO={},EXPER_INFO={}", themeNo, zoneNo, JSONObject.toJSONString(gameExperInfoBOS));
        return gameExperInfoBOS;
    }

    public String findHallNo(String parentId)
    {
        ZonesZoneInfoPO zonesZoneInfoPO = zonesZoneInfoMapper.selectZoneDetail(parentId);
        String strParentNo = Optional.ofNullable(zonesZoneInfoPO).map(ZonesZoneInfoPO::getParentId).orElse(null);
        if (ObjectUtils.isEmpty(strParentNo)) {
            String strZoneNo = zonesZoneInfoPO.getZoneNo();
            return strZoneNo;
        }
        return this.findHallNo(strParentNo);
    }

    public ZonesZoneBaseOUT queryHallInfo(String hallNo)
    {
        ZonesZoneInfoPO zonesZoneInfoPO = zonesZoneInfoMapper.selectZoneDetail(hallNo);
        ZonesZoneBaseOUT zonesZoneBaseOUT = Optional.ofNullable(zonesZoneInfoPO).map(e -> zonesZoneConvert.convert2ZonesZoneOUT(e)).orElse(null);
        return zonesZoneBaseOUT;
    }


    @Override
    public void evictZoneInfo(String zoneNo)
    {
        //清理缓存
        HashMap<String, Object> mapKeyParam = new HashMap<>();
        mapKeyParam.put("zoneNo", zoneNo);
        HrmsCacheUtil.cacheEvict(RedisCacheEnum.CACHE_KEY_PLACE_ZONE_INFO, mapKeyParam);
    }

    @Override
    public ZonesZoneDetailOUT queryZoneDetail(ZonesZoneBaseIN zonesZoneIN)
    {
        String strZoneNo = Optional.ofNullable(zonesZoneIN).map(ZonesZoneBaseIN::getZoneNo).orElseThrow(() -> new HrmsException(ErrorMessageEnum.RESULT_CODE_997));
        HashMap<String, Object> mapKeyParam = new HashMap<>();
        mapKeyParam.put("zoneNo", strZoneNo);

        ZonesZoneDetailOUT zonesZoneDetailOUT = HrmsCacheUtil.cacheGet(RedisCacheEnum.CACHE_KEY_PLACE_ZONE_INFO, mapKeyParam);
        if (!ObjectUtils.isEmpty(zonesZoneDetailOUT)) {
            return zonesZoneDetailOUT;
        }
        ZonesZoneInfoPO zonesZoneInfoPO = zonesZoneInfoMapper.selectZoneDetail(strZoneNo);
        LOGGER.info("查询场馆详情：ZONE_NO={}", strZoneNo);
        zonesZoneDetailOUT = zonesZoneConvert.convert2ZonesZoneOUT(zonesZoneInfoPO);
        if (ObjectUtils.isEmpty(zonesZoneDetailOUT)) {
            return zonesZoneDetailOUT;
        }
        String strParentNo = zonesZoneInfoPO.getParentId();
        if (!ObjectUtils.isEmpty(strParentNo)) {
            String strHallNo = this.findHallNo(strParentNo);
            ZonesZoneBaseOUT zonesZoneBaseOUT = this.queryHallInfo(strHallNo);
            zonesZoneDetailOUT.setHallInfo(zonesZoneBaseOUT);
        }
        //
        //查询场馆开放日期数据
        List<ZonesZoneDatePO> zonesZoneDatePOS = Optional.ofNullable(zonesZoneDateMapper.selectZoneDatePOS(strZoneNo)).orElse(Collections.emptyList());
        List<ZonesZoneDateOUT> zonesZoneDateOUTS = zonesZoneDateConvert.convert2ZoneDateOUTS(zonesZoneDatePOS);
        //根据开放日期查询开放时间数据
        zonesZoneDateOUTS.stream().forEach(zonesZoneDateOUT -> {
            List<ZonesZoneTimePO> zonesZoneTimePOS = zonesZoneTimeMapper.selectAllTimePOList(zonesZoneDateOUT.getDateNo());
            List<ZonesZoneTimeOUT> zonesZoneTimeOUTS = new ArrayList<>();
            zonesZoneTimePOS.stream().forEach(time -> {
                ZonesZoneTimeOUT zonesZoneTimeOUT = zonesZoneTimeConvert.conver2ZoneTimeOUT(time);
                zonesZoneTimeOUTS.add(zonesZoneTimeOUT);
            });
            zonesZoneDateOUT.setTimeList(zonesZoneTimeOUTS);
        });
        zonesZoneDetailOUT.setOpenList(zonesZoneDateOUTS);
        if (!ObjectUtils.isEmpty(zonesZoneDetailOUT)) {
            HrmsCacheUtil.cachePut(RedisCacheEnum.CACHE_KEY_PLACE_ZONE_INFO, zonesZoneDetailOUT, mapKeyParam);
        }
        return zonesZoneDetailOUT;
    }

    @Override
    public ResultOut<PageResponse<ZonesZoneInfoOUT>> queryZoneList(ZonesThemePageIN zonesThemePageIn)
    {
        PageResponse<ZonesZoneInfoOUT> pageResponse = new PageResponse<>();
        ZonesThemePageIN zonesThemePageIN = ThemePageUtil.checkThemePageInfo(zonesThemePageIn);
        pageResponse.setPageNum(zonesThemePageIN.getPageNum());
        pageResponse.setPageSize(zonesThemePageIN.getPageSize());
        //根据场馆编号查询子结构
        if (!ObjectUtils.isEmpty(zonesThemePageIN.getZoneNo())) {
            ZonesZoneInfoPO zonesZoneInfoPO = zonesZoneInfoMapper.selectZoneDetail(zonesThemePageIN.getZoneNo());
            List<ZonesZoneInfoPO> zonesChildList = zonesZoneInfoMapper.selectChildZoneList(zonesZoneInfoPO.getZoneNo());
            List<ZonesZoneInfoOUT> zonesZoneOUTList = zonesZoneConvert.convert2ZonesZoneOUTS(zonesChildList);
            pageResponse.setRecords(zonesZoneOUTList);
            return new ResultOut<PageResponse<ZonesZoneInfoOUT>>().buildSuccess(pageResponse);
        }
        //查询父结构场馆列表
        IPage<ZonesZoneInfoPO> zonesPage = this.queryZoneParentList(zonesThemePageIn);
        pageResponse.setTotalNum((int) zonesPage.getTotal());
        List<ZonesZoneInfoPO> zonesZoneInfoPOS = zonesPage.getRecords();
        Boolean parentFlag = zonesThemePageIn.getParentFlag();
        if (ObjectUtils.isEmpty(parentFlag) || !parentFlag) {        //查询子结构场馆列表
            zonesZoneInfoPOS.stream().filter(zonesZoneInfoPO -> Optional.ofNullable(zonesZoneInfoPO).isPresent()).forEach(zonesZoneInfoPO -> {
                List<ZonesZoneInfoPO> zonesChildList = zonesZoneInfoMapper.selectChildZoneList(zonesZoneInfoPO.getZoneNo());
                zonesZoneInfoPO.setChildZonesList(zonesChildList);
            });
        }
        List<ZonesZoneInfoOUT> zonesZoneInfoOUTS = zonesZoneConvert.convert2ZonesZoneOUTS(zonesZoneInfoPOS);
        LOGGER.info("查询父子场馆列表：ZONE_LIST={}", JSONObject.toJSONString(zonesZoneInfoOUTS));
        pageResponse.setRecords(zonesZoneInfoOUTS);
        return new ResultOut<PageResponse<ZonesZoneInfoOUT>>().buildSuccess(pageResponse);
    }

    private String combineImageURL(String imageFile)
    {
        if (ObjectUtils.isEmpty(imageFile)) {
            return null;
        }
        String strImageURL = storageBaseConfig.getOssCname() + "/" + storagePathConfig.getRootPath() + storagePathConfig.getHeadPath() + "/" + imageFile;
        return strImageURL;
    }

    @Override
    public ThemeHeadInfoBO randHeadList(String themeNo)
    {
        ZonesThemePageIN zonesThemePageIN = new ZonesThemePageIN();
        zonesThemePageIN.setZoneNo(themeNo);
        //计算共有多少头像，随机选择一个出来
        zonesThemePageIN.setPageSize(1);
        zonesThemePageIN.setPageNum(1);
        IPage<ThemeHeadInfoBO> page = this.queryHeadList(zonesThemePageIN);
        int nTotalNum = Optional.ofNullable(page).map(e -> (int) e.getTotal()).orElse(0);
        if (nTotalNum < 0) {
            return null;
        }
        int nPageNum = new Random().nextInt(nTotalNum) + 1;
        zonesThemePageIN.setPageNum(nPageNum);
        page = this.queryHeadList(zonesThemePageIN);
        List<ThemeHeadInfoBO> themeHeadInfoBOS = Optional.ofNullable(page).map(e -> e.getRecords()).orElse(null);

        ThemeHeadInfoBO themeHeadInfoBO = Optional.ofNullable(themeHeadInfoBOS).map(e -> e.get(0)).orElse(null);
        return themeHeadInfoBO;
    }

    /**
     * @param themeNo
     * @param headNo
     * @return
     */
    @Override
    public ThemeHeadInfoBO queryHeadDetail(String themeNo, String headNo)
    {
        ThemeHeadInfoPO themeHeadInfoPO = themeHeadInfoMapper.selectHeadDetail(themeNo, headNo);
        ThemeHeadInfoBO themeHeadInfoBO = Optional.ofNullable(themeHeadInfoPO).map(e -> zonesZoneConvert.convertHeadInfoPO(themeHeadInfoPO)).orElse(null);
        return themeHeadInfoBO;
    }


    /**
     * @param zonesThemePageIN
     * @return
     */
    @Override
    public Page<ThemeHeadInfoBO> queryHeadList(ZonesThemePageIN zonesThemePageIN)
    {
        String strThemeNo = zonesThemePageIN.getThemeNo();
        int nPageNum = Optional.ofNullable(zonesThemePageIN).map(e -> e.getPageNum()).orElse(1);
        int nPageSize = Optional.ofNullable(zonesThemePageIN).map(e -> e.getPageSize()).orElse(-1);
        nPageSize = (nPageSize <= 0) ? PageRequest.PAGE_SIZE_MAX_LIMIT : nPageSize;
        HashMap<String, Object> mapKeyParam = new HashMap<>();
        mapKeyParam.put("themeNo", strThemeNo);
        mapKeyParam.put("pageNum", nPageNum);
        mapKeyParam.put("pageSize", nPageSize);
        Page<ThemeHeadInfoBO> page = HrmsCacheUtil.cacheGet(RedisCacheEnum.CACHE_KEY_THEME_HEAD_LIST, mapKeyParam);
        if (!ObjectUtils.isEmpty(page)) {
            return page;
        }
        Page<ThemeHeadInfoPO> pageInfo = themeHeadInfoMapper.selectHeadList(new Page<ThemeHeadInfoPO>(nPageNum, nPageSize), strThemeNo, zonesThemePageIN.getSequenceId());
        LOGGER.info("查询系统头像列表：THEME_NO={},PAGE_NUM={}, PAGE_SIZE={}", zonesThemePageIN.getThemeNo(), zonesThemePageIN.getPageNum(), zonesThemePageIN.getPageSize());
        List<ThemeHeadInfoBO> themeHeadInfoBOS = Optional.ofNullable(pageInfo.getRecords()).map(e -> e.stream().map(themeHeadInfoPO -> {
            ThemeHeadInfoBO themeHeadInfoBO = zonesZoneConvert.convertHeadInfoPO(themeHeadInfoPO);
            return themeHeadInfoBO;
        }).collect(Collectors.toList())).orElse(Collections.emptyList());
        page = new Page<>();
        page.setCurrent(pageInfo.getCurrent());
        page.setSize(pageInfo.getSize());
        page.setTotal((int) pageInfo.getTotal());
        page.setRecords(themeHeadInfoBOS);
        if (!ObjectUtils.isEmpty(page)) {
            HrmsCacheUtil.cachePut(RedisCacheEnum.CACHE_KEY_THEME_HEAD_LIST, page, mapKeyParam);
        }
        return page;
    }


    private IPage<ZonesZoneInfoPO> queryZoneParentList(ZonesThemePageIN zonesThemePageIn)
    {
        IPage<ZonesZoneInfoPO> page = new Page<>();
        IPage<ZonesZoneInfoPO> pageInfo = zonesZoneInfoMapper.selectZoneList(new Page<ZonesZoneInfoPO>(zonesThemePageIn.getPageNum(), zonesThemePageIn.getPageSize()), zonesThemePageIn.getThemeNo());
        LOGGER.info("主题编号查询场馆列表：THEME_NO={},PAGE_NUM={}, PAGE_SIZE={}", zonesThemePageIn.getThemeNo(), zonesThemePageIn.getPageNum(), zonesThemePageIn.getPageSize());
        List<ZonesZoneInfoPO> zonesZoneInfoPOS = pageInfo.getRecords();
        page.setCurrent(pageInfo.getCurrent());
        page.setSize(pageInfo.getSize());
        page.setTotal((int) pageInfo.getTotal());
        page.setRecords(zonesZoneInfoPOS);
        return page;
    }

}
