package com.zjq.init.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjq.init.common.Result;
import com.zjq.init.common.ResultUtils;
import com.zjq.init.module.dto.UserDTO;
import com.zjq.init.module.request.LoginRequest;
import com.zjq.init.module.request.LoginResponse;
import com.zjq.init.module.vo.UserVO;
import com.zjq.init.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;




/**
 * 用户控制层
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public Result<Page<UserVO>> getUserList(@RequestParam("pageNum") Long pageNum, @RequestParam("pageSize") Long pageSize){
        userService.verityPageParams(pageNum,pageSize);
        return ResultUtils.success(userService.getUserListByPage(pageNum,pageSize));
    }

    @GetMapping("/current")
    public Result<UserVO> getCurrentLoginUser(){
        return ResultUtils.success(userService.getCurrentUser());
    }

    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody UserDTO userDTO){
        // 处理参数
        userService.verifyRegister(userDTO);
        // 处理注册逻辑
        return ResultUtils.success(userService.register(userDTO));
    }

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        // 验证参数
        userService.verifyLogin(loginRequest);
        // 登录逻辑
        return ResultUtils.success(userService.login(loginRequest));
    }
}
