package demo.sport.zones.common.exception;


import com.hrms.core.exception.ExpResultMessage;
import com.hrms.core.exception.HrmsException;

/**
 * <p>
 * 异常错误码枚举定义
 * </p>
 *
 * @author lhy
 * @date 2020/12/3
 */
public enum ErrorMessageEnum implements ExpResultMessage
{
    ERROR_PFILE_NOT_EXIST(-1, 22701001, "用户档案不存在"),


    ERROR_CANNOT_OBTAIN_LOCK(-1, 22701002, "无法获取到修改锁"),

    /**
     * 场馆开放中，无法删除
     */
    RESULT_CODE_996(-1, -22701996, "场馆开放中，无法删除"),

    /**
     * -22701997 : 接口入参校验失败
     */
    RESULT_CODE_997(-1, -22701997, "入参校验失败"),

    ERROR_THEME_NOT_EXIST(-1, -22701998, "主题不存在"),

    /**
     * 场馆不存在
     */
    RESULT_CODE_998(-1, -22701998, "场馆不存在"),

    ERROR_CALL_OCCUR_FAIL(-1, -22701999, "API接口调用系统异常");


    private final int status;
    private final long code;
    private final String message;

    ErrorMessageEnum(int status, long code, String message)
    {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public int status()
    {
        return this.status;
    }

    @Override
    public long code()
    {
        return this.code;
    }

    @Override
    public String message()
    {
        return this.message;
    }

    public HrmsException createException()
    {
        return new HrmsException(this);
    }

    public HrmsException createException(String overrideMsg)
    {
        return new HrmsException(this, overrideMsg);
    }

    public HrmsException createException(Throwable tw)
    {
        return new HrmsException(this, tw);
    }

    public HrmsException createException(Object convertInfo, Throwable tw)
    {
        return new HrmsException(this, convertInfo, tw);
    }

    @Override
    public String toString()
    {
        return "ArExpResultMessage{" +
                "status=" + status +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
