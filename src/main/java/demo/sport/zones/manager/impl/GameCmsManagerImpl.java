package demo.sport.zones.manager.impl;


import com.hrms.core.domian.ResultOut;
import demo.sport.domain.theme.ThemeCmsGameInfoOut;
import demo.sport.zones.convert.IGameConvert;
import demo.sport.zones.domain.ThemeGameInfoPO;
import demo.sport.zones.manager.IGameCmsManager;
import demo.sport.zones.mapper.IGameInfoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author: houpengyi
 * @date: 2022/10/17 22:07
 * @description:
 */
@Component
public class GameCmsManagerImpl implements IGameCmsManager
{

    @Resource
    private IGameInfoMapper gameInfoMapper;

    @Resource
    private IGameConvert gameConvert;


    @Override
    public ResultOut<List<ThemeCmsGameInfoOut>> queryGameList()
    {
        List<ThemeGameInfoPO> themeGameInfoPOS = gameInfoMapper.selectGameList();
        List<ThemeCmsGameInfoOut> themeGameInfoOUTS = gameConvert.convertGameInfoOUTS(themeGameInfoPOS);
        return new ResultOut<List<ThemeCmsGameInfoOut>>().buildSuccess(themeGameInfoOUTS);
    }
}
