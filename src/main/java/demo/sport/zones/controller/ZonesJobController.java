package demo.sport.zones.controller;

import com.hrms.core.domian.ResultOut;
import demo.sport.api.zones.ISportZonesJobApi;
import demo.sport.domain.zones.UpThemeHeadInfoIn;
import demo.sport.zones.service.ZonesJobService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(ISportZonesJobApi.PATH)
public class ZonesJobController implements ISportZonesJobApi
{

    @Resource
    private ZonesJobService zonesJobService;

    public ZonesJobController(ZonesJobService zonesJobService)
    {
        this.zonesJobService = zonesJobService;
    }

    @Override
    public ResultOut<Void> updateThemeHeadInfo(List<UpThemeHeadInfoIn> upInfos)
    {
        zonesJobService.updateThemeHeadInfo(upInfos);
        return new ResultOut<>();
    }

    @Override
    public ResultOut<Void> changePlaceOpenStatus()
    {
        zonesJobService.changePlaceOpenStatus();
        return new ResultOut<>();
    }

//    @Override
//    public ResultOut<Void> updateZoneState() {
//        zoneStateUpdateJob.updateZoneState();
//        return new ResultOut<>();
//    }
}
