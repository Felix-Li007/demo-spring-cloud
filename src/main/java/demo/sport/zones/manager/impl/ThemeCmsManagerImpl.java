package demo.sport.zones.manager.impl;

import com.alibaba.fastjson.JSONObject;
import com.hrms.core.domian.ResultOut;
import com.hrms.frame.sequence.HrmsSequenceGenerator;
import demo.sport.domain.cms.theme.ThemeAddIn;
import demo.sport.domain.cms.theme.ThemeDetailIn;
import demo.sport.domain.theme.ThemeDetailCmsOUT;
import demo.sport.domain.zones.ZonesThemeBaseIN;
import demo.sport.zones.common.constant.ZonesSequenEnum;
import demo.sport.zones.convert.IThemeConvert;
import demo.sport.zones.domain.PlaceThemeInfoPO;
import demo.sport.zones.manager.IThemeCmsManager;
import demo.sport.zones.service.IThemeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author: houpengyi
 * @date: 2022/10/17 22:07
 * @description:
 */
@Component
public class ThemeCmsManagerImpl implements IThemeCmsManager
{
    private static final Logger logger = LoggerFactory.getLogger(ThemeCmsManagerImpl.class);
    @Resource
    IThemeConvert themeConvert;
    @Resource
    private IThemeInfoService themeInfoService;

    @Override
    public ResultOut<ThemeDetailCmsOUT> queryThemeInfo(ThemeDetailIn themeDetailIn)
    {
        ZonesThemeBaseIN zonesThemeBaseIN = new ZonesThemeBaseIN();
        zonesThemeBaseIN.setThemeNo(themeDetailIn.getThemeNo());
        ThemeDetailCmsOUT zonesThemeInfoOUT = themeInfoService.queryCmsThemeDetail(zonesThemeBaseIN);
        if (Objects.isNull(zonesThemeInfoOUT)) {
            return new ResultOut<ThemeDetailCmsOUT>().buildSuccess(new ThemeDetailCmsOUT());
        }
        return new ResultOut<ThemeDetailCmsOUT>().buildSuccess(zonesThemeInfoOUT);
    }

    @Override
    public String modifyThemeInfo(ThemeAddIn themeAddIn)
    {
        String strThemeNo = themeAddIn.getThemeNo();
        PlaceThemeInfoPO zonesThemeInfoPO = themeConvert.convertAddIn2PO(themeAddIn);

        if (ObjectUtils.isEmpty(strThemeNo)) {
            strThemeNo = HrmsSequenceGenerator.dictionaryNo(ZonesSequenEnum.THEME_CODE);
            zonesThemeInfoPO.setThemeNo(strThemeNo);
            themeInfoService.addThemeInfo(zonesThemeInfoPO);
            logger.info("成功添加一条主题信息:{}", JSONObject.toJSON(zonesThemeInfoPO));
        } else {
            themeInfoService.updateThemeInfo(zonesThemeInfoPO);
            themeInfoService.evictThemeInfo(strThemeNo);
        }
        return zonesThemeInfoPO.getThemeNo();
    }
}
