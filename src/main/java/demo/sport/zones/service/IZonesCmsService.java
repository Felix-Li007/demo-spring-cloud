package demo.sport.zones.service;

import com.hrms.core.domian.PageResponse;
import com.hrms.core.domian.ResultOut;
import com.hrms.sport.domain.cms.*;
import demo.sport.domain.cms.*;

import java.util.List;

/**
 * @author: houpengyi
 * @date: 2022/9/28 10:52
 * @description:
 */

public interface IZonesCmsService
{

    ResultOut<Void> addZoneInfo(ZoneAddIN zoneAddIN);

    ResultOut<ZoneCmsOUT> queryCmsZoneDetail(ZoneDetailIN zoneDetailIN);

    ResultOut<Void> deleteZoneInfo(ZoneDelIn zoneDelIn);

    ResultOut<PageResponse<ZoneCmsOUT>> queryZoneList(WhereZonePageCmsIN zonesZonePageCmsIN);

    ResultOut<List<ZoneCmsOUT>> queryZoneParentList();

    ResultOut<Void> updateZoneState(ZoneEditIN zoneEditIN);

    ResultOut<Void> updateZoneInfo(ZoneEditIN zoneEditIN);
}
