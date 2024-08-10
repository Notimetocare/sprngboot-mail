package com.darren.springbootmall.controller;

import com.darren.springbootmall.dto.UserLoginRequest;
import com.darren.springbootmall.dto.UserRegisterRequest;
import com.darren.springbootmall.pojo.User;
import com.darren.springbootmall.service.UserSrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserSrvice userSrvice;
    @PostMapping("users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        Integer userId = userSrvice.register(userRegisterRequest);

        User user = userSrvice.getUserByUserId(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @PostMapping("users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        User user = userSrvice.login(userLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);

    }
}
