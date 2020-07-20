package com.example.management.mLogin.controller;

import com.example.management.mLogin.pojo.LoginParam;
import com.example.management.mLogin.pojo.LoginResponse;
import com.example.management.mLogin.service.LoginService;
import com.example.management.mLogin.util.ConstantUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/login/check")
    public LoginResponse login(@Valid @RequestBody LoginParam param, BindingResult bindingResult, HttpServletRequest request) {
        if(bindingResult.hasErrors()){
            LoginResponse loginResponse=new LoginResponse(1);
            loginResponse.setSucMessage(0);
            loginResponse.setErrorMessage(3);
            return loginResponse;
        }
        return loginService.loginCheck(request, param);
    }

    @RequestMapping("/login/list")
    public PageInfo getList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "5")int pageSize, HttpServletRequest request){

        return loginService.getList(pageNum, pageSize, request);
    }

    @RequestMapping("/login/logout")
    public String logout(HttpServletRequest request){
        String msg="退出失败";
        try {
            request.getSession().removeAttribute(ConstantUtils.USER_NAME);
            request.getSession().removeAttribute(ConstantUtils.USER_ROLE);
            msg="退出成功";
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }



}
