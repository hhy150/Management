package com.example.management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Pattern;


public class Admin{

    @TableId(type = IdType.AUTO)
    protected Long id;
    @Pattern(regexp = "^[D|C]([\\u4E00-\\u9FA5A-Za-z0-9]+)$",message = "用户名格式不正确")
    private String username;
    @JsonIgnore
    private String password;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
