package com.zjq.init.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjq.init.module.domain.User;
import com.zjq.init.module.request.LoginRequest;
import com.zjq.init.module.request.LoginResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjq.init.module.dto.UserDTO;
import com.zjq.init.module.vo.UserVO;

/**
* @author ZJQ
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2022-12-30 20:04:32
*/
public interface UserService extends IService<User> {

    /**
     *  用户注册
     * @param userDTO  用户输入数据
     */
    boolean register(UserDTO userDTO);


    /**
     * 用户注册验证
     * @param userDTO 用户输入数据
     */
    void verifyRegister(UserDTO userDTO);

    /**
     * 用户登录
     */
    LoginResponse login(LoginRequest loginRequest);

    /**
     * 用户登录验证
     */
    void verifyLogin(LoginRequest loginRequest);

    /**
     * 获取当前用户
     * @return
     */
    UserVO getCurrentUser();

    /**
     * 获取用户列表
     * @param pageNum 页数
     * @param pageSize 条数
     * @return
     */
    Page<UserVO> getUserListByPage(Long pageNum, Long pageSize);

    /**
     * 验证分页参数
     * @param pageNum 页数
     * @param pageSize 条数
     */
    void verityPageParams(long pageNum, long pageSize);
}
