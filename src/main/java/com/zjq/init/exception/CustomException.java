package com.zjq.init.exception;

import com.zjq.init.common.ErrorCode;
import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class CustomException extends RuntimeException {


    /**
     * 错误码
     */
    private final int code;

    /**
     * 错误描述
     */
    private final String description;

    public CustomException(ErrorCode errorCode, String description) {
        super(description);
        this.code = errorCode.getCode() ;
        this.description = description;
    }

    public CustomException(ErrorCode errorCode) {
        this(errorCode,errorCode.getMessage());
    }
}
