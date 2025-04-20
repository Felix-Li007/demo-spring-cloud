package demo.sport.zones.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import demo.sport.zones.domain.PlaceThemeInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * @Entity com.hrms.sport.zones.entity.po.PlaceThemeInfoPO
 */
@Mapper
public interface IThemeInfoMapper
{


    PlaceThemeInfoPO selectThemeInfo(@Param("themeNo") String themeNo);

    Page<PlaceThemeInfoPO> queryThemeList(Page<PlaceThemeInfoPO> page, @Param("themeDesc") String themeDesc);

    void insertSelective(PlaceThemeInfoPO zonesThemeInfoPO);

    void updateThemeInfo(PlaceThemeInfoPO zonesThemeInfoPO);

    /**
     * 关闭场馆
     *
     * @param now         当前时间
     * @param closeStatus 关闭状态
     */
    void closePlaceTheme(@Param("now") LocalDateTime now, @Param("closeStatus") String closeStatus);


    /**
     * 开放场馆
     *
     * @param openStatus 开放状态
     * @param now        当前时间
     */
    void openPlaceTheme(@Param("now") LocalDateTime now, @Param("openStatus") String openStatus);
}
