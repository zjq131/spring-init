package com.zjq.init.module.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 *  登录返回数据
 */
@Data
@Builder
public class LoginResponse {

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 登录Token
     */
    private String token;

    /**
     *  用户权限
     *
     */
    private RoleInfo role;
    /**
     * 权限信息
     */
    @Data
    @AllArgsConstructor
    public static class RoleInfo {

        private String roleName;

        private String value;
    }

}
