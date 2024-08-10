package com.darren.springbootmall.service.impl;

import com.darren.springbootmall.dao.UserDao;
import com.darren.springbootmall.dto.UserLoginRequest;
import com.darren.springbootmall.dto.UserRegisterRequest;
import com.darren.springbootmall.pojo.User;
import com.darren.springbootmall.service.UserSrvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServicImpl implements UserSrvice {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        //檢查emaul是否創建過
        if (user!= null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        //使用MD5生成密碼的雜湊直
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);


        //創建帳號
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserByUserId(Integer userId) {
        return userDao.getUserByUserId(userId);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user =  userDao.getUserByEmail(userLoginRequest.getEmail());

        //檢查user是否存在
        if (user==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email does not exist");
        }
        //使用md5生成密碼雜湊直
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        //檢查密碼是否正確
        if(user.getPassword().equals(hashedPassword)){
            return user;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is incorrect");
        }

    }
}
