package com.darren.springbootmall.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterRequest {
    @Email(message = "請輸入有效的Email")
    @NotBlank(message = "用户名不能為空")
    private String email;
    @NotBlank(message = "密碼不能為空")
    private String password;
}
