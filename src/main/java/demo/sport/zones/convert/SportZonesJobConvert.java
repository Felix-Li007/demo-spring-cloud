package demo.sport.zones.convert;

import demo.sport.domain.zones.UpThemeHeadInfoIn;
import demo.sport.zones.domain.ThemeHeadInfoPO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Date;
import java.util.List;

@Mapper(imports = {Date.class}, componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SportZonesJobConvert
{

    List<ThemeHeadInfoPO> toThemeHeadInfoPOs(List<UpThemeHeadInfoIn> upInfos);

    @Mapping(target = "createTime", expression = "java(new Date())")
    @Mapping(target = "updateTime", expression = "java(new Date())")
    ThemeHeadInfoPO toThemeHeadInfoPO(UpThemeHeadInfoIn info);
}
