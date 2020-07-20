package com.example.management.mLogin.pojo;


import javax.validation.constraints.NotBlank;

public class LoginParam {
    @NotBlank(message = "用户名不能为空")
    private String name;

    @NotBlank(message = "密码不能为空")
    private String password;

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
