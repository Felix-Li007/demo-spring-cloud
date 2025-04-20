package demo.sport.zones.mapper;

import demo.sport.zones.domain.CionLimitInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ICionLimitMapper
{

    /**
     * 根据主题编号查询货币的使用限制数据
     *
     * @param cionCode
     * @return
     */
    List<CionLimitInfoPO> selectLimitList(@Param("cionCode") String cionCode);

    void deleteByThemeNo(@Param("themeNo") String themeNo);

    void insertSelective(CionLimitInfoPO cionLimitPO);
}
