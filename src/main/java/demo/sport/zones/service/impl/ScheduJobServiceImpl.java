package demo.sport.zones.service.impl;

import com.hrms.core.domian.PageResponse;
import demo.sport.constants.zones.OpenStateEnum;
import demo.sport.domain.theme.PlaceThemeInfoOUT;
import demo.sport.domain.zones.UpThemeHeadInfoIn;
import demo.sport.domain.zones.ZoneThemePageIN;
import demo.sport.zones.common.config.DatacsDataConfig;
import demo.sport.zones.convert.SportZonesJobConvert;
import demo.sport.zones.domain.ThemeHeadInfoPO;
import demo.sport.zones.manager.IThemeHeadManager;
import demo.sport.zones.service.IThemeInfoService;
import demo.sport.zones.service.ZonesJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ScheduJobServiceImpl implements ZonesJobService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduJobServiceImpl.class);
    @Resource
    private SportZonesJobConvert sportZonesJobConvert;
    @Resource
    private IThemeHeadManager themeHeadManager;
    @Resource
    private IThemeInfoService themeInfoService;

    @Resource
    private DatacsDataConfig gatherRankConfig;


    void test()
    {
        //查询出所有的主题列表
        PageResponse<PlaceThemeInfoOUT> pageResponse = themeInfoService.queryThemeList(new ZoneThemePageIN());
        List<PlaceThemeInfoOUT> placeThemeInfoOUTS = Optional.ofNullable(pageResponse).map(PageResponse::getRecords).orElse(Collections.emptyList());

        placeThemeInfoOUTS.stream().forEach(placeThemeInfoOUT -> {
            String strThemeNo = placeThemeInfoOUT.getThemeNo();
            List<DatacsDataConfig.RankDataConfig> rankTopnConfigs = gatherRankConfig.queryRankConfig(strThemeNo);
            if (ObjectUtils.isEmpty(rankTopnConfigs)) {
                return;
            }
            rankTopnConfigs.stream().forEach(rankTopnConfig -> {
                String strRankCode = rankTopnConfig.getRankCode();
                int nRankTopn = rankTopnConfig.getRankTopn();

            });
        });


    }

    @Override
    public void updateThemeHeadInfo(List<UpThemeHeadInfoIn> upInfos)
    {
        List<ThemeHeadInfoPO> themeHeadInfoPOS = sportZonesJobConvert.toThemeHeadInfoPOs(upInfos);
        List<ThemeHeadInfoPO> existThemeHeadInfoPOS = themeHeadManager.getList();
        Map<String, ThemeHeadInfoPO> themeHeadInfoPOMap = existThemeHeadInfoPOS.stream().collect(Collectors.toMap(ThemeHeadInfoPO::getHeadNo, Function.identity()));
        themeHeadInfoPOS.forEach(themeHeadInfoPO -> {
            ThemeHeadInfoPO existPo = themeHeadInfoPOMap.get(themeHeadInfoPO.getHeadNo());
            if (Objects.nonNull(existPo)) {
                themeHeadInfoPO.setSequenceId(existPo.getSequenceId());
                themeHeadInfoPO.setCreateTime(null);
            }
        });

        themeHeadManager.saveOrUpdate(themeHeadInfoPOS);
    }

    @Override
    public void changePlaceOpenStatus()
    {
        LOGGER.info("开始自动更新场馆状态任务");

        LocalDateTime now = LocalDateTime.now();
        // 开放到期的场馆
        try {
            themeInfoService.openPlaceTheme(now, OpenStateEnum.ZONE_STATE_OPENING.getCode());
        } catch (Exception e) {
            LOGGER.error("自动开放场馆失败", e);
        }
        // 关闭到期的场馆
        try {
            themeInfoService.closePlaceTheme(now, OpenStateEnum.ZONE_STATE_CLOSED.getCode());
        } catch (Exception e) {
            LOGGER.error("自动关闭场馆失败", e);
        }

        LOGGER.info("结束自动更新场馆状态任务");
    }
}
