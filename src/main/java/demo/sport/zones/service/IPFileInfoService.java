package demo.sport.zones.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import demo.sport.constants.zones.HeadModeEnum;
import demo.sport.constants.zones.HeadTypeEnum;
import demo.sport.constants.zones.PlayModeEnum;
import demo.sport.domain.zones.ThemePFilePageIN;
import demo.sport.domain.zones.ZoneThemePageIN;
import demo.sport.domain.zones.ZonesPFileBaseIN;
import demo.sport.zones.domain.PlaceExperFlowPO;
import demo.sport.zones.domain.ZonesPfileInfoPO;
import demo.sport.zones.entity.PlaceExperFlowBO;
import demo.sport.zones.entity.PlayExperInfoBO;
import demo.sport.zones.entity.TeamExperInfoBO;
import demo.sport.zones.entity.ZonesPfileInfoBO;

import java.util.List;

/**
 *
 */
public interface IPFileInfoService
{
    /**
     * 保存用户经验点数记录
     *
     * @param placeExperFlowBO
     * @return
     */
    PlaceExperFlowPO writeExperFlow(PlaceExperFlowBO placeExperFlowBO);

    List<PlaceExperFlowBO> queryExperFlow(String accntNo, List<String> experNos);


    boolean modifyExperPoint(String themeNo, String accntNo, Integer experPoint);

    ZonesPfileInfoBO modifyHeadDetail(String themeNo, String accntNo, String headNo, HeadTypeEnum headType, HeadModeEnum headMode);

    ZonesPfileInfoPO queryPFileDetail(ZonesPFileBaseIN zonesPFileBaseIN);

    List<ZonesPfileInfoPO> queryPFileList(List<String> accntNos);

    PlayModeEnum splitExperPoint(PlaceExperFlowBO placeExperFlowBO);

    Page<ZonesPfileInfoBO> queryPFileList(ThemePFilePageIN themePFilePageIN);

    PlayExperInfoBO queryPlayExper(String themeNo, String accntNo, String playMode);


    TeamExperInfoBO queryTeamExper(String themeNo, String teamNo);


    TeamExperInfoBO calcuTeamExper(String themeNo, String accntNo);


    IPage<TeamExperInfoBO> queryExperList(ZoneThemePageIN zoneThemePageIN);

}
