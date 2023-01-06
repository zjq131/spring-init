package com.zjq.init.common;

import lombok.Data;

/**
 *  统一返回封装类
 */

@Data
public class Result <T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 数据信息
     */
    private String message;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 返回的数据
     */
    private T result;


    public Result(int code, String message,String description, T result) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.result = result;
    }


}
