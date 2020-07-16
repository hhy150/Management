package com.example.management.mLogin.service;

import com.example.management.mLogin.pojo.LoginParam;
import com.example.management.mLogin.pojo.LoginResponse;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    public LoginResponse loginCheck(HttpServletRequest request, LoginParam param);
    public PageInfo getList(int pageNum, int pageSize, HttpServletRequest request);
}
