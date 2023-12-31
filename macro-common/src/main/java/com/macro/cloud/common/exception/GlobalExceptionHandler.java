package com.macro.cloud.common.exception;


import com.macro.cloud.common.domain.Response;
//import com.pig4cloud.plugin.idempotent.exception.IdempotentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author baobao
 * @create 2021-09-09 22:31
 * @description 全局异常处理器
 */
@ConditionalOnWebApplication
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 自定义业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Response<Void> handleCustomException(BusinessException e) {
        return Response.error(e.getMessage());
    }

    /**
     * 数据重复异常
     */
//    @ExceptionHandler(DuplicateKeyException.class)
//    public Response<Void> handleDuplicateKeyException(DuplicateKeyException e) {
//        log.error(e.getMessage(), e);
//        return Response.error("数据重复");
//    }

    /**
     * 数据校验异常
     */
    @ExceptionHandler(BindException.class)
    public Response<Void> handleValidateException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return Response.error(message);
    }

    /**
     * 数据校验异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Response<Void> handleValidateException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        String message = e.getConstraintViolations().iterator().next().getMessage();
        return Response.error(message);
    }

    /**
     * 数据校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<Void> handleValidateException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return Response.error(message);
    }

    /**
     * 参数非法异常
     * 重复提交异常
     */
//    @ExceptionHandler({IllegalArgumentException.class, IdempotentException.class})
//    public Response<Void> handleIllegalArgumentException(Exception e) {
//        log.error(e.getMessage(), e);
//        return Response.error(e.getMessage());
//    }

    /**
     * 其余未知异常
     */
    @ExceptionHandler(Exception.class)
    public Response<Void> handleOtherException(Exception e) {
        log.error(e.getMessage(), e);
        return Response.error("系统繁忙");
    }
}
