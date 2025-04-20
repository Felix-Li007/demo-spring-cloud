package demo.sport.zones.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 对象存储配置
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "storage")
public class StorageBaseConfig
{

    /**
     * cdn 域名
     */
    private String ossCname;

    /**
     * 存储桶密钥
     */
    private String ossBucket;


    public String getOssCname()
    {
        return ossCname;
    }

    public void setOssCname(String ossCname)
    {
        this.ossCname = ossCname;
    }

    public String getOssBucket()
    {
        return ossBucket;
    }

    public void setOssBucket(String ossBucket)
    {
        this.ossBucket = ossBucket;
    }
}
