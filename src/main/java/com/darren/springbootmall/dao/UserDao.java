package com.darren.springbootmall.dao;

import com.darren.springbootmall.dto.UserRegisterRequest;
import com.darren.springbootmall.pojo.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserByUserId(Integer userId);

    User getUserByEmail(String email);
}
