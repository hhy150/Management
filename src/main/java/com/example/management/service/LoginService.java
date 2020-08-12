package com.example.management.service;

import com.example.management.entity.LoginParam;
import com.example.management.entity.LoginResponse;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LoginService {
     LoginResponse loginCheck(HttpServletRequest request, LoginParam param);

     LoginResponse getRole(HttpServletRequest request);

     PageInfo getListByRole(int pageNum, int pageSize);

     List getList();
    }
