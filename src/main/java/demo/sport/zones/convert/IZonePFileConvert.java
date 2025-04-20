package demo.sport.zones.convert;

import demo.sport.domain.zones.PlaceExperFlowOUT;
import demo.sport.domain.zones.TeamExperInfoOUT;
import demo.sport.domain.zones.ZonePFileInfoOUT;
import demo.sport.zones.domain.PlaceExperFlowPO;
import demo.sport.zones.domain.PlayExperInfoPO;
import demo.sport.zones.domain.TeamExperInfoPO;
import demo.sport.zones.domain.ZonesPfileInfoPO;
import demo.sport.zones.entity.PlaceExperFlowBO;
import demo.sport.zones.entity.PlayExperInfoBO;
import demo.sport.zones.entity.TeamExperInfoBO;
import demo.sport.zones.entity.ZonesPfileInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IZonePFileConvert
{

    TeamExperInfoOUT convertExperInfoOUT(TeamExperInfoBO teamExperInfoBO);

    PlaceExperFlowOUT convertExperFlowOUT(PlaceExperFlowBO placeExperFlowBO);

    List<PlaceExperFlowOUT> convertExperFlowOUTS(List<PlaceExperFlowBO> placeExperFlowBOS);

    List<PlaceExperFlowBO> convertExperFlowPOS(List<PlaceExperFlowPO> placeExperFlowPOS);

    PlaceExperFlowBO convertExperFlowPO(PlaceExperFlowPO placeExperFlowPO);

    PlaceExperFlowPO convertExperFlowBO(PlaceExperFlowBO placeExperFlowBO);


    ZonePFileInfoOUT convertPFileInfoOUT(ZonesPfileInfoPO zonesPfileInfoPO);

    List<ZonePFileInfoOUT> convertPFileInfoOUTS(List<ZonesPfileInfoBO> zonesPfileInfoBOS);

    ZonePFileInfoOUT convertPfileInfoBO(ZonesPfileInfoBO zonesPfileInfoBO);

    @Mappings({
            //转化道具状态枚举值
            @Mapping(target = "headType", expression = "java(com.hrms.sport.constants.zones.HeadTypeEnum.getHeadType(zonesPfileInfoPO.getHeadType()))"),
            @Mapping(target = "headMode", expression = "java(com.hrms.sport.constants.zones.HeadModeEnum.getHeadMode(zonesPfileInfoPO.getHeadMode()))"),
            @Mapping(target = "proteState", expression = "java(com.hrms.sport.constants.zones.ProteStateEnum.getProteState(zonesPfileInfoPO.getProteState()))"),
    })
    ZonesPfileInfoBO convertPfileInfoPO(ZonesPfileInfoPO zonesPfileInfoPO);

    List<ZonesPfileInfoBO> convertPfileInfoPOS(List<ZonesPfileInfoPO> zonesPfileInfoPOS);

    @Mappings({
            //转化道具状态枚举值
            @Mapping(target = "playMode", expression = "java(com.hrms.sport.constants.zones.PlayModeEnum.getPlayMode(profileExperInfoPO.getPlayMode()))"),
    })
    PlayExperInfoBO convertExperInfoPO(PlayExperInfoPO profileExperInfoPO);

    TeamExperInfoBO convertExperInfoPO(TeamExperInfoPO teamExperInfoPO);

    List<TeamExperInfoBO> convertExperInfoPOS(List<TeamExperInfoPO> teamExperInfoPOS);

    PlayExperInfoPO convertExperInfoBO(PlayExperInfoBO playExperInfoBO);

    TeamExperInfoPO convertExperInfoBO(TeamExperInfoBO teamExperInfoBO);
}
