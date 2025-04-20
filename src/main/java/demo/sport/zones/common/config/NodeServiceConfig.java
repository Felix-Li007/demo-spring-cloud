package demo.sport.zones.common.config;

import com.hrms.gateway.api.NodeApiHelper;
import demo.sport.zones.call.INodeServiceCaller;
import demo.sport.zones.call.ResultOutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置node 网关调用助手
 *
 * @author zhengjun
 * @date 2022/3/2
 * @since v1.0
 */
@Configuration
public class NodeServiceConfig
{

    @Bean
    public NodeApiHelper nodeApiHelper(INodeServiceCaller nodeServiceCaller)
    {
        return new NodeApiHelper(nodeServiceCaller, resultOut -> ResultOutHandler.handler(resultOut, "调用node网关服务"));
    }
}
