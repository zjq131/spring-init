package com.zjq.init.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjq.init.constant.UserConstant;
import com.zjq.init.exception.CustomException;
import com.zjq.init.mapper.UserMapper;
import com.zjq.init.module.domain.User;
import com.zjq.init.module.request.LoginRequest;
import com.zjq.init.module.request.LoginResponse;
import com.zjq.init.common.ErrorCode;
import com.zjq.init.module.dto.UserDTO;
import com.zjq.init.module.vo.UserVO;
import com.zjq.init.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;


/**
* @author ZJQ
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{


    @Override
    public boolean register(UserDTO userDTO) {
        // 验证参数
        verifyRegister(userDTO);
        // 校验两次密码是否一致
        if (!StringUtils.equals(userDTO.getUserPassword(),userDTO.getAckPassword())){
            throw new CustomException(ErrorCode.PARAMS_ERROR,"两次密码不一致");
        }
        // 写入数据库
        // 密码加密 【盐值 + md5加密】
        String saltPassword = DigestUtils.md5DigestAsHex((UserConstant.SALT + userDTO.getUserPassword())
                .getBytes(StandardCharsets.UTF_8));
        User user = User.builder().username(userDTO.getUsername()).userPassword(saltPassword).build();
        return this.save(user);
    }


    @Override
    public void verifyRegister(UserDTO userDTO) {
        // 验证参数
        if(Objects.isNull(userDTO)){
            throw new CustomException(ErrorCode.PARAMS_ERROR);
        }
        if(StringUtils.isBlank(userDTO.getUsername()) || userDTO.getUsername().length() > 20){
            throw new CustomException(ErrorCode.PARAMS_ERROR,"用户名格式有误");
        }
        if(StringUtils.isBlank(userDTO.getUserPassword()) || userDTO.getUserPassword().length() < 6 || userDTO.getUserPassword().length() > 20){
            throw new CustomException(ErrorCode.PARAMS_ERROR,"密码格式有误");
        }
        if(StringUtils.isBlank(userDTO.getAckPassword())  || userDTO.getUserPassword().length() < 6 || userDTO.getAckPassword().length() > 20){
            throw new CustomException(ErrorCode.PARAMS_ERROR,"确认密码格式有误");
        }
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        // 通过数据库验证用户名与密码
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,loginRequest.getUsername());
        queryWrapper.eq(User::getUserPassword, DigestUtils.md5DigestAsHex((UserConstant.SALT +loginRequest.getPassword()).getBytes(StandardCharsets.UTF_8)));
        User loginUser = this.getOne(queryWrapper);
        if(Objects.isNull(loginUser)) {
            throw new CustomException(ErrorCode.PARAMS_ERROR,"用户不存在");
        }
        // 封装数据登录对象
        return LoginResponse.builder().userId(loginUser.getId()).token(UUID.fastUUID().toString(true)).role(new LoginResponse.RoleInfo("admin","admin")).build();
    }

    @Override
    public void verifyLogin(LoginRequest loginRequest) {
        // 获取参数
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        // 验证参数
        if(StringUtils.isBlank(username)){
            throw new CustomException(ErrorCode.PARAMS_ERROR,"用户名不能为空");
        }
        if(StringUtils.isBlank(password)){
            throw new CustomException(ErrorCode.PARAMS_ERROR,"密码不能为空");
        }
    }

    @Override
    public UserVO getCurrentUser() {
        //获取当前登录用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,"zjx");
        User loginUser = this.getOne(queryWrapper);
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(loginUser,userVO);
        return userVO;
    }

    @Override
    public Page<UserVO> getUserListByPage(Long pageNum, Long pageSize) {
        // 验证参数
        this.verityPageParams(pageNum,pageSize);
        // 分页处理
        Page<User> userPage = new Page<>(pageNum,pageSize);
        userPage = this.page(userPage);
        // 脱敏数据
        List<UserVO> userVOList = userPage.getRecords().stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).toList();
        // 重新封装数据
        Page<UserVO> userVOPage = new Page<>();
        BeanUtils.copyProperties(userPage,userVOPage);
        userVOPage.setRecords(userVOList);
        return userVOPage;
    }

    @Override
    public void verityPageParams(long pageNum, long pageSize) {
        if (pageNum <= 0 || pageSize <= 0){
            throw new CustomException(ErrorCode.PARAMS_ERROR,"分页参数出错");
        }
    }

}




