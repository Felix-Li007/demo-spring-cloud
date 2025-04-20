package demo.sport.zones.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hrms.frame.cache.HrmsCacheUtil;
import demo.sport.zones.common.constant.RedisCacheEnum;
import demo.sport.zones.convert.IGameConvert;
import demo.sport.zones.domain.ThemeGameInfoPO;
import demo.sport.zones.entity.ThemeGameInfoBO;
import demo.sport.zones.mapper.IGameInfoMapper;
import demo.sport.zones.service.IGameInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 *
 */
@Service
public class GameInfoServiceImpl implements IGameInfoService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(GameInfoServiceImpl.class);

    @Resource
    private IGameInfoMapper gameInfoMapper;

    @Resource
    private IGameConvert gameConvert;

    /**
     *
     */
    @Override
    public List<ThemeGameInfoBO> queryGameList()
    {
        List<ThemeGameInfoBO> themeGameInfoBOS = HrmsCacheUtil.cacheGet(RedisCacheEnum.CACHE_KEY_THEME_GAME_LIST);
        if (!ObjectUtils.isEmpty(themeGameInfoBOS)) {
            return themeGameInfoBOS;
        }
        List<ThemeGameInfoPO> themeGameInfoPOS = gameInfoMapper.selectGameList();
        themeGameInfoBOS = Optional.ofNullable(themeGameInfoPOS).map(e -> gameConvert.convertGameInfoPOS(e)).orElse(null);
        if (!ObjectUtils.isEmpty(themeGameInfoBOS)) {
            HrmsCacheUtil.cachePut(RedisCacheEnum.CACHE_KEY_THEME_GAME_LIST, themeGameInfoBOS);
        }
        LOGGER.info("查询游戏列表数据：GAME_INFO={}", JSONObject.toJSONString(themeGameInfoBOS));
        return themeGameInfoBOS;
    }

    /**
     * @param playCode
     * @return
     */
    @Override
    public ThemeGameInfoBO queryGameInfo(String playCode)
    {
        List<ThemeGameInfoBO> themeGameInfoBOS = this.queryGameList();
        themeGameInfoBOS = themeGameInfoBOS.stream().filter(e -> playCode.equals(e.getPlayCode())).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(themeGameInfoBOS)) {
            LOGGER.info("查询指定游戏信息数据：PLAY_CODE={}", playCode);
            return null;
        }
        ThemeGameInfoBO themeGameInfoBO = themeGameInfoBOS.get(0);
        LOGGER.info("查询指定游戏信息数据：PLAY_CODE={}, GAME_INFO={}", playCode, JSON.toJSONString(themeGameInfoBO));
        return themeGameInfoBO;
    }


}
