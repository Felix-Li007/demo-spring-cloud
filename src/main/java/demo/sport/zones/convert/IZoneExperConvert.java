package demo.sport.zones.convert;

import demo.sport.domain.theme.ThemeExperCmsOUT;
import demo.sport.domain.zones.GameExperInfoOUT;
import demo.sport.zones.domain.PlaceExperFlowPO;
import demo.sport.zones.entity.GameExperInfoBO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IZoneExperConvert
{


    GameExperInfoOUT convertExperOUT(GameExperInfoBO gameExperInfoBO);

    List<GameExperInfoOUT> convertExperOUTS(List<GameExperInfoBO> gameExperInfoBOS);

    ThemeExperCmsOUT convert2CmsExperOUT(PlaceExperFlowPO placeExperFlowPO);
}
