package demo.sport.zones.service;


import demo.sport.zones.entity.ThemeGameInfoBO;

import java.util.List;

/**
 *
 */
public interface IGameInfoService
{

    List<ThemeGameInfoBO> queryGameList();

    ThemeGameInfoBO queryGameInfo(String playCode);
}
