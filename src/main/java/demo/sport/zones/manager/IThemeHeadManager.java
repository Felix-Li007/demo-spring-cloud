package demo.sport.zones.manager;

import demo.sport.zones.domain.ThemeHeadInfoPO;

import java.util.List;

public interface IThemeHeadManager
{

    List<ThemeHeadInfoPO> getList();

    void saveOrUpdate(List<ThemeHeadInfoPO> themeHeadInfoPOS);
}
