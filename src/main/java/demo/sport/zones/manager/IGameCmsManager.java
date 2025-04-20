package demo.sport.zones.manager;


import com.hrms.core.domian.ResultOut;
import demo.sport.domain.theme.ThemeCmsGameInfoOut;

import java.util.List;

/**
 * @description: 接口描述
 * @author: houpengyi
 * @since: 从XXX版本引入的接口
 */
public interface IGameCmsManager
{

    ResultOut<List<ThemeCmsGameInfoOut>> queryGameList();
}
