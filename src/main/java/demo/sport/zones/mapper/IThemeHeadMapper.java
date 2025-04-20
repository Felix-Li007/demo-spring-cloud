package demo.sport.zones.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import demo.sport.zones.domain.ThemeHeadInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author lifeng
 * @description 针对表【t_place_head_info(默认头像列表)】的数据库操作Mapper
 * @createDate 2022-10-09 16:07:41
 * @Entity generator.domain.TPlaceHeadInfo
 */
@Mapper
public interface IThemeHeadMapper
{

    Page<ThemeHeadInfoPO> selectHeadList(Page<ThemeHeadInfoPO> page, @Param("themeNo") String themeNo, @Param("sequenceId") Integer sequenceId);

    ThemeHeadInfoPO selectHeadDetail(@Param("themeNo") String themeNo, @Param("headNo") String headNo);

    List<ThemeHeadInfoPO> getList();

    int deleteNotInSequenceIds(@Param("sequenceIds") List<Integer> sequenceIds);

    int insertBatch(@Param("themeHeadInfoPOCollection") Collection<ThemeHeadInfoPO> themeHeadInfoPOCollection);

    int updateSelective(ThemeHeadInfoPO themeHeadInfoPO);
}
