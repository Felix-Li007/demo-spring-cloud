package demo.sport.zones.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


public class PlayTaskInfoBO implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String accountNo;
    /**
     * 主题编号
     */
    private String themeNo;
    /**
     * 货币代码
     */
    private String playCode;
    /**
     * 最高
     */
    private String playName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fetchTime;

    public LocalDateTime getFetchTime()
    {
        return fetchTime;
    }

    public void setFetchTime(LocalDateTime fetchTime)
    {
        this.fetchTime = fetchTime;
    }

    public String getAccountNo()
    {
        return accountNo;
    }

    public void setAccountNo(String accountNo)
    {
        this.accountNo = accountNo;
    }

    public String getThemeNo()
    {
        return themeNo;
    }

    public void setThemeNo(String themeNo)
    {
        this.themeNo = themeNo;
    }

    public String getPlayCode()
    {
        return playCode;
    }

    public void setPlayCode(String playCode)
    {
        this.playCode = playCode;
    }

    public String getPlayName()
    {
        return playName;
    }

    public void setPlayName(String playName)
    {
        this.playName = playName;
    }

}