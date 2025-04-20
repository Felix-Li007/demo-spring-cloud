package demo.sport.zones.manager.impl;


import demo.sport.domain.cms.theme.ThemeAddIn;
import demo.sport.zones.convert.IRankConvert;
import demo.sport.zones.domain.ZonesThemeRankPO;
import demo.sport.zones.manager.IRankCmsManager;
import demo.sport.zones.mapper.IThemeRankMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author: houpengyi
 * @date: 2022/10/17 22:07
 * @description:
 */
@Component
public class RankCmsManagerImpl implements IRankCmsManager
{

    @Resource
    private IRankConvert rankConvert;

    @Resource
    private IThemeRankMapper themeRankMapper;

    @Override
    public void modifyRankThemeRel(ThemeAddIn themeAddIn, String themeNo)
    {
        themeRankMapper.deleteByThemeNo(themeNo);
        List<ThemeAddIn.ThemeRankAddIn> rankList = themeAddIn.getRankList();
        rankList.stream().forEach(data -> {
            ZonesThemeRankPO zonesThemeRankPO = rankConvert.convert2ThemeRankRelList(data, themeNo);
            themeRankMapper.insertSelective(zonesThemeRankPO);
        });
    }
}
