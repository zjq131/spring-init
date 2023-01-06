package com.zjq.init.common;

import lombok.Getter;

/**
 * 错误状态码
 */
@Getter
public enum ErrorCode {

    PARAMS_ERROR(40000,"请求参数错误",""),
    NULL_ERROR(40001,"空数据",""),
    NOT_LOGIN(40100,"未登陆",""),
    NO_AUTH(40101,"无权限",""),
    FORBIDDEN(40301,"禁止访问",""),
    SYSTEM_ERROR(50000,"系统内部错误","");

    /**
     * 错误码
     */
    private final int code;

    /**
     * 错误信息
     */
    private final String message;

    /**
     * 描述
     */
    private final String description;


    ErrorCode(int code,String message,String description) {
        this.code = code;
        this.description = description;
        this.message = message;
    }


}
