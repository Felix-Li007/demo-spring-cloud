package demo.sport.zones.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.core.domian.PageResponse;
import com.hrms.core.domian.ResultOut;
import demo.sport.domain.zones.ZonesThemePageIN;
import demo.sport.domain.zones.ZonesZoneBaseIN;
import demo.sport.domain.zones.ZonesZoneDetailOUT;
import demo.sport.domain.zones.ZonesZoneInfoOUT;
import demo.sport.zones.domain.ZonesMessInfoPO;
import demo.sport.zones.entity.GameExperInfoBO;
import demo.sport.zones.entity.PlaceExperFlowBO;
import demo.sport.zones.entity.ThemeHeadInfoBO;

import java.util.List;


/**
 *
 */
public interface IZoneInfoService
{

    void modifyZoneState(String zoneNo, String zoneState);

    void evictZoneInfo(String zoneNo);

    PlaceExperFlowBO calcuExperPoint(PlaceExperFlowBO placeExperFlowBO);

    /**
     * 根据场馆编号查询场馆详情
     *
     * @param zonesZoneIN
     * @return
     */
    ZonesZoneDetailOUT queryZoneDetail(ZonesZoneBaseIN zonesZoneIN);

    /**
     * 根据场馆编号查询场馆详情
     *
     * @param zonesThemePageIn
     * @return
     */
    ResultOut<PageResponse<ZonesZoneInfoOUT>> queryZoneList(ZonesThemePageIN zonesThemePageIn);

    Page<ThemeHeadInfoBO> queryHeadList(ZonesThemePageIN zonesThemePageIN);


    ThemeHeadInfoBO randHeadList(String themeNo);

    ThemeHeadInfoBO queryHeadDetail(String themeNo, String headNo);

    List<GameExperInfoBO> queryExperList(String themeNo, String zoneNo);

    Page<ZonesMessInfoPO> queryMessList(ZonesThemePageIN zonesThemePageIN);


}
