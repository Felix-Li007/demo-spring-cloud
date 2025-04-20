package demo.sport.zones.convert;

import demo.sport.domain.theme.ThemeMedalInfoOUT;
import demo.sport.domain.theme.ThemeTaskInfoOUT;
import demo.sport.zones.domain.ThemeMedalInfoPO;
import demo.sport.zones.domain.ThemeTaskInfoPO;
import demo.sport.zones.entity.ThemeMedalInfoBO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IZonesMedalConvert
{


    List<ThemeMedalInfoOUT> convertMedalOUTS(List<ThemeMedalInfoPO> records);

    List<ThemeMedalInfoBO> convertMedalInfoPOS(List<ThemeMedalInfoPO> themeMedalInfoPOS);

    List<ThemeTaskInfoOUT> convertTaskInfoOUTS(List<ThemeTaskInfoPO> themeTaskInfoPOS);
}
