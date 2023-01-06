package com.zjq.init.common;

/**
 * 返回封装类的二次封装
 */
public class ResultUtils {

    /**
     * 成功
     */
   public static Result<?> success(){ return new Result<>(0,"ok","",null); }
   public static <T> Result<T> success(T data){ return new Result<>(0,"ok","",data);}


    /**
     * 失败
     */
    public static Result<?> failure(int code,String description){
        return new Result<>(code,description,"",null);
    }

    public static Result<?> failure(ErrorCode code){
        return new Result<>(code.getCode(), code.getMessage(),"",null);
    }

    public static Result<?> failure(ErrorCode code,String description){
        return new Result<>(code.getCode(), description,"",null);
    }
}
