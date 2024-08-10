package com.darren.springbootmall.service;

import com.darren.springbootmall.dto.UserLoginRequest;
import com.darren.springbootmall.dto.UserRegisterRequest;
import com.darren.springbootmall.pojo.User;

public interface UserSrvice {


    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserByUserId(Integer userId);

    User login(UserLoginRequest userLoginRequest);
}
