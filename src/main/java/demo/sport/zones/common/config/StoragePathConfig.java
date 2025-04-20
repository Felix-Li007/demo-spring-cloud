package demo.sport.zones.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;


@Component
@RefreshScope
@ConfigurationProperties(prefix = "storage.path-config")
public class StoragePathConfig
{

    /**
     * 对象存储根目录
     */
    private String rootPath;
    /**
     * 私有读目录
     */
    private String headPath;
    /**
     * 本地历史工作目录
     */
    private String iconsPath;
    /**
     * 得分上报路径
     */
    private String propsPath;

    public String getRootPath()
    {
        return rootPath;
    }

    public void setRootPath(String rootPath)
    {
        this.rootPath = rootPath;
    }

    public String getHeadPath()
    {
        return headPath;
    }

    public void setHeadPath(String headPath)
    {
        this.headPath = headPath;
    }

    public String getIconsPath()
    {
        return iconsPath;
    }

    public void setIconsPath(String iconsPath)
    {
        this.iconsPath = iconsPath;
    }

    public String getPropsPath()
    {
        return propsPath;
    }

    public void setPropsPath(String propsPath)
    {
        this.propsPath = propsPath;
    }


}
