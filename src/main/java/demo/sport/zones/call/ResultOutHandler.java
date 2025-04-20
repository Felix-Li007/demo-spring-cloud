package demo.sport.zones.call;

import com.alibaba.fastjson.JSONObject;
import com.hrms.core.constant.ResultConstant;
import com.hrms.core.domian.ResultOut;
import com.hrms.core.exception.ExpResultMessage;
import com.hrms.core.exception.HrmsException;
import com.hrms.core.exception.HrmsResultOutException;
import com.hrms.frame.utils.function.HrmsFunction2Args;
import com.hrms.frame.utils.function.HrmsFunction3Args;
import demo.sport.zones.common.exception.ErrorMessageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 远程调用接口处理器
 * 对应响应不成功的抛出异常进行处理
 * 成功的直接返回数据对象
 *
 * @author zhengjun <br>
 * @date 2021/8/24 12:21 <br>
 * @since 1.7 <br>
 */
public class ResultOutHandler
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultOutHandler.class);

    public static <P, T> T handler(Function<P, ResultOut<T>> callback, P param, String description)
    {
        return handler(callback.apply(param), description);
    }


    public static <P, T> T handler(Function<P, ResultOut<T>> callback, P param)
    {
        return handler(callback.apply(param), null);
    }

    public static <P1, P2, T> T handler(HrmsFunction2Args<P1, P2, ResultOut<T>> callback, P1 p1, P2 p2, String description)
    {
        return handler(callback.apply(p1, p2), description);
    }

    public static <P1, P2, P3, T> T handler(HrmsFunction3Args<P1, P2, P3, ResultOut<T>> callback, P1 p1, P2 p2, P3 p3, String description)
    {
        return handler(callback.apply(p1, p2, p3), description);
    }

    public static <T> T handler(Supplier<ResultOut<T>> callback, String description)
    {
        return handler(callback.get(), description);
    }


    public static <T> T handler(ResultOut<T> resultOut, String description)
    {
        LOGGER.info(description + "，调用远程接口响应信息为：" + JSONObject.toJSONString(resultOut));
        if (resultOut != null && resultOut.getStatus() == ResultConstant.SUCCESS) {
            return resultOut.getValue();
        }
        String message = "调用远程接口失败！";

        if (ObjectUtils.isEmpty(resultOut)) {
            throw new HrmsException(ErrorMessageEnum.ERROR_CALL_OCCUR_FAIL, description + message);
        }

        message = resultOut.getMsg();
        // 非成功状态 以 HrmsResultOutException异常抛出
        throw new HrmsResultOutException(null, new ExpResultMessage()
        {
            @Override
            public int status()
            {
                return resultOut.getStatus();
            }

            @Override
            public long code()
            {
                return resultOut.getResultCode();
            }

            @Override
            public String message()
            {
                return resultOut.getMsg();
            }
        }, resultOut.getResultCode(), resultOut.getMsg(), resultOut.getStatus(), message);
    }
}
