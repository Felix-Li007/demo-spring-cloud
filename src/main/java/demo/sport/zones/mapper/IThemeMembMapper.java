package demo.sport.zones.mapper;

import demo.sport.zones.domain.ZonesThemeMembPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface IThemeMembMapper
{


    List<ZonesThemeMembPO> selectMembList(String themeNo);
}
