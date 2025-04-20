package demo.sport.zones.manager.impl;


import demo.sport.domain.cms.theme.ThemeAddIn;
import demo.sport.zones.convert.IZoneMessConvert;
import demo.sport.zones.domain.ZonesMessInfoPO;
import demo.sport.zones.manager.IMessCmsManager;
import demo.sport.zones.mapper.IThemeMessMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * @author: houpengyi
 * @date: 2022/10/17 22:07
 * @description:
 */
@Component
public class MessCmsManagerImpl implements IMessCmsManager
{

    @Resource
    private IThemeMessMapper zonesMessInfoMapper;

    @Resource
    private IZoneMessConvert messConvert;


    @Override
    public void modifyMessThemeRel(ThemeAddIn themeAddIn, String themeNo)
    {
        zonesMessInfoMapper.deleteByThemeNo(themeNo);
        ThemeAddIn.messInfoAddIn messInfo = themeAddIn.getMessInfo();
        String messConf = messInfo.getMessConf();
        List<String> messCon = Arrays.asList(messConf.split(";"));
        messCon.stream().forEach(mess -> {
            ZonesMessInfoPO zonesMessInfoPO = this.conver2MessPO(mess, themeNo, messInfo.getSortMode());
            zonesMessInfoMapper.insertSelective(zonesMessInfoPO);
        });
    }

    private ZonesMessInfoPO conver2MessPO(String mess, String themeNo, String sortMode)
    {
        ZonesMessInfoPO zonesMessInfoPO = new ZonesMessInfoPO();
        List<String> messCon = Arrays.asList(mess.split(":"));
        zonesMessInfoPO.setMessType(messCon.get(0));
        zonesMessInfoPO.setMessNum(Integer.valueOf(messCon.get(1)));
        zonesMessInfoPO.setThemeNo(themeNo);
        zonesMessInfoPO.setSortMode(sortMode);
        return zonesMessInfoPO;
    }
}
