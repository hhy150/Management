package com.example.management.mLogin.service;

import com.example.management.mLogin.pojo.LoginParam;
import com.example.management.mLogin.pojo.LoginResponse;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LoginService {
    public LoginResponse loginCheck(HttpServletRequest request, LoginParam param);

    public List getList();

    public LoginResponse getRole(HttpServletRequest request);

    public PageInfo getListByRole(int pageNum, int pageSize, HttpServletRequest request);
    }
