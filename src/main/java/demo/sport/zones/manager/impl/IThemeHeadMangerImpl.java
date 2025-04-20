package demo.sport.zones.manager.impl;

import demo.sport.zones.domain.ThemeHeadInfoPO;
import demo.sport.zones.manager.IThemeHeadManager;
import demo.sport.zones.mapper.IThemeHeadMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Transactional(readOnly = true)
public class IThemeHeadMangerImpl implements IThemeHeadManager
{
    private final IThemeHeadMapper themeHeadMapper;

    public IThemeHeadMangerImpl(IThemeHeadMapper themeHeadMapper)
    {
        this.themeHeadMapper = themeHeadMapper;
    }

    @Override
    public List<ThemeHeadInfoPO> getList()
    {
        return themeHeadMapper.getList();
    }

    @Override
    @Transactional
    public void saveOrUpdate(List<ThemeHeadInfoPO> themeHeadInfoPOS)
    {
        List<ThemeHeadInfoPO> addThemeHeadInfoPOS = themeHeadInfoPOS.stream().filter(v -> Objects.isNull(v.getSequenceId())).collect(Collectors.toList());
        List<ThemeHeadInfoPO> upThemeHeadInfoPOS = themeHeadInfoPOS.stream().filter(v -> Objects.nonNull(v.getSequenceId())).collect(Collectors.toList());
        themeHeadMapper.deleteNotInSequenceIds(upThemeHeadInfoPOS.stream().map(ThemeHeadInfoPO::getSequenceId).collect(Collectors.toList()));

        if (addThemeHeadInfoPOS.size() > 0) {
            themeHeadMapper.insertBatch(addThemeHeadInfoPOS);
        }
        upThemeHeadInfoPOS.forEach(v -> {
            themeHeadMapper.updateSelective(v);
        });
    }
}
