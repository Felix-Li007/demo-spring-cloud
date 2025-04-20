package demo.sport.zones.service;


import com.hrms.core.domian.PageResponse;
import demo.sport.domain.theme.PlaceThemeInfoOUT;
import demo.sport.domain.theme.ThemeDetailCmsOUT;
import demo.sport.domain.theme.ThemeTaskInfoOUT;
import demo.sport.domain.zones.ZoneThemePageIN;
import demo.sport.domain.zones.ZonesCionInfoOUT;
import demo.sport.domain.zones.ZonesThemeBaseIN;
import demo.sport.zones.domain.PlaceThemeInfoPO;
import demo.sport.zones.domain.ZonesThemeRankPO;
import com.hrms.sport.zones.entity.*;
import demo.sport.zones.entity.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 */
public interface IThemeInfoService
{

    void evictThemeInfo(String themeNo);

    List<RankGameInfoBO> queryGameList(String playCode);

    PlaceThemeInfoOUT queryThemeInfo(ZonesThemeBaseIN zonesThemeBaseIN);

    /**
     * 根据主题编号查询货币限制数据
     *
     * @param cionCode
     * @return
     */
    List<CionLimitInfoBO> queryLimitList(String cionCode);

    List<ZonesCionInfoOUT> queryCionList(String themeNo);

    List<ThemeTaskInfoOUT> queryTaskList(String themeNo);


    PageResponse<PlaceThemeInfoOUT> queryThemeList(ZoneThemePageIN zoneThemePageIN);

    List<ZonesThemeRankPO> queryRankList(ZonesThemeBaseIN zonesThemeBaseIN);

    List<ThemeMedalInfoBO> queryMedalList(String themeNo);

    List<ThemeZoneInfoBO> queryZoneList(String zoneNo);

    void addThemeInfo(PlaceThemeInfoPO zonesThemeInfoPO);

    PlaceThemeInfoPO selectDetailByThemeNo(String themeNo);

    void updateThemeInfo(PlaceThemeInfoPO zonesThemeInfoPO);

    ThemeDetailCmsOUT queryCmsThemeDetail(ZonesThemeBaseIN zonesThemeBaseIN);

    /**
     * 关闭场馆
     *
     * @param now         当前时间
     * @param closeStatus 关闭状态
     */
    void closePlaceTheme(LocalDateTime now, String closeStatus);

    /**
     * 打开场馆
     *
     * @param now        当前时间
     * @param openStatus 开放状态
     */
    void openPlaceTheme(LocalDateTime now, String openStatus);

    PlaceThemeInfoBO queryThemeInfo(String themeNo);
}
