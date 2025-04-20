package demo.sport.zones.common.utils;

import demo.sport.domain.zones.ZonesThemePageIN;

import java.util.Optional;

/**
 * @author: houpengyi
 * @date: 2022/9/29 14:33
 * @description:
 */
public class ThemePageUtil
{
    public static ZonesThemePageIN checkThemePageInfo(ZonesThemePageIN zonesThemePageIn)
    {
        int nPageNum = Optional.ofNullable(zonesThemePageIn).map(ZonesThemePageIN::getPageNum).orElse(1);
        int nPageSize = Optional.ofNullable(zonesThemePageIn).map(ZonesThemePageIN::getPageSize).orElse(ZonesThemePageIN.PAGE_SIZE_MAX_LIMIT);
        nPageSize = (nPageSize > ZonesThemePageIN.PAGE_SIZE_MAX_LIMIT) ? ZonesThemePageIN.PAGE_SIZE_MAX_LIMIT : nPageSize;
        zonesThemePageIn.setPageSize(nPageSize);
        zonesThemePageIn.setPageNum(nPageNum);
        return zonesThemePageIn;
    }
}
