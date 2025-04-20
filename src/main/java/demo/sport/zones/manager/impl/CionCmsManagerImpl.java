package demo.sport.zones.manager.impl;


import com.hrms.core.domian.ResultOut;
import demo.sport.domain.cms.theme.ThemeAddIn;
import demo.sport.domain.theme.ThemeCionInfoOUT;
import demo.sport.zones.convert.ICionConvert;
import demo.sport.zones.domain.CionLimitInfoPO;
import demo.sport.zones.domain.ZonesThemeCionPO;
import demo.sport.zones.entity.PlaceCionInfoBO;
import demo.sport.zones.manager.ICionCmsManager;
import demo.sport.zones.mapper.ICionInfoMapper;
import demo.sport.zones.mapper.ICionLimitMapper;
import demo.sport.zones.mapper.IThemeCionMapper;
import demo.sport.zones.service.IPlaceInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


/**
 * @author: houpengyi
 * @date: 2022/10/17 22:07
 * @description:
 */
@Component
public class CionCmsManagerImpl implements ICionCmsManager
{

    private static final Logger LOGGER = LoggerFactory.getLogger(CionCmsManagerImpl.class);

    @Resource
    private ICionInfoMapper placeCionInfoMapper;

    @Resource
    private ICionConvert cionConvert;

    @Resource
    private IThemeCionMapper zonesThemeCionMapper;

    @Resource
    private ICionLimitMapper cionLimitMapper;

    @Resource
    private IPlaceInfoService placenfoService;

    @Override
    public ResultOut<List<ThemeCionInfoOUT>> queryCionList()
    {
        List<PlaceCionInfoBO> placeCionInfoBOS = placenfoService.queryCionList();

        List<ThemeCionInfoOUT> themeCionInfoOUTS = Optional.ofNullable(cionConvert.convertCionInfoBOS(placeCionInfoBOS)).orElse(null);
        return new ResultOut<List<ThemeCionInfoOUT>>().buildSuccess(themeCionInfoOUTS);
    }

    @Override
    public void modifyCionThemeRel(ThemeAddIn themeAddIn, String themeNo)
    {
        //修改主题与货币关联关系
        zonesThemeCionMapper.deleteByThemeNo(themeNo);
        List<ThemeAddIn.ThemeCionAddIn> cionList = themeAddIn.getCionList();

        cionList.stream().forEach(data -> {
            ZonesThemeCionPO cionPO = cionConvert.convert2ThemeCionRelList(data, themeNo);
            BigDecimal multipy = this.multiply(cionPO.getInitiValue());
            cionPO.setInitiValue(multipy);
            zonesThemeCionMapper.insertSelective(cionPO);
            LOGGER.info("添加货币与主活动关联关系：THEME_NO={}, CION_CODE={}", themeNo, data.getCionCode());
        });
        //修改货币限制条件
        cionLimitMapper.deleteByThemeNo(themeNo);
        cionList.stream().forEach(limitPO -> {
            CionLimitInfoPO cionLimitPO = cionConvert.convert2ThemeCionLimit(limitPO, themeNo);
            if (cionLimitPO.getValueMode().equals("00")) {
                Integer limitValue = cionLimitPO.getLimitValue();
                cionLimitPO.setLimitValue(limitValue * 100);
            }
            cionLimitMapper.insertSelective(cionLimitPO);
            LOGGER.info("添加货币限制：THEME_NO={}, CION_CODE={}", themeNo, limitPO.getCionCode());
        });
    }

    private BigDecimal multiply(BigDecimal bigDecimal)
    {
        BigDecimal two = new BigDecimal("100");
        BigDecimal multiply = bigDecimal.multiply(two);
        return multiply;
    }
}
