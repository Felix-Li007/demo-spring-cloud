package demo.sport.zones.service;

import demo.sport.domain.zones.UpThemeHeadInfoIn;

import java.util.List;

public interface ZonesJobService
{

    void updateThemeHeadInfo(List<UpThemeHeadInfoIn> upInfos);

    /**
     * 更新场馆状态
     */
    void changePlaceOpenStatus();
}
