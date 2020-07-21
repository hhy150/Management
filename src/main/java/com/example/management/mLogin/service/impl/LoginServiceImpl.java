package com.example.management.mLogin.service.impl;

import com.example.management.mLogin.dao.LoginMapper;
import com.example.management.mLogin.pojo.*;
import com.example.management.mLogin.service.LoginService;
import com.example.management.mLogin.util.ConstantUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    public LoginResponse loginCheck(HttpServletRequest request, LoginParam param) {
        String name=param.getName();
        String password=param.getPassword();
        LoginResponse loginResponse = new LoginResponse(1);
        if("admin".equals(name)){
            Admin superAdmin= loginMapper.getSuperAdmin(password);
            if(superAdmin==null){
                loginResponse.setErrorMessage(2);
            }else {
                loginResponse.setSucMessage(4);
                loginResponse.setResult(0);

            }
        }else if('C'==name.charAt(0)){
            Club club= loginMapper.getClubByName(name);
            if(club==null){
                loginResponse.setErrorMessage(1);
            }else if(!club.getPassword().equals(password)){
                loginResponse.setErrorMessage(2);
            }else {
                loginResponse.setSucMessage(3);
                loginResponse.setResult(0);

            }
        }else if('D'==name.charAt(0)){
            Department dept=loginMapper.getDeptByName(name);
            if(dept==null){
                loginResponse.setErrorMessage(1);
            }else if(!dept.getPassword().equals(password)){
                loginResponse.setErrorMessage(2);
            }else {
                loginResponse.setSucMessage(2);
                loginResponse.setResult(0);

            }
        }else {
            Student student=loginMapper.getStuByName(name);
            if(student==null){
                loginResponse.setErrorMessage(1);
            }else if(!student.getStuPassword().equals(password)){
                loginResponse.setErrorMessage(2);
            }else {
                loginResponse.setSucMessage(1);
                loginResponse.setResult(0);
            }
        }
        if(loginResponse.getResult()==0){
            request.getSession().setAttribute(ConstantUtils.USER_NAME, name);
            request.getSession().setAttribute(ConstantUtils.USER_ROLE, loginResponse.getSucMessage());
        }
        return loginResponse;
    }

    @Override
    public PageInfo getList(int pageNum, int pageSize, HttpServletRequest request) {
        int role = (int)request.getSession().getAttribute(ConstantUtils.USER_ROLE);
        String name=String.valueOf(request.getSession().getAttribute(ConstantUtils.USER_NAME));
        PageHelper.startPage(pageNum, pageSize);
        switch (role){
            case 2:
                Department department=loginMapper.getDeptByName(name);
                List<Student> list=loginMapper.getStuList(department.getId());
                return new PageInfo<>(list);
            case 3:
                Club club =loginMapper.getClubByName(name);
                List<Department> list1=loginMapper.getDeptList(club.getId());
                return new PageInfo<>(list1);
            case 4:
                Admin admin=loginMapper.getSuperAdmin(name);
                List<Club> list2=loginMapper.getClubList();
                return new PageInfo<>(list2);
            default:
                return null;
        }

    }


}
