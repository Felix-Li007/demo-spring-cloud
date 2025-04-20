package demo.sport.zones.common.constant;

import com.hrms.frame.cache.HrmsCacheKey;


public enum RedisCacheEnum implements HrmsCacheKey
{

    CACHE_KEY_ZONE_RANK_GAME("ZONE_RANK_GAME", "榜单玩法数据", -1),
    CACHE_KEY_ZONE_GAME_EXPER("ZONE_GAME_EXPER:{themeNo}-{zoneNo}", "场馆游戏点数", -1),
    CACHE_KEY_THEME_GAME_LIST("THEME_GAME_LIST", "主题游戏列表", -1),
    CACHE_KEY_THEME_HEAD_LIST("THEME_HEAD_LIST:{themeNo}:{pageNum}-{pageSize}", "主题头像列表", -1),
    CACHE_KEY_PLACE_ZONE_INFO("PALCE_ZONE_INFO:{zoneNo}", "场馆场馆明细", -1),
    CACHE_KEY_PLACE_THEME_INFO("PALCE_THEME_INFO:{themeNo}", "主题明细数据", -1),
    CACHE_KEY_PLACE_THEME_MESS("PALCE_THEME_MESS:{themeNo}", "主题消息数据", -1),
    CACHE_KEY_PLACE_THEME_MEDAL("PALCE_THEME_MEDAL:{themeNo}", "主题勋章数据", -1),
    CACHE_KEY_PLACE_THEME_TASK("PALCE_THEME_TASK:{themeNo}", "主题任务数据", -1),
    LOCKD_KEY_THEME_ACCNT_PLAY("THEME_ACCNT_PLAY:{themeNo}:{accntNo}-{playMode}", "玩法经验锁KEY", -1),
    LOCKD_KEY_THEME_ACCNT_PFILE("THEME_ACCNT_PFILE:{themeNo}:{accntNo}", "用户经验锁KEY", -1);;
    private final String key;
    private final String mark;
    /**
     * 过期时间，秒为单位
     */
    private final long time;

    RedisCacheEnum(String key, String mark, long time)
    {
        this.key = key;
        this.mark = mark;
        this.time = time;
    }

    public long getTime()
    {
        return time;
    }

    @Override
    public String keyExpression()
    {
        return key;
    }

    @Override
    public String mark()
    {
        return mark;
    }

}
