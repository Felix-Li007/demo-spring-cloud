package demo.sport.zones.controller;

import com.hrms.core.domian.ResultOut;
import demo.sport.api.theme.IThemeServiceApi;
import demo.sport.domain.theme.ThemeGameInfoOUT;
import demo.sport.domain.zones.CionLimitInfoOUT;
import demo.sport.zones.convert.IGameConvert;
import demo.sport.zones.convert.IZonesCionConvert;
import demo.sport.zones.entity.CionLimitInfoBO;
import demo.sport.zones.entity.ThemeGameInfoBO;
import demo.sport.zones.service.IGameInfoService;
import demo.sport.zones.service.IThemeInfoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Api
@RestController
public class ThemeController implements IThemeServiceApi
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThemeController.class);

    @Resource
    private IThemeInfoService themeInfoService;

    @Resource
    private IZonesCionConvert zonesCionConvert;

    @Resource
    private IGameConvert gameConvert;

    @Resource
    private IGameInfoService gameService;


    /**
     * @param cionCode
     * @return
     */
    @Override
    public ResultOut<List<CionLimitInfoOUT>> queryCionLimit(String cionCode)
    {
        List<CionLimitInfoBO> cionLimitInfoBOS = themeInfoService.queryLimitList(cionCode);
        List<CionLimitInfoOUT> cionLimitInfoOUTS = Optional.ofNullable(cionLimitInfoBOS).map(e -> zonesCionConvert.convertLimitInfoBOS(e)).orElse(Collections.emptyList());
        return new ResultOut<List<CionLimitInfoOUT>>().buildSuccess(cionLimitInfoOUTS);
    }

    /**
     * @return
     */
    @Override
    public ResultOut<List<ThemeGameInfoOUT>> queryGameList()
    {
        List<ThemeGameInfoBO> themeGameInfoBOS = gameService.queryGameList();
        List<ThemeGameInfoOUT> themeGameInfoOUTS = Optional.ofNullable(themeGameInfoBOS).map(e -> gameConvert.convertGameInfoBOS(e)).orElse(Collections.emptyList());
        return new ResultOut<List<ThemeGameInfoOUT>>().buildSuccess(themeGameInfoOUTS);
    }
}
