package demo.sport.zones.common.exception;/*
 * Copyright (c) 2022, 北京中体彩运营管理公司  All rights reserved.
 */


import com.hrms.core.domian.ResultOut;
import com.hrms.core.exception.ExpResultMessage;
import com.hrms.core.exception.HrmsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;


/**
 * 全局异常处理
 * - 参数校验
 * - 业务异常统一抛出处理
 * - 异常拦截
 *
 * @author lhy
 * @date 2020/12/3
 */

@RestControllerAdvice
public class GlobalExceptionHandler
{

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String PREFIX = "hrms-sport-zone接口调用异常: ";

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultOut<Object> globalExceptionHandler(Exception ex)
    {
        String message = PREFIX + ex.toString();
        logger.error(message, ex);
        ResultOut<Object> objectResultOut = new ResultOut<>();
        //objectResultOut.buildFail(ArExpResultMessage.RESULT_CODE_999.code(), ArExpResultMessage.RESULT_CODE_999.message());
        return objectResultOut;
    }


    @ExceptionHandler({HrmsException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultOut<Object> globalExceptionHandler(HrmsException ex)
    {
        String message = ex.getMessage();
        ExpResultMessage result = ex.getResult();
        if (result == null) {
            result = ErrorMessageEnum.ERROR_CALL_OCCUR_FAIL;
        } else {
            message = PREFIX.concat(result.message());
        }
        logger.error(PREFIX.concat(ex.toString()), ex);
        logger.info(result.toString());
        return new ResultOut<>().buildFail(result.code(), message);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultOut<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder("api接口入参校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        logger.info(msg, e);
        ResultOut<String> objectResultOut = new ResultOut<>();
        objectResultOut.buildFail(ErrorMessageEnum.RESULT_CODE_997.code(), msg);
        return objectResultOut;
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultOut<String> handleConstraintViolationException(ConstraintViolationException ex)
    {
        StringBuilder msg = new StringBuilder();
        msg.append("api接口入参校验失败：");
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            String message = constraintViolation.getMessage();
            msg.append("[").append(message).append("]");
        }
        logger.error(msg.toString(), ex);
        ResultOut<String> objectResultOut = new ResultOut<>();
        objectResultOut.buildFail(ErrorMessageEnum.RESULT_CODE_997.code(), msg.toString());
        return objectResultOut;
    }
}