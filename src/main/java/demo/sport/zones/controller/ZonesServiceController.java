package demo.sport.zones.controller;

import cn.hutool.core.util.DesensitizedUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.account.domain.team.TeamAccntInfoOUT;
import com.hrms.core.domian.PageResponse;
import com.hrms.core.domian.ResultOut;
import com.hrms.core.exception.HrmsException;
import demo.sport.api.zones.IZonesServiceApi;
import demo.sport.constants.zones.HeadModeEnum;
import demo.sport.constants.zones.HeadTypeEnum;
import demo.sport.domain.theme.PlaceThemeInfoOUT;
import demo.sport.domain.theme.ThemeMedalInfoOUT;
import com.hrms.sport.domain.zones.*;
import demo.sport.domain.zones.*;
import demo.sport.zones.common.config.StorageBaseConfig;
import demo.sport.zones.common.config.StoragePathConfig;
import demo.sport.zones.common.exception.ErrorMessageEnum;
import com.hrms.sport.zones.convert.*;
import demo.sport.zones.convert.*;
import demo.sport.zones.domain.ZonesMessInfoPO;
import demo.sport.zones.domain.ZonesPfileInfoPO;
import demo.sport.zones.domain.ZonesThemeRankPO;
import com.hrms.sport.zones.entity.*;
import demo.sport.zones.entity.*;
import demo.sport.zones.service.IPFileInfoService;
import demo.sport.zones.service.ITeamInfoService;
import demo.sport.zones.service.IThemeInfoService;
import demo.sport.zones.service.IZoneInfoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api
@RestController
public class ZonesServiceController implements IZonesServiceApi
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ZonesServiceController.class);

    @Resource
    private IZoneInfoService zoneInfoService;


    @Resource
    private IPFileInfoService pfileInfoService;

    @Resource
    private IThemeInfoService themeInfoService;


    @Resource
    private IZonesRankConvert zonesRankConvert;

    @Resource
    private IZonePFileConvert zonePFileConvert;

    @Resource
    private IZoneMessConvert zoneMessConvert;

    @Resource
    private IZoneExperConvert zoneExperConvert;

    @Resource
    private IZonesZoneConvert zonesZoneConvert;

    @Resource
    private StorageBaseConfig storageBaseConfig;

    @Resource
    private StoragePathConfig storagePathConfig;

    @Resource
    private ITeamInfoService teamInfoService;


    private String combineImageURL(String imageFile)
    {
        if (ObjectUtils.isEmpty(imageFile)) {
            return null;
        }
        String strImageURL = storageBaseConfig.getOssCname() + "/" + storagePathConfig.getRootPath() + storagePathConfig.getHeadPath() + "/" + imageFile;
        return strImageURL;
    }


    /**
     * 查询系统头像列表
     *
     * @param zonesThemePageIN
     * @return
     */
    @Override
    public ResultOut<PageResponse<ThemeHeadInfoOUT>> queryHeadList(ZonesThemePageIN zonesThemePageIN)
    {
        Page<ThemeHeadInfoBO> page = zoneInfoService.queryHeadList(zonesThemePageIN);
        PageResponse<ThemeHeadInfoOUT> pageResponse = new PageResponse<>();
        pageResponse.setPageSize(zonesThemePageIN.getPageSize());
        pageResponse.setPageNum(zonesThemePageIN.getPageNum());
        pageResponse.setSequenceId(zonesThemePageIN.getSequenceId());
        pageResponse.setTotalNum((int) page.getTotal());

        List<ThemeHeadInfoOUT> themeHeadInfoOUTS = Optional.ofNullable(page.getRecords()).map(e -> e.stream().map(themeHeadInfoBO -> {
            String strHeadImage = themeHeadInfoBO.getHeadImage();
            themeHeadInfoBO.setHeadImage(combineImageURL(strHeadImage));
            ThemeHeadInfoOUT themeHeadInfoOUT = zonesZoneConvert.convertHeadInfoBO(themeHeadInfoBO);
            return themeHeadInfoOUT;
        }).collect(Collectors.toList())).orElse(null);

        pageResponse.setRecords(themeHeadInfoOUTS);


        return new ResultOut<PageResponse<ThemeHeadInfoOUT>>().buildSuccess(pageResponse);
    }

    @Override
    public ResultOut<PageResponse<ZonesZoneInfoOUT>> queryZoneList(ZonesThemePageIN zonesThemePageIn)
    {
        ResultOut<PageResponse<ZonesZoneInfoOUT>> pageResponse = zoneInfoService.queryZoneList(zonesThemePageIn);
        return pageResponse;
    }

    @Override
    public ResultOut<ZonesZoneDetailOUT> queryZoneDetail(ZonesZoneBaseIN zonesZoneBaseIN)
    {
        ZonesZoneDetailOUT zonesZoneDetailOUT = zoneInfoService.queryZoneDetail(zonesZoneBaseIN);
        return new ResultOut<ZonesZoneDetailOUT>().buildSuccess(zonesZoneDetailOUT);
    }


    @Override
    public ResultOut<List<ThemeRankInfoOUT>> queryRankList(ZonesThemeBaseIN zonesThemeBaseIN)
    {
        List<ZonesThemeRankPO> zonesThemeRankPOS = themeInfoService.queryRankList(zonesThemeBaseIN);
        List<ThemeRankInfoOUT> zonesThemeRankOUTS = zonesRankConvert.convert2ZonesRankOUTS(zonesThemeRankPOS);
        return new ResultOut<List<ThemeRankInfoOUT>>().buildSuccess(zonesThemeRankOUTS);
    }


    @Override
    public ResultOut<PageResponse<ZonePFileInfoOUT>> queryPFileList(ThemePFilePageIN themePFilePageIN)
    {
        Page<ZonesPfileInfoBO> page = pfileInfoService.queryPFileList(themePFilePageIN);
        PageResponse<ZonePFileInfoOUT> pageResponse = new PageResponse<>();
        pageResponse.setPageNum(themePFilePageIN.getPageNum());
        pageResponse.setPageSize(themePFilePageIN.getPageSize());
        pageResponse.setTotalNum((int) page.getTotal());
        List<ZonePFileInfoOUT> zonePFileInfoOUTS = zonePFileConvert.convertPFileInfoOUTS(page.getRecords());
        if (!ObjectUtils.isEmpty(zonePFileInfoOUTS)) {
            zonePFileInfoOUTS = zonePFileInfoOUTS.stream().parallel().map(zonePFileInfoOUT -> {
                zonePFileInfoOUT.setHeadImage(combineImageURL(zonePFileInfoOUT.getHeadImage()));
                return zonePFileInfoOUT;
            }).collect(Collectors.toList());
        }
        pageResponse.setRecords(zonePFileInfoOUTS);
        return new ResultOut<PageResponse<ZonePFileInfoOUT>>().buildSuccess(pageResponse);
    }

    @Override
    public ResultOut<PageResponse<ZonesMessInfoOUT>> queryMessList(ZonesThemePageIN zonesThemePageIN)
    {
        PageResponse<ZonesMessInfoOUT> pageResponse = new PageResponse<>();
        pageResponse.setPageNum(zonesThemePageIN.getPageNum());
        pageResponse.setPageSize(zonesThemePageIN.getPageSize());
        IPage<ZonesMessInfoPO> page = zoneInfoService.queryMessList(zonesThemePageIN);
        pageResponse.setTotalNum((int) page.getTotal());
        List<ZonesMessInfoOUT> zonesZoneOUTList = zoneMessConvert.convertMessOUTS(page.getRecords());
        pageResponse.setRecords(zonesZoneOUTList);
        return new ResultOut<PageResponse<ZonesMessInfoOUT>>().buildSuccess(pageResponse);
    }

    /**
     * @param zonesThemeBaseIN
     * @return
     */
    @Override
    public ResultOut<List<ThemeMedalInfoOUT>> queryMedalList(ZonesThemeBaseIN zonesThemeBaseIN)
    {
        String strThemeNo = zonesThemeBaseIN.getThemeNo();
        List<ThemeMedalInfoBO> themeMedalInfoBOS = themeInfoService.queryMedalList(strThemeNo);
        List<ThemeMedalInfoOUT> themeMedalInfoOUTS = zonesRankConvert.convertMedalInfoOUTS(themeMedalInfoBOS);
        return new ResultOut<List<ThemeMedalInfoOUT>>().buildSuccess(themeMedalInfoOUTS);
    }

    /**
     * 修改用户档案头像数据
     *
     * @param themeHeadInfoIN
     * @return
     */
    @Override
    public ResultOut<ZonePFileInfoOUT> modifyHeadDetail(ThemeHeadInfoIN themeHeadInfoIN)
    {
        String strAccntNo = Optional.ofNullable(themeHeadInfoIN).map(ThemeHeadInfoIN::getAccountNo).orElseThrow(() -> new HrmsException(ErrorMessageEnum.RESULT_CODE_997));
        String strThemeNo = Optional.ofNullable(themeHeadInfoIN).map(ThemeHeadInfoIN::getThemeNo).orElseThrow(() -> new HrmsException(ErrorMessageEnum.RESULT_CODE_997));
        String strHeadNo = Optional.ofNullable(themeHeadInfoIN).map(ThemeHeadInfoIN::getHeadNo).orElseThrow(() -> new HrmsException(ErrorMessageEnum.RESULT_CODE_997));
        ZonesPfileInfoBO themeHeadInfoBO = pfileInfoService.modifyHeadDetail(strThemeNo, strAccntNo, strHeadNo, HeadTypeEnum.HEAD_TYPE_AUTO, HeadModeEnum.HEAD_MODE_USER);
        ZonePFileInfoOUT zonePFileInfoOUT = Optional.ofNullable(themeHeadInfoBO).map(e -> zonePFileConvert.convertPfileInfoBO(e)).orElse(null);
        if (!ObjectUtils.isEmpty(zonePFileInfoOUT)) {
            zonePFileInfoOUT.setHeadNo(strHeadNo);
        }
        zonePFileInfoOUT.setHeadImage(combineImageURL(zonePFileInfoOUT.getHeadImage()));

        return new ResultOut<ZonePFileInfoOUT>().buildSuccess(zonePFileInfoOUT);
    }


    public ResultOut<TeamExperInfoOUT> queryTeamExper(ThemeTeamInfoIN themeTeamInfoIN)
    {
        String strThemeNo = themeTeamInfoIN.getThemeNo();
        String strTeamNo = themeTeamInfoIN.getTeamNo();
        TeamExperInfoBO teamExperInfoBO = pfileInfoService.queryTeamExper(strThemeNo, strTeamNo);
        TeamExperInfoOUT teamExperInfoOUT = Optional.ofNullable(teamExperInfoBO).map(e -> zonePFileConvert.convertExperInfoOUT(e)).orElse(null);
        return new ResultOut<TeamExperInfoOUT>().buildSuccess(teamExperInfoOUT);
    }


    @Override
    public ResultOut<ZonePFileInfoOUT> queryPfileDetail(ZonesPFileBaseIN zonesPFileBaseIN)
    {
        ZonesPfileInfoPO zonesPfileInfoPO = pfileInfoService.queryPFileDetail(zonesPFileBaseIN);
        ZonePFileInfoOUT zonePFileInfoOUT = zonePFileConvert.convertPFileInfoOUT(zonesPfileInfoPO);
        String strAccntNo = zonePFileInfoOUT.getAccountNo();
        //优先使用用户帐号服务中的数据，从群组服务中查询用户的帐号数据
        TeamAccntInfoOUT teamAccntInfoOUT = teamInfoService.queryAccntDetail(strAccntNo);
        if (!ObjectUtils.isEmpty(teamAccntInfoOUT)) {
            zonePFileInfoOUT.setTrueName(teamAccntInfoOUT.getTrueName());
            String strPhoneNum = teamAccntInfoOUT.getPhoneNum();
            if (!ObjectUtils.isEmpty(strPhoneNum)) {
                strPhoneNum = DesensitizedUtil.mobilePhone(strPhoneNum);
                zonePFileInfoOUT.setPhoneNum(strPhoneNum);
            }
        }
        zonePFileInfoOUT.setHeadImage(combineImageURL(zonePFileInfoOUT.getHeadImage()));
        return new ResultOut<ZonePFileInfoOUT>().buildSuccess(zonePFileInfoOUT);
    }

    @Override
    public ResultOut<PlaceThemeInfoOUT> queryThemeDetail(ZonesThemeBaseIN zonesThemeBaseIN)
    {
        PlaceThemeInfoOUT placeThemeInfoOUT = themeInfoService.queryThemeInfo(zonesThemeBaseIN);
        return new ResultOut<PlaceThemeInfoOUT>().buildSuccess(placeThemeInfoOUT);
    }

    @Override
    public ResultOut<List<GameExperInfoOUT>> queryExperList(ThemeZoneInfoIN themeZoneInfoIN)
    {
        String strThemeNo = themeZoneInfoIN.getThemeNo();
        String strZoneNo = themeZoneInfoIN.getZoneNo();

        List<GameExperInfoBO> gameExperInfoBOS = zoneInfoService.queryExperList(strThemeNo, strZoneNo);
        List<GameExperInfoOUT> zonesGameExperOUTS = Optional.ofNullable(gameExperInfoBOS).map(e -> zoneExperConvert.convertExperOUTS(e)).orElse(Collections.emptyList());
        return new ResultOut<List<GameExperInfoOUT>>().buildSuccess(zonesGameExperOUTS);
    }

    /**
     * @param zonesThemeBaseIN
     * @return
     */
    @Override
    public ResultOut<List<ZonesCionInfoOUT>> queryCionList(ZonesThemeBaseIN zonesThemeBaseIN)
    {
        String strThemeNo = zonesThemeBaseIN.getThemeNo();
        List<ZonesCionInfoOUT> zonesCionInfoOUTS = themeInfoService.queryCionList(strThemeNo);
        return new ResultOut<List<ZonesCionInfoOUT>>().buildSuccess(zonesCionInfoOUTS);
    }


    /**
     * @param placeExperFlowIN
     * @return
     */
    @Override
    public ResultOut<List<PlaceExperFlowOUT>> queryExperFlow(PlaceExperFlowIN placeExperFlowIN)
    {
        String strAccntNo = placeExperFlowIN.getAccountNo();
        List<String> experNos = placeExperFlowIN.getExperNos();
        List<PlaceExperFlowBO> placeExperFlowBOS = pfileInfoService.queryExperFlow(strAccntNo, experNos);
        List<PlaceExperFlowOUT> placeExperFlowOUTS = Optional.ofNullable(placeExperFlowBOS).map(e -> zonePFileConvert.convertExperFlowOUTS(e)).orElse(Collections.emptyList());
        return new ResultOut<List<PlaceExperFlowOUT>>().buildSuccess(placeExperFlowOUTS);
    }
}
