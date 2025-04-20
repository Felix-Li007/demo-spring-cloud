package demo.sport.zones.call;

import com.hrms.core.domian.ResultOut;
import com.hrms.core.exception.HrmsCallerException;
import com.hrms.gateway.api.NodeApi;
import com.hrms.gateway.domain.node.NodeRequest;
import demo.sport.zones.common.exception.ErrorMessageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author zhengjun
 * @date 2022/3/2
 * @since v1.0
 */
@SuppressWarnings("rawtypes")
@FeignClient(value = "${service.call.gateway.node}", path = "/gw/node/", contextId = "unifyOrderApi", fallbackFactory = INodeServiceCaller.NodeApiCallerFallbackFactory.class)
public interface INodeServiceCaller extends NodeApi
{

    @Component
    class NodeApiCallerFallbackFactory implements FallbackFactory<INodeServiceCaller>
    {

        private static final Logger LOGGER = LoggerFactory.getLogger(NodeApiCallerFallbackFactory.class);

        @Override
        public INodeServiceCaller create(Throwable cause)
        {
            return new INodeServiceCaller()
            {

                @Override
                public ResultOut callNodeJs(NodeRequest nodeRequest)
                {
                    LOGGER.warn("调用node【NodeApiCaller#callNodeJs】远程接口调用异常", cause);
                    throw new HrmsCallerException(cause, ErrorMessageEnum.ERROR_CALL_OCCUR_FAIL, "NodeApiCaller#callNodeJs接口异常");
                }
            };
        }
    }
}
