package demo.sport.zones.common.constant;

import com.hrms.frame.sequence.IdItem;

/**
 * 序列定义
 *
 * @author zhengjun <br>
 * @date 2021/8/26 9:52 <br>
 * @since 1.7 <br>
 */
public enum ZonesSequenEnum implements IdItem
{


    /**
     * 主题编号生成编码
     */
    THEME_CODE("13", 100, "竞技场馆-竞技档案表code生成编号"),
    /**
     * 竞技档案生成编码
     */
    PRO_FILE_CODE("10", 100, "竞技场馆-竞技档案表code生成编号"),

    /**
     * 场馆日期编码生成编码
     */
    ZONE_DATE_CODE("11", 100, "竞技场馆-场馆日期表code生成编号"),

    /**
     * 场馆日期编码生成编码
     */
    ZONE_CODE("12", 100, "竞技场馆-场馆信息表code生成编号");

    private final String bizTag;
    private final int step;
    private final String desc;

    ZonesSequenEnum(String bizTag, int step, String desc)
    {
        this.bizTag = bizTag;
        this.step = step;
        this.desc = desc;
    }

    @Override
    public String bizTag()
    {
        return bizTag;
    }

    @Override
    public int step()
    {
        return step;
    }

    @Override
    public String desc()
    {
        return desc;
    }
}
