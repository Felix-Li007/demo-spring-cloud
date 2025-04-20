package demo.sport.zones.manager;

import com.hrms.core.domian.ResultOut;
import demo.sport.domain.cms.theme.ThemeAddIn;
import demo.sport.domain.cms.theme.ThemeDetailIn;
import demo.sport.domain.theme.ThemeDetailCmsOUT;


public interface IThemeCmsManager
{
    ResultOut<ThemeDetailCmsOUT> queryThemeInfo(ThemeDetailIn themeDetailIn);

//     ResultOut<PageResponse<PlaceThemeInfoOUT>> queryThemeList(ZoneThemePageIN themePageIn);

    String modifyThemeInfo(ThemeAddIn themeAddIn);
}
