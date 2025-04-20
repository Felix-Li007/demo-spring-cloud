package demo.sport.zones.entity;

import demo.sport.constants.TimeUnitEnum;
import demo.sport.constants.zones.ValueModeEnum;

import java.io.Serializable;

/**
 * 货币使用限制
 *
 * @TableName t_place_cion_limit
 */
public class CionLimitInfoBO implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    private Integer sequenceId;
    /**
     * 主题编号
     */
    private String themeNo;
    /**
     * 货币代码
     */
    private String cionCode;
    /**
     * 最高
     */
    private Integer limitValue;
    /**
     * 00-分钟，10-小时，20-天数，30-月数，40-年数
     */
    private TimeUnitEnum timeUnit;
    /**
     * 00-固定模式，10-比例模式
     */
    private ValueModeEnum valueMode;


    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    public Integer getSequenceId()
    {
        return sequenceId;
    }

    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    public void setSequenceId(Integer sequenceId)
    {
        this.sequenceId = sequenceId;
    }

    /**
     * 主题编号
     */
    public String getThemeNo()
    {
        return themeNo;
    }

    /**
     * 主题编号
     */
    public void setThemeNo(String themeNo)
    {
        this.themeNo = themeNo;
    }

    /**
     * 货币代码
     */
    public String getCionCode()
    {
        return cionCode;
    }

    /**
     * 货币代码
     */
    public void setCionCode(String cionCode)
    {
        this.cionCode = cionCode;
    }

    /**
     * 最高
     */
    public Integer getLimitValue()
    {
        return limitValue;
    }

    /**
     * 最高
     */
    public void setLimitValue(Integer limitValue)
    {
        this.limitValue = limitValue;
    }

    public TimeUnitEnum getTimeUnit()
    {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnitEnum timeUnit)
    {
        this.timeUnit = timeUnit;
    }

    public ValueModeEnum getValueMode()
    {
        return valueMode;
    }

    public void setValueMode(ValueModeEnum valueMode)
    {
        this.valueMode = valueMode;
    }
}