package com.zjq.init.module.vo;

import lombok.Data;


/**
 * 用户信息脱敏
 */
@Data
public class UserVO {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;


    /**
     * 用户头像地址
     */
    private String avatarUrl;

    /**
     * 用户描述
     */
    private String userProfile;

    /**
     * 角色标识   0-普通管理员 1-超级管理员
     */
    private Integer userRole;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;


}
