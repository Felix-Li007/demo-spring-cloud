package demo.sport.zones.controller;

import com.hrms.core.domian.PageResponse;
import com.hrms.core.domian.ResultOut;
import demo.sport.api.zones.IZonesCmsApi;
import com.hrms.sport.domain.cms.*;
import demo.sport.domain.cms.*;
import demo.sport.zones.service.IZonesCmsService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author: houpengyi
 * @date: 2022/9/23 19:54
 * @description:
 */
@Valid
@RestController
@Api(value = "cms后管", tags = "ZonesCmsController", produces = "http, https")
public class ZonesCmsController implements IZonesCmsApi
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ZonesCmsController.class);

    @Resource
    private IZonesCmsService zonesCmsService;

    @Override
    public ResultOut<Void> addZoneInfo(ZoneAddIN zoneAddIN)
    {
        return zonesCmsService.addZoneInfo(zoneAddIN);
    }

    @Override
    public ResultOut<ZoneCmsOUT> queryCmsZoneDetail(ZoneDetailIN zoneDetailIN)
    {
        return zonesCmsService.queryCmsZoneDetail(zoneDetailIN);
    }

    @Override
    public ResultOut<Void> deleteZoneInfo(ZoneDelIn zoneDelIn)
    {
        return zonesCmsService.deleteZoneInfo(zoneDelIn);
    }

    @Override
    public ResultOut<PageResponse<ZoneCmsOUT>> queryCmsZoneList(WhereZonePageCmsIN whereZonePageCmsIN)
    {
        return zonesCmsService.queryZoneList(whereZonePageCmsIN);
    }

    @Override
    public ResultOut<List<ZoneCmsOUT>> queryZoneParentList()
    {
        return zonesCmsService.queryZoneParentList();
    }

    @Override
    public ResultOut<Void> updateZoneState(ZoneEditIN zoneEditIN)
    {
        return zonesCmsService.updateZoneState(zoneEditIN);
    }

    @Override
    public ResultOut<Void> updateZoneInfo(ZoneEditIN zoneEditIN)
    {
        return zonesCmsService.updateZoneInfo(zoneEditIN);
    }

}
