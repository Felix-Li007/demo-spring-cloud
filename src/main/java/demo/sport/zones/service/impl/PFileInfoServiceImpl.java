package demo.sport.zones.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.account.domain.team.AccntTeamInfoOUT;
import com.hrms.account.domain.team.TeamCrewInfoOUT;
import com.hrms.account.domain.team.WhereTeamPageIN;
import com.hrms.core.domian.PageRequest;
import com.hrms.core.domian.PageResponse;
import com.hrms.core.exception.HrmsException;
import com.hrms.core.exception.ServiceValidate;
import com.hrms.frame.cache.HrmsCacheKeyUtil;
import com.hrms.frame.redis.lock.RedissonDistributedLocker;
import com.hrms.frame.sequence.HrmsSequenceGenerator;
import com.hrms.frame.utils.DateTimeUtils;
import demo.sport.constants.zones.HeadModeEnum;
import demo.sport.constants.zones.HeadTypeEnum;
import demo.sport.constants.zones.PlayModeEnum;
import demo.sport.domain.zones.ThemePFilePageIN;
import demo.sport.domain.zones.ZoneThemePageIN;
import demo.sport.domain.zones.ZonesPFileBaseIN;
import demo.sport.domain.zones.ZonesThemePageIN;
import demo.sport.zones.common.constant.RedisCacheEnum;
import demo.sport.zones.common.constant.ZonesSequenEnum;
import demo.sport.zones.common.exception.ErrorMessageEnum;
import demo.sport.zones.convert.IZonePFileConvert;
import demo.sport.zones.domain.PlaceExperFlowPO;
import demo.sport.zones.domain.PlayExperInfoPO;
import demo.sport.zones.domain.TeamExperInfoPO;
import demo.sport.zones.domain.ZonesPfileInfoPO;
import com.hrms.sport.zones.entity.*;
import demo.sport.zones.entity.*;
import demo.sport.zones.mapper.IExperFlowMapper;
import demo.sport.zones.mapper.IPFileInfoMapper;
import demo.sport.zones.mapper.IPlayExperMapper;
import demo.sport.zones.mapper.ITeamExperMapper;
import demo.sport.zones.service.IGameInfoService;
import demo.sport.zones.service.IPFileInfoService;
import demo.sport.zones.service.ITeamInfoService;
import demo.sport.zones.service.IZoneInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class PFileInfoServiceImpl implements IPFileInfoService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(PFileInfoServiceImpl.class);

    @Resource
    private IPFileInfoMapper pFileInfoMapper;

    @Resource
    private IZoneInfoService zoneInfoService;

    @Resource
    private IZonePFileConvert zonePFileConvert;

    @Resource
    private IExperFlowMapper experFlowMapper;

    @Resource
    private IPlayExperMapper playExperMapper;

    @Resource
    private IGameInfoService gameInfoService;

    @Resource
    private ITeamExperMapper teamExperMapper;

    @Resource
    private ITeamInfoService teamInfoService;

    @Resource
    private RedissonDistributedLocker distributedLocker;

    public TeamExperInfoBO loadTeamExper(String themeNo, String teamNo)
    {
        WhereTeamPageIN whereTeamPageIN = new WhereTeamPageIN();
        whereTeamPageIN.setTeamNo(teamNo);
        whereTeamPageIN.setPageSize(2000);
        whereTeamPageIN.setPageNum(1);
        int nPageNum = 1;
        int nPageCount = 0;
        int nTeamSize = 1;
        long nTotalExper = 0;
        do {
            PageResponse<TeamCrewInfoOUT> pageResponse = teamInfoService.queryCrewList(whereTeamPageIN);
            if (ObjectUtils.isEmpty(pageResponse)) {
                return null;
            }
            nPageCount = Optional.ofNullable(pageResponse.getPageCount()).orElse(0);
            nTeamSize = Optional.ofNullable(pageResponse.getTotalNum()).orElse(1);
            List<String> accntNos = Optional.ofNullable(pageResponse.getRecords()).map(e -> e.stream().map(teamCrewInfoOUT -> {
                String strAccntNo = teamCrewInfoOUT.getAccountNo();
                return strAccntNo;
            }).collect(Collectors.toList())).orElse(null);
            List<ZonesPfileInfoPO> zonesPfileInfoPOS = this.queryPFileList(accntNos);
            nTotalExper += Optional.ofNullable(zonesPfileInfoPOS).map(e -> e.stream().mapToLong(ZonesPfileInfoPO::getExperPoint).sum()).orElse(0L);
            nPageNum++;
        } while (nPageNum <= nPageCount);
        //计算平均值
        BigDecimal nExperPoint = new BigDecimal(nTotalExper);
        LocalDateTime ldtNow = LocalDateTime.now();
        TeamExperInfoBO teamExperInfoBO = this.queryTeamExper(themeNo, teamNo);
        TeamExperInfoPO teamExperInfoPO = Optional.ofNullable(teamExperInfoBO).map(e -> zonePFileConvert.convertExperInfoBO(e)).orElse(null);

        if (ObjectUtils.isEmpty(teamExperInfoPO)) {
            teamExperInfoPO = new TeamExperInfoPO();
            teamExperInfoPO.setThemeNo(themeNo);
            teamExperInfoPO.setTeamNo(teamNo);
            teamExperInfoPO.setExperPoint(nExperPoint);
            teamExperInfoPO.setCreateTime(ldtNow);
            teamExperInfoPO.setUpdateTime(ldtNow);
            int nInsertNum = teamExperMapper.insertExperInfo(teamExperInfoPO);
        } else {
            teamExperInfoPO.setUpdateTime(ldtNow);
            teamExperInfoPO.setExperPoint(nExperPoint);
            int nUpdateNum = teamExperMapper.updateExperInfo(teamExperInfoPO);
        }
        LOGGER.info("保存团队经验点数：THEME_NO={}, TEAM_NO={},TEAM_EXPER={}", teamNo, teamNo, JSONObject.toJSONString(teamExperInfoPO));
        teamExperInfoBO = zonePFileConvert.convertExperInfoPO(teamExperInfoPO);
        teamExperInfoBO.setTeamSize(nTeamSize);
        return teamExperInfoBO;
    }

    public TeamExperInfoBO calcuTeamExper(String themeNo, String accntNo)
    {
        AccntTeamInfoOUT accntTeamInfoOUT = teamInfoService.queryTeamDetail(accntNo, null);
        if (ObjectUtils.isEmpty(accntTeamInfoOUT)) {
            return null;
        }
        String strTeamNo = Optional.ofNullable(accntTeamInfoOUT).map(AccntTeamInfoOUT::getTeamNo).orElse(null);
        TeamExperInfoBO teamExperInfoBO = loadTeamExper(themeNo, strTeamNo);
        return teamExperInfoBO;
    }

    /**
     * @return
     */
    @Override
    public IPage<TeamExperInfoBO> queryExperList(ZoneThemePageIN zoneThemePageIN)
    {
        IPage<TeamExperInfoBO> page = new Page<>();
        int nPageNum = Optional.ofNullable(zoneThemePageIN).map(ZoneThemePageIN::getPageNum).orElse(1);
        int nPageSize = Optional.ofNullable(zoneThemePageIN).map(ZoneThemePageIN::getPageSize).orElse(ZonesThemePageIN.PAGE_SIZE_MAX_LIMIT);
        nPageSize = (nPageSize > ZonesThemePageIN.PAGE_SIZE_MAX_LIMIT) ? PageRequest.PAGE_SIZE_MAX_LIMIT : nPageSize;
        int nSequenceId = Optional.ofNullable(zoneThemePageIN).map(ZoneThemePageIN::getSequenceId).orElse(1);
        String themeNo = Optional.ofNullable(zoneThemePageIN).map(ZoneThemePageIN::getThemeNo).orElse(null);
        IPage<TeamExperInfoPO> pageInfo = teamExperMapper.selectExperList(new Page<TeamExperInfoPO>(nPageNum, nPageSize), themeNo);
        List<TeamExperInfoPO> teamExperInfoPOS = pageInfo.getRecords();
        List<TeamExperInfoBO> teamExperInfoBOS = Optional.ofNullable(teamExperInfoPOS).map(e -> zonePFileConvert.convertExperInfoPOS(teamExperInfoPOS)).orElse(null);
        page.setCurrent(pageInfo.getCurrent());
        page.setSize(pageInfo.getSize());
        page.setTotal((int) pageInfo.getTotal());
        page.setRecords(teamExperInfoBOS);
        return page;
    }


    @Override
    public PlayExperInfoBO queryPlayExper(String themeNo, String accntNo, String playCode)
    {
        ThemeGameInfoBO themeGameInfoBO = gameInfoService.queryGameInfo(playCode);
        if (ObjectUtils.isEmpty(themeGameInfoBO)) {
            LOGGER.info("没有找到游戏玩法信息，跳过处理：ACCNT_NO={}, PLAY_CODE={}", accntNo, playCode);
            return null;
        }
        String strPlayMode = Optional.ofNullable(themeGameInfoBO.getPlayMode()).map(PlayModeEnum::getCode).orElse(null);
        if (ObjectUtils.isEmpty(strPlayMode)) {
            LOGGER.error("游戏玩法配置模式异常：ACCNT_NO={}, PLAY_CODE={}，PLAY_MODE={}", accntNo, playCode, strPlayMode);
            return null;
        }
        PlayExperInfoPO profileExperInfoPO = playExperMapper.selectExperInfo(themeNo, accntNo, strPlayMode);
        PlayExperInfoBO profileExperInfoBO = Optional.ofNullable(profileExperInfoPO).map(e -> zonePFileConvert.convertExperInfoPO(e)).orElse(null);
        return profileExperInfoBO;
    }

    /**
     * @param themeNo
     * @param teamNo
     * @return
     */
    @Override
    public TeamExperInfoBO queryTeamExper(String themeNo, String teamNo)
    {
        TeamExperInfoPO teamExperInfoPO = teamExperMapper.selectExperInfo(themeNo, teamNo);
//        if (ObjectUtils.isEmpty(teamExperInfoPO)) {
//            LOGGER.info("没有找到团队经验点数记录，开始初始化加载：THEME_NO={}, TEAM_NO={}", themeNo,teamNo);
//            TeamExperInfoBO teamExperInfoBO=  this.loadTeamExper(themeNo,teamNo);
//            LOGGER.info("没有找到团队经验点数记录，初始化加载完成：THEME_NO={}, TEAM_NO={}, EXPER_INFO={}", themeNo,teamNo,JSONObject.toJSONString(teamExperInfoBO));
//            return teamExperInfoBO;
//        }
        TeamExperInfoBO teamExperInfoBO = Optional.ofNullable(teamExperInfoPO).map(e -> zonePFileConvert.convertExperInfoPO(e)).orElse(null);
        return teamExperInfoBO;
    }


    @Override
    public PlayModeEnum splitExperPoint(PlaceExperFlowBO placeExperFlowBO)
    {
        if (ObjectUtils.isEmpty(placeExperFlowBO)) {
            LOGGER.info("没有需要进行拆分的经验点数数据，跳过处理：EXPER_FLOW={}", JSONObject.toJSONString(placeExperFlowBO));
            return null;
        }
        String strPlayCode = placeExperFlowBO.getPlayCode();
        String strAccntNo = placeExperFlowBO.getAccountNo();
        Integer nExperPoint = placeExperFlowBO.getExperPoint();
        String strThemeNo = placeExperFlowBO.getThemeNo();
        //查询当前玩法是否存在
        ThemeGameInfoBO themeGameInfoBO = gameInfoService.queryGameInfo(strPlayCode);
        if (ObjectUtils.isEmpty(themeGameInfoBO)) {
            LOGGER.info("没有找到游戏玩法信息，跳过处理：ACCNT_NO={}, PLAY_CODE={},EXPER_POINT={}", strAccntNo, strPlayCode, nExperPoint);
            return null;
        }
        PlayModeEnum playMode = themeGameInfoBO.getPlayMode();
        String strPlayMode = Optional.ofNullable(playMode).map(PlayModeEnum::getCode).orElse(null);
        if (ObjectUtils.isEmpty(strPlayMode)) {
            LOGGER.info("用户经验点数数据不需要拆分：ACCNT_NO={}, PLAY_CODE={},PLAY_MODE={}, EXPER_POINT={}", strAccntNo, strPlayCode, strPlayMode, nExperPoint);
            return null;
        }
        LocalDateTime ldtNow = LocalDateTime.now();

        //查询拆分记录是否存在，存在则进行累计
        PlayExperInfoBO playExperInfoBO = this.queryPlayExper(strThemeNo, strAccntNo, strPlayCode);
        PlayExperInfoPO playExperInfoPO = Optional.ofNullable(playExperInfoBO).map(e -> zonePFileConvert.convertExperInfoBO(e)).orElse(null);
        if (ObjectUtils.isEmpty(playExperInfoPO)) {
            playExperInfoPO = new PlayExperInfoPO();
            //根据不同玩法构建拆分记录
            playExperInfoPO.setAccountNo(strAccntNo);
            playExperInfoPO.setExperPoint(nExperPoint);
            playExperInfoPO.setPlayMode(strPlayMode);
            playExperInfoPO.setThemeNo(strThemeNo);

            playExperInfoPO.setCreateTime(ldtNow);
            playExperInfoPO.setUpdateTime(ldtNow);
            int nInsertNum = playExperMapper.insertExperInfo(playExperInfoPO);
            if (nInsertNum <= 0) {
                LOGGER.error("根据用户拆分用户经验点数异常：ACCNT_NO={}, PLAY_CODE={},PLAY_MODE={}, EXPER_POINT={}", strAccntNo, strPlayCode, strPlayMode, nExperPoint);
                return null;
            }
        } else {
            Map<String, Object> mapKeyParam = new HashMap<>();
            mapKeyParam.put("themeNo", strThemeNo);
            mapKeyParam.put("accntNo", strAccntNo);
            mapKeyParam.put("playMode", strPlayMode);
            String strLockKey = HrmsCacheKeyUtil.cacheKey(RedisCacheEnum.LOCKD_KEY_THEME_ACCNT_PLAY, mapKeyParam);
            // 锁被占用一分钟后仍旧无法获取到锁，说明上一个修改存在问题
            boolean lock = distributedLocker.tryLock(strLockKey, TimeUnit.SECONDS, 60, 60);
            if (!lock) {
                LOGGER.error("获取用户经验值修改锁失败，修改用户经验值失败，失败信息为：SequenceId=" + playExperInfoPO.getSequenceId() + ",nExperPoint=" + nExperPoint + ",ldtNow= " + ldtNow);
                return null;
            }
            // 锁被占用一分钟后仍旧无法获取到锁，说明上一个修改存在问题
            ServiceValidate.isTrue(lock, ErrorMessageEnum.ERROR_CANNOT_OBTAIN_LOCK);
            try {
                playExperMapper.updateExperInfo(playExperInfoPO.getSequenceId(), nExperPoint, ldtNow);
            } finally {
                distributedLocker.unlock(strLockKey);
            }
        }
        LOGGER.debug("根据用户拆分用户经验点数成功：ACCNT_NO={}, PLAY_CODE={},PLAY_MODE={}, EXPER_POINT={}", strAccntNo, strPlayCode, strPlayMode, JSONObject.toJSONString(playExperInfoPO));
        return playMode;

    }

    @Override
    public Page<ZonesPfileInfoBO> queryPFileList(ThemePFilePageIN themePFilePageIN)
    {
        Page<ZonesPfileInfoBO> page = new Page<>();
        int nPageNum = Optional.ofNullable(themePFilePageIN).map(ThemePFilePageIN::getPageNum).orElse(1);
        int nPageSize = Optional.ofNullable(themePFilePageIN).map(ThemePFilePageIN::getPageSize).orElse(ZonesThemePageIN.PAGE_SIZE_MAX_LIMIT);
        nPageSize = (nPageSize > ZonesThemePageIN.PAGE_SIZE_MAX_LIMIT) ? PageRequest.PAGE_SIZE_MAX_LIMIT : nPageSize;
        int nSequenceId = Optional.ofNullable(themePFilePageIN).map(ThemePFilePageIN::getSequenceId).orElse(1);
        String strThemeNo = Optional.ofNullable(themePFilePageIN).map(ThemePFilePageIN::getThemeNo).orElse(null);
        String strTeamNo = Optional.ofNullable(themePFilePageIN).map(ThemePFilePageIN::getTeamNo).orElse(null);
        Page<ZonesPfileInfoPO> pageInfo = pFileInfoMapper.paingPFileList(new Page<ZonesPfileInfoPO>(nPageNum, nPageSize), strThemeNo, strTeamNo, nSequenceId);
        LOGGER.info("主题查询用户档案列表：THEME_NO={},TEAM_NO={},PAGE_NUM={}, PAGE_SIZE={}", strThemeNo, strTeamNo, nPageNum, nPageSize);
        List<ZonesPfileInfoBO> zonesPfileInfoBOS = Optional.ofNullable(pageInfo.getRecords()).map(e -> zonePFileConvert.convertPfileInfoPOS(e)).orElse(null);
        page.setCurrent(pageInfo.getCurrent());
        page.setSize(pageInfo.getSize());
        page.setTotal((int) pageInfo.getTotal());
        page.setRecords(zonesPfileInfoBOS);
        return page;
    }


    @Transactional(rollbackFor = Exception.class)
    public ZonesPfileInfoPO createPFileDetail(String themeNo, String accntNo)
    {
        ZonesPfileInfoPO zonesPfileInfoPO = new ZonesPfileInfoPO();
        zonesPfileInfoPO.setAccountNo(accntNo);
        String strPfileNo = HrmsSequenceGenerator.dictionaryNo(ZonesSequenEnum.PRO_FILE_CODE);
        zonesPfileInfoPO.setPfileNo(strPfileNo);
        zonesPfileInfoPO.setProteState("00");
        zonesPfileInfoPO.setExperPoint(0);
        pFileInfoMapper.insertPfileDetail(zonesPfileInfoPO);
        //系统分配默认头像
        zonesPfileInfoPO.setHeadType(HeadTypeEnum.HEAD_TYPE_AUTO.getCode());
        zonesPfileInfoPO.setHeadMode(HeadModeEnum.HEAD_MODE_AUTO.getCode());
        ThemeHeadInfoBO themeHeadInfoBO = zoneInfoService.randHeadList(themeNo);
        String strHeadImage = Optional.ofNullable(themeHeadInfoBO).map(ThemeHeadInfoBO::getHeadImage).orElse(null);
        String strHeadNo = Optional.ofNullable(themeHeadInfoBO).map(ThemeHeadInfoBO::getHeadNo).orElse(null);
        zonesPfileInfoPO.setHeadImage(strHeadImage);
        zonesPfileInfoPO.setHeadNo(strHeadNo);
        Date dtUpdateTime = DateTimeUtils.localDateTime2Date(LocalDateTime.now());
        zonesPfileInfoPO.setUpdateTime(dtUpdateTime);
        pFileInfoMapper.updatePfileDetail(zonesPfileInfoPO);
        LOGGER.info("根据帐号编号创建用户档案：THEME_NO={},ACCNT_NO={}, PFILE_INFO={}", themeNo, accntNo, JSONObject.toJSONString(zonesPfileInfoPO));
        return zonesPfileInfoPO;
    }

    @Async("ThreadPoolTask")
    @Override
    public PlaceExperFlowPO writeExperFlow(PlaceExperFlowBO placeExperFlowBO)
    {
        if (ObjectUtils.isEmpty(placeExperFlowBO)) {
            return null;
        }
        String strAccntNo = placeExperFlowBO.getAccountNo();
        PlaceExperFlowPO placeExperFlowPO = zonePFileConvert.convertExperFlowBO(placeExperFlowBO);
        LocalDateTime ldtNow = LocalDateTime.now();
        placeExperFlowPO.setCreateTime(ldtNow);
        placeExperFlowPO.setUpdateTime(ldtNow);
        int nInsertNum = experFlowMapper.insertExperFlow(placeExperFlowPO);
        if (nInsertNum <= 0) {
            return null;
        }
        LOGGER.info("保存用户经验点数记录：ACCN_NO={},EXPER_FLOW={}", strAccntNo, JSONObject.toJSONString(placeExperFlowPO));
        return placeExperFlowPO;
    }

    /**
     * @param accntNos
     * @return
     */
    @Override
    public List<ZonesPfileInfoPO> queryPFileList(List<String> accntNos)
    {
        if (ObjectUtils.isEmpty(accntNos)) {
            return null;
        }
        List<ZonesPfileInfoPO> zonesPfileInfoPOS = pFileInfoMapper.selectPFileList(accntNos);
        return zonesPfileInfoPOS;
    }

    /**
     * @param accntNo
     * @param experNos
     * @return
     */
    @Override
    public List<PlaceExperFlowBO> queryExperFlow(String accntNo, List<String> experNos)
    {
        List<PlaceExperFlowPO> placeExperFlowPOS = experFlowMapper.selectExperFlow(accntNo, experNos);
        List<PlaceExperFlowBO> placeExperFlowBOS = Optional.ofNullable(placeExperFlowPOS).map(e -> zonePFileConvert.convertExperFlowPOS(e)).orElse(Collections.emptyList());
        LOGGER.info("查询用户经验点数记录：ACCNT_NO={}, EXPER_NOS={}, EXPER_FLOW={}", accntNo, experNos, JSONObject.toJSONString(placeExperFlowBOS));
        return placeExperFlowBOS;
    }

    @Override
    public boolean modifyExperPoint(String themeNo, String accntNo, Integer experPoint)
    {
        Map<String, Object> mapKeyParam = new HashMap<>();
        mapKeyParam.put("themeNo", themeNo);
        mapKeyParam.put("accntNo", accntNo);
        String strLockKey = HrmsCacheKeyUtil.cacheKey(RedisCacheEnum.LOCKD_KEY_THEME_ACCNT_PFILE, mapKeyParam);
        // 锁被占用一分钟后仍旧无法获取到锁，说明上一个修改存在问题
        boolean lock = distributedLocker.tryLock(strLockKey, TimeUnit.SECONDS, 60, 60);
        if (!lock) {
            LOGGER.error("获取用户经验值修改锁失败，修改用户经验值失败，失败信息为：themeNo=" + themeNo + ",accntNo=" + accntNo + ",experPoint= " + experPoint);
            return false;
        }
        try {
            ZonesPFileBaseIN zonesPFileBaseIN = new ZonesPFileBaseIN();
            zonesPFileBaseIN.setAccountNo(accntNo);
            zonesPFileBaseIN.setThemeNo(themeNo);
            ZonesPfileInfoPO zonesPfileInfoPO = this.queryPFileDetail(zonesPFileBaseIN);
            if (ObjectUtils.isEmpty(zonesPfileInfoPO)) {
                throw new HrmsException(ErrorMessageEnum.ERROR_PFILE_NOT_EXIST);
            }
            String strPFileNo = zonesPfileInfoPO.getPfileNo();
            Date dtUpdateTime = DateTimeUtils.localDateTime2Date(LocalDateTime.now());
            int nUpdateNum = pFileInfoMapper.updateExperPoint(strPFileNo, experPoint, dtUpdateTime);
            if (nUpdateNum <= 0) {
                LOGGER.error("更新用户档案经验点数失败：ACCN_NO={},EXPER_POINT={}, UPDATE_TIME={}", accntNo, experPoint, dtUpdateTime);
                return false;
            }
            LOGGER.info("更新用户档案经验点数：ACCN_NO={},EXPER_POINT={}, UPDATE_TIME={}", accntNo, experPoint, dtUpdateTime);
            return true;
        } finally {
            distributedLocker.unlock(strLockKey);
        }
    }

    /**
     * @param accntNo
     * @param headNo
     * @return
     */
    @Override
    public ZonesPfileInfoBO modifyHeadDetail(String themeNo, String accntNo, String headNo, HeadTypeEnum headType, HeadModeEnum headMode)
    {
        String strHeadType = Optional.ofNullable(headType).map(HeadTypeEnum::getCode).orElseThrow(() -> new HrmsException(ErrorMessageEnum.RESULT_CODE_997));
        String strHeadMode = Optional.ofNullable(headMode).map(HeadModeEnum::getCode).orElseThrow(() -> new HrmsException(ErrorMessageEnum.RESULT_CODE_997));
        ZonesPFileBaseIN zonesPFileBaseIN = new ZonesPFileBaseIN();
        zonesPFileBaseIN.setAccountNo(accntNo);
        zonesPFileBaseIN.setThemeNo(themeNo);
        ZonesPfileInfoPO zonesPfileInfoPO = this.queryPFileDetail(zonesPFileBaseIN);
        if (ObjectUtils.isEmpty(zonesPfileInfoPO)) {
            throw new HrmsException(ErrorMessageEnum.ERROR_PFILE_NOT_EXIST);
        }

        switch (headType) {
            case HEAD_TYPE_AUTO:
                ThemeHeadInfoBO themeHeadInfoBO = zoneInfoService.queryHeadDetail(themeNo, headNo);
                String strHeadImage = Optional.ofNullable(themeHeadInfoBO).map(ThemeHeadInfoBO::getHeadImage).orElse(null);
                String strHeadNo = Optional.ofNullable(themeHeadInfoBO).map(ThemeHeadInfoBO::getHeadNo).orElse(null);
                if (!ObjectUtils.isEmpty(strHeadImage)) {
                    zonesPfileInfoPO.setHeadImage(strHeadImage);
                    zonesPfileInfoPO.setHeadType(strHeadType);
                    zonesPfileInfoPO.setHeadNo(strHeadNo);
                    zonesPfileInfoPO.setHeadMode(strHeadMode);
                    Date dtUpdateTime = DateTimeUtils.localDateTime2Date(LocalDateTime.now());
                    zonesPfileInfoPO.setUpdateTime(dtUpdateTime);
                    int nUpdateNum = pFileInfoMapper.updatePfileDetail(zonesPfileInfoPO);
                    LOGGER.info("更新帐号用户档案头像：THEME_NO={}, ACCNT_NO={}, HEAD_NO={}, HEAD_TYPE={}, HEAD_MODE={}, UPDATE_NUM={}", themeNo, accntNo, headNo, headType, headMode, nUpdateNum);
                }
                break;
            default:
                break;
        }
        ZonesPfileInfoBO zonesPfileInfoBO = zonePFileConvert.convertPfileInfoPO(zonesPfileInfoPO);
        return zonesPfileInfoBO;
    }


    private ZonesPfileInfoPO adjustPFileTeam(ZonesPfileInfoPO zonesPfileInfoPO)
    {
        if (ObjectUtils.isEmpty(zonesPfileInfoPO)) {
            return null;
        }
        String strAccntNo = zonesPfileInfoPO.getAccountNo();
        //根据帐号查询归属团队信息
        AccntTeamInfoOUT accntTeamInfoOUT = teamInfoService.queryTeamDetail(strAccntNo, null);
        String strTeamNo = Optional.ofNullable(accntTeamInfoOUT).map(AccntTeamInfoOUT::getTeamNo).orElse(null);
        //更新用户档案数据
        zonesPfileInfoPO.setTeamNo(strTeamNo);
        int nUpdateNum = pFileInfoMapper.updatePfileDetail(zonesPfileInfoPO);
        if (nUpdateNum <= 0) {
            LOGGER.error("调整用户关联的团队编号异常：ACCNT_NO={}, TEAM_NO={}", strAccntNo, strTeamNo);
            return null;
        }
        return zonesPfileInfoPO;
    }

    @Override
    public ZonesPfileInfoPO queryPFileDetail(ZonesPFileBaseIN zonesPFileBaseIN)
    {
        String strAccntNo = Optional.ofNullable(zonesPFileBaseIN).map(ZonesPFileBaseIN::getAccountNo).orElseThrow(() -> new HrmsException(ErrorMessageEnum.RESULT_CODE_997));
        String strThemeNo = Optional.ofNullable(zonesPFileBaseIN).map(ZonesPFileBaseIN::getThemeNo).orElseThrow(() -> new HrmsException(ErrorMessageEnum.RESULT_CODE_997));
        List<ZonesPfileInfoPO> zonesPfileInfoPOS = pFileInfoMapper.selectPFileList(Arrays.asList(strAccntNo));
        if (ObjectUtils.isEmpty(zonesPfileInfoPOS)) {
            LOGGER.info("帐号档案数据不存在，创建用户档案数据：THEME_NO={}, ACCOUNT_NO={}", strThemeNo, strAccntNo);
            ZonesPfileInfoPO zonesPfileInfoPO = this.createPFileDetail(strThemeNo, strAccntNo);
            return zonesPfileInfoPO;
        }
        ZonesPfileInfoPO zonesPfileInfoPO = zonesPfileInfoPOS.get(0);
        zonesPfileInfoPO = this.adjustPFileTeam(zonesPfileInfoPO);
        LOGGER.info("查询帐号用户档案数据：THEME_NO={}, ACCOUNT_NO={}, PFILE_INFO={}", strThemeNo, strAccntNo, JSONObject.toJSONString(zonesPfileInfoPO));
        return zonesPfileInfoPO;
    }
}
