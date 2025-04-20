package demo.sport.zones.domain;


public class ThemeTaskInfoPO extends ZonesZoneBasePO
{


    private static final long serialVersionUID = 1L;
    /**
     * 主题编号
     */
    private String themeNo;
    /**
     * 任务编号
     */
    private String taskNo;

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


    public String getTaskNo()
    {
        return taskNo;
    }

    public void setTaskNo(String taskNo)
    {
        this.taskNo = taskNo;
    }
}