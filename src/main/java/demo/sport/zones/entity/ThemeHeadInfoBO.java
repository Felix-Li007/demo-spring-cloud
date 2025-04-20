package demo.sport.zones.entity;

import java.io.Serializable;

/**
 * 默认头像列表
 *
 * @TableName t_place_head_info
 */
public class ThemeHeadInfoBO implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
     * 自增序号，作为主键使用，无实际业务含义
     */
    private Integer sequenceId;
    /**
     * 头像编号
     */
    private String headNo;
    /**
     *
     */
    private String themeNo;
    /**
     * 头像地址
     */
    private String headImage;
    /**
     * 国家代码
     */
    private String nationCode;

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
     * 头像编号
     */
    public String getHeadNo()
    {
        return headNo;
    }

    /**
     * 头像编号
     */
    public void setHeadNo(String headNo)
    {
        this.headNo = headNo;
    }

    /**
     *
     */
    public String getThemeNo()
    {
        return themeNo;
    }

    /**
     *
     */
    public void setThemeNo(String themeNo)
    {
        this.themeNo = themeNo;
    }

    /**
     * 头像地址
     */
    public String getHeadImage()
    {
        return headImage;
    }

    /**
     * 头像地址
     */
    public void setHeadImage(String headImage)
    {
        this.headImage = headImage;
    }

    /**
     * 国家代码
     */
    public String getNationCode()
    {
        return nationCode;
    }

    /**
     * 国家代码
     */
    public void setNationCode(String nationCode)
    {
        this.nationCode = nationCode;
    }


}