package com.zjq.init.exception;

import com.zjq.init.common.ErrorCode;
import com.zjq.init.common.Result;
import com.zjq.init.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 自定义异常处理
     * @param e 自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public Result<?> customExceptionHandle(CustomException e){
        log.error(e.getDescription(),e);
        return ResultUtils.failure(e.getCode(), e.getDescription());
    }

    /**
     * 系统内部异常
     * @param e 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Result<?> systemExceptionHandle(CustomException e){
        log.error(e.getMessage());
        return ResultUtils.failure(ErrorCode.SYSTEM_ERROR);
    }
}
