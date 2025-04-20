package demo.sport.zones.mapper;

import demo.sport.zones.domain.ThemeTaskInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IThemeTaskMapper
{
    List<ThemeTaskInfoPO> selectTaskList(@Param("themeNo") String themeNo);
}
