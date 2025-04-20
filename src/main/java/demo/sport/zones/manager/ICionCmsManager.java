package demo.sport.zones.manager;


import com.hrms.core.domian.ResultOut;
import demo.sport.domain.cms.theme.ThemeAddIn;
import demo.sport.domain.theme.ThemeCionInfoOUT;

import java.util.List;

/**
 * @description: 接口描述
 * @author: houpengyi
 * @since: 从XXX版本引入的接口
 */
public interface ICionCmsManager
{

    ResultOut<List<ThemeCionInfoOUT>> queryCionList();

    void modifyCionThemeRel(ThemeAddIn themeAddIn, String themeNo);
}
