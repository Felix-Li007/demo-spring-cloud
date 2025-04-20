package demo.sport.zones.service.impl;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.hrms.core.domian.PageResponse;
import com.hrms.gateway.api.NodeApiHelper;
import com.hrms.gateway.collect.DataCollectDto;
import com.hrms.gateway.collect.DataCollectProcess;
import demo.sport.domain.theme.PlaceThemeInfoOUT;
import demo.sport.domain.zones.ZoneThemePageIN;
import demo.sport.zones.common.config.DatacsDataConfig;
import demo.sport.zones.convert.IGameConvert;
import demo.sport.zones.entity.PlayTaskInfoBO;
import demo.sport.zones.entity.RankTaskInfoBO;
import demo.sport.zones.mapper.IGameInfoMapper;
import demo.sport.zones.service.IGatheInfoService;
import demo.sport.zones.service.IRviewCallService;
import demo.sport.zones.service.IThemeInfoService;
import com.psdn.review.domain.rank.ReviewRankItemOUT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;


/**
 *
 */
@Service
public class GatheInfoServiceImpl implements IGatheInfoService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(GatheInfoServiceImpl.class);

    @Resource
    private IGameInfoMapper gameInfoMapper;

    @Resource
    private IGameConvert gameConvert;


    @Resource
    private DatacsDataConfig datacsDataConfig;

    @Resource
    private IThemeInfoService themeInfoService;

    @Resource
    private IRviewCallService rviewCallService;

    @Resource
    private NodeApiHelper nodeApiHelper;

    @Async("ThreadPoolTask")
    @Override
    public void gathePlayTask(String accntNo, String playCode)
    {
        LOGGER.info("开始玩法投注成功数据推送到公共任务服务");
        ZoneThemePageIN zoneThemePageIN = new ZoneThemePageIN();
        zoneThemePageIN.setPageNum(1);
        zoneThemePageIN.setPageSize(-1);
        List<PlaceThemeInfoOUT> placeThemeInfoOUTS = Optional.ofNullable(themeInfoService.queryThemeList(zoneThemePageIN)).map(PageResponse::getRecords).orElse(Collections.emptyList());
        placeThemeInfoOUTS.stream().forEach(placeThemeInfoOUT -> {
            String strThemeNo = placeThemeInfoOUT.getThemeNo();
            LOGGER.info("开始处理主题下的玩法数据推送到公共任务服务：THEME_NO={}", strThemeNo);
            List<DatacsDataConfig.PlayDataConfig> playDataConfigs = datacsDataConfig.queryPlayConfig(strThemeNo);
            if (ObjectUtils.isEmpty(playDataConfigs) || ObjectUtils.isEmpty(playDataConfigs)) {
                LOGGER.info("没有找到任务数据配置，直接结束玩法投注成功数据推送");
                return;
            }
            CompletableFuture[] futureList = playDataConfigs.stream().map(playDataConfig -> {
                return CompletableFuture.runAsync(() -> {
                    String strRankDcmi = playDataConfig.getPlayDcmi();
                    String strPlayCode = playDataConfig.getPlayCode();
                    String strPlayName = playDataConfig.getPlayName();
                    String[] dcmiArray = Optional.ofNullable(strRankDcmi).map(e -> e.split("\\-")).orElse(null);
                    String strReqApiC = Optional.ofNullable(dcmiArray[0]).orElse(null);
                    String strReqApiD = Optional.ofNullable(dcmiArray[1]).orElse(null);
                    String strReqApiM = Optional.ofNullable(dcmiArray[2]).orElse(null);
                    DataCollectDto dataCollectDto = new DataCollectDto();
                    dataCollectDto.setAccountNo(accntNo);
                    dataCollectDto.setReqCommon(ImmutableMap.of("accountNo", accntNo));
                    dataCollectDto.setReqApiC(strReqApiC);
                    dataCollectDto.setReqApiD(strReqApiD);
                    dataCollectDto.setReqApiM(strReqApiM);
                    PlayTaskInfoBO playTaskInfoBO = new PlayTaskInfoBO();
                    playTaskInfoBO.setAccountNo(accntNo);
                    playTaskInfoBO.setPlayName(strPlayName);
                    playTaskInfoBO.setPlayCode(strPlayCode);
                    dataCollectDto.setReqData(playTaskInfoBO);
                    DataCollectProcess.dataCollect(nodeApiHelper, dataCollectDto);
                });
            }).toArray(CompletableFuture[]::new);
            futureList = Arrays.stream(futureList).filter(Objects::nonNull).toArray(CompletableFuture[]::new);
            CompletableFuture.allOf(futureList).join();
        });
        LOGGER.info("结束处理玩法投注成功数据推送到公共任务任务");
    }

    @Override
    public void gatheRankTask(LocalDateTime rankTime)
    {
        //查询所有的主题列表
        ZoneThemePageIN zoneThemePageIN = new ZoneThemePageIN();
        zoneThemePageIN.setPageNum(1);
        zoneThemePageIN.setPageSize(-1);
        List<PlaceThemeInfoOUT> placeThemeInfoOUTS = Optional.ofNullable(themeInfoService.queryThemeList(zoneThemePageIN)).map(PageResponse::getRecords).orElse(Collections.emptyList());
        placeThemeInfoOUTS.stream().forEach(placeThemeInfoOUT -> {
            String strThemeNo = placeThemeInfoOUT.getThemeNo();
            LOGGER.info("开始处理主题下的榜单数据推送到公共任务服务：THEME_NO={}", strThemeNo);
            List<DatacsDataConfig.RankDataConfig> rankDataConfigs = datacsDataConfig.queryRankConfig(strThemeNo);
            if (ObjectUtils.isEmpty(rankDataConfigs)) {
                LOGGER.info("结束处理主题下的榜单数据推送到公共任务任务：THEME_NO={}", strThemeNo);
                return;
            }
            CompletableFuture[] futureList = rankDataConfigs.stream().map(rankDataConfig -> {
                return CompletableFuture.runAsync(() -> {
                    String strRankCode = rankDataConfig.getRankCode();
                    String strRangCode = rankDataConfig.getRangCode();
                    String strMetriCode = rankDataConfig.getMetriCode();
                    String strRangValue = rankDataConfig.getRangValue();
                    String strRankDcmi = rankDataConfig.getRankDcmi();
                    int nRankTopn = rankDataConfig.getRankTopn();
                    String[] dcmiArray = Optional.ofNullable(strRankDcmi).map(e -> e.split("\\-")).orElse(null);
                    String strReqApiC = Optional.ofNullable(dcmiArray[0]).orElse(null);
                    String strReqApiD = Optional.ofNullable(dcmiArray[1]).orElse(null);
                    String strReqApiM = Optional.ofNullable(dcmiArray[2]).orElse(null);
                    int nPageNum = 1;
                    int nPageCount = 0;
                    int nPageSize = 500;
                    do {
                        PageResponse<ReviewRankItemOUT> pageResponse = rviewCallService.queryRankItem(strRankCode, strRangCode, strMetriCode, strRangValue, rankTime, nPageNum, nPageSize);
                        if (ObjectUtils.isEmpty(pageResponse) || ObjectUtils.isEmpty(pageResponse.getRecords())) {
                            return;
                        }
                        List<ReviewRankItemOUT> reviewRankItemOUTS = pageResponse.getRecords();
                        reviewRankItemOUTS.forEach(reviewRankItemOUT -> {
                            RankTaskInfoBO rankTaskInfoBO = new RankTaskInfoBO();
                            rankTaskInfoBO.setRankObject(reviewRankItemOUT.getRankObject());
                            rankTaskInfoBO.setRankCode(reviewRankItemOUT.getRankCode());
                            rankTaskInfoBO.setRankSeat(reviewRankItemOUT.getRankSeat());
                            rankTaskInfoBO.setRankTime(reviewRankItemOUT.getRankTime());
                            rankTaskInfoBO.setRankType(reviewRankItemOUT.getRankType());
                            rankTaskInfoBO.setDiffSeat(reviewRankItemOUT.getDiffSeat());
                            rankTaskInfoBO.setMetriCode(reviewRankItemOUT.getMetriCode());
                            rankTaskInfoBO.setRangCode(reviewRankItemOUT.getRangCode());
                            String strAccntNo = rankTaskInfoBO.getRankObject();
                            DataCollectDto dataCollectDto = new DataCollectDto();
                            dataCollectDto.setAccountNo(strAccntNo);
                            dataCollectDto.setReqCommon(ImmutableMap.of("accountNo", strAccntNo));
                            dataCollectDto.setReqApiC(strReqApiC);
                            dataCollectDto.setReqApiD(strReqApiD);
                            dataCollectDto.setReqApiM(strReqApiM);
                            dataCollectDto.setReqData(rankTaskInfoBO);
                            DataCollectProcess.dataCollect(nodeApiHelper, dataCollectDto);
                            LOGGER.info("推送榜单排名数据到公共任务服务：RANK_CODE={}, RANG_CODE={}, METRI_CODE={},RANG_VALUE={},DATA_COLL={}", strRangCode, strRangCode, strMetriCode, strRangValue, JSON.toJSONString(dataCollectDto));

                        });
                        nPageNum++;
                    } while (nPageNum <= nPageCount || ((nPageNum - 1) * nPageSize) < nRankTopn);
                });
            }).toArray(CompletableFuture[]::new);
            futureList = Arrays.stream(futureList).filter(Objects::nonNull).toArray(CompletableFuture[]::new);
            CompletableFuture.allOf(futureList).join();
            LOGGER.info("结束处理主题下的榜单数据推送到公共任务任务：THEME_NO={}", strThemeNo);
        });

    }

}
