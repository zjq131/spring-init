package com.zjq.init.module.dto;


import lombok.Data;

/**
 *  用户信息脱敏
 */
@Data
public class UserDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 确认密码
     */
    private String ackPassword;
}
