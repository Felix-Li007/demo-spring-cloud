package demo.sport.zones.controller;

import com.alibaba.fastjson.JSONObject;
import com.hrms.core.domian.PageResponse;
import com.hrms.core.domian.ResultOut;
import demo.sport.api.theme.IThemeCmsApi;
import demo.sport.domain.cms.theme.ThemeAddIn;
import demo.sport.domain.cms.theme.ThemeDetailIn;
import demo.sport.domain.cms.theme.ThemeZonePageIn;
import demo.sport.domain.theme.PlaceThemeInfoOUT;
import demo.sport.domain.theme.ThemeCionInfoOUT;
import demo.sport.domain.theme.ThemeCmsGameInfoOut;
import demo.sport.domain.theme.ThemeDetailCmsOUT;
import demo.sport.domain.zones.ZoneThemePageIN;
import demo.sport.domain.zones.ZonesZoneInfoOUT;
import demo.sport.zones.convert.IThemeInfoConvert;
import com.hrms.sport.zones.manager.*;
import demo.sport.zones.manager.*;
import demo.sport.zones.service.IThemeInfoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@Api
@RestController
public class ThemeCmsController implements IThemeCmsApi
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThemeCmsController.class);

    @Resource
    private IThemeCmsManager themeCmsManager;

    @Resource
    private IGameCmsManager gameCmsManager;

    @Resource
    private ICionCmsManager cionCmsManager;

    @Resource
    private IZonesCmsManager zonesCmsManager;

    @Resource
    private IExperCmsManager experCmsManager;

    @Resource
    private IMessCmsManager messCmsManager;

    @Resource
    private IThemeInfoService themeInfoService;

    @Resource
    private IRankCmsManager rankCmsManager;

    @Resource
    private IThemeInfoConvert themeInfoConvert;

    @Override
    public ResultOut<ThemeDetailCmsOUT> queryThemeInfo(ThemeDetailIn themeDetailIn)
    {
        ResultOut<ThemeDetailCmsOUT> result = themeCmsManager.queryThemeInfo(themeDetailIn);
        LOGGER.info("查询后管场馆数据数据：THEME_INFO={}", JSONObject.toJSONString(result.getValue()));
        return result;
    }

    @Override
    public ResultOut<PageResponse<PlaceThemeInfoOUT>> queryThemeList(ZoneThemePageIN zoneThemePageIN)
    {
        PageResponse<PlaceThemeInfoOUT> pageResponse = themeInfoService.queryThemeList(zoneThemePageIN);
        return new ResultOut<PageResponse<PlaceThemeInfoOUT>>().buildSuccess(pageResponse);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultOut<String> modifyThemeInfo(ThemeAddIn themeAddIn)
    {
        String themeNo = themeCmsManager.modifyThemeInfo(themeAddIn);
        zonesCmsManager.modifyZoneThemeRel(themeAddIn, themeNo);
        cionCmsManager.modifyCionThemeRel(themeAddIn, themeNo);
        experCmsManager.modifyExperThemeRel(themeAddIn, themeNo);
        messCmsManager.modifyMessThemeRel(themeAddIn, themeNo);
        //TODO 主题和榜单的关联的建立因为后管功能缺失通过数据库脚本完成，不需要处理。
        //rankCmsManager.modifyRankThemeRel(themeAddIn, themeNo);
        return new ResultOut<String>().buildSuccess(themeNo);
    }

    @Override
    public ResultOut<List<ThemeCmsGameInfoOut>> queryGameList()
    {
        return gameCmsManager.queryGameList();
    }

    @Override
    public ResultOut<List<ThemeCionInfoOUT>> queryCionList()
    {
        return cionCmsManager.queryCionList();
    }

    @Override
    public ResultOut<PageResponse<ZonesZoneInfoOUT>> queryZoneList(ThemeZonePageIn themeZonePageIn)
    {
        return zonesCmsManager.queryZoneList(themeZonePageIn);
    }
}
