package com.example.management.service.impl;

import com.example.management.entity.*;
import com.example.management.mapper.LoginMapper;
import com.example.management.mapper.StudentMapper;
import com.example.management.service.LoginService;
import com.example.management.util.ConstantUtils;
import com.example.management.util.ToolUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@CacheConfig(cacheNames = "login")
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    HttpSession httpSession;

    @Override
    public LoginResponse loginCheck(HttpServletRequest request, LoginParam param) {
        String username=param.getName();
        String password=param.getPassword();
        int role=ToolUtil.getRole(username);
        LoginResponse loginResponse = new LoginResponse(1,0,0);
        switch (role){
            case 4:
                Admin superAdmin= loginMapper.getSuperAdmin(username);
                if(superAdmin==null){
                    loginResponse.setErrorMessage(2);
                }else {
                    loginResponse.setSucMessage(4);
                    loginResponse.setResult(0);
                }
                break;
            case 3:
                Club club= loginMapper.getClubByName(username);
                if(club==null){
                    loginResponse.setErrorMessage(1);
                }else if(!club.getPassword().equals(password)){
                    loginResponse.setErrorMessage(2);
                }else {
                    loginResponse.setSucMessage(3);
                    loginResponse.setResult(0);
                }
                break;
            case 2:
                Department dept=loginMapper.getDeptByName(username);
                if(!dept.getPassword().equals(password)){
                    loginResponse.setErrorMessage(2);
                }else {
                    loginResponse.setSucMessage(2);
                    loginResponse.setResult(0);
                }
                break;
            default:
                Student student=loginMapper.getStu(username);
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
            System.out.println(username+"suc");
            System.out.println(loginResponse.getSucMessage());

            request.getSession().setAttribute(ConstantUtils.USER_NAME, username);
            request.getSession().setAttribute(ConstantUtils.USER_ROLE, loginResponse.getSucMessage());
            System.out.println(request.getSession().getAttribute(ConstantUtils.USER_ROLE));
        }else{
            request.getSession().removeAttribute(ConstantUtils.USER_NAME);
            request.getSession().removeAttribute(ConstantUtils.USER_ROLE);
        }
        return loginResponse;
    }

    @Override
    public List getList(){
        List<Club> list=loginMapper.getClubList();
        return ToolUtil.saveClub(list);
    }

    //可以提取出来获取session中的东西
    public SessionAttributes attributes(){
        return getAttributes(httpSession);
    }


    @NotNull
    static SessionAttributes getAttributes(HttpSession httpSession) {
        int role=0;
        String username=" ";
        Object o = httpSession.getAttribute(ConstantUtils.USER_ROLE);
        Object name= httpSession.getAttribute(ConstantUtils.USER_NAME);
        if(o!=null&& name!=null){
            role=(int)o;
            username = String.valueOf(name);
        }
        SessionAttributes attributes = new SessionAttributes(role, username);
        return attributes;
    }

    @Override
    public PageInfo getListByRole(int pageNum, int pageSize) {
        int role =attributes().getRole();
        String username=attributes().getUsername();
        PageHelper.startPage(pageNum, pageSize);
        switch (role){
            case 2:
                Department department=loginMapper.getDeptByName(username);
                List<Student> list=studentMapper.getStuList(department.getId());
                return new PageInfo<>(ToolUtil.saveStu(list));
            case 3:
                Club club =loginMapper.getClubByName(username);
                List<Department> list1=loginMapper.getDeptList(club.getId());
                return new PageInfo<>(ToolUtil.saveDept(list1));
            case 4:
                List<Club> list2=loginMapper.getClubList();
                return new PageInfo<>(ToolUtil.saveClub(list2));
            default:
                return null;
        }

    }
    @Override
    public LoginResponse getRole(HttpServletRequest request) {
        int role = (int) request.getSession().getAttribute(ConstantUtils.USER_ROLE);
        return new LoginResponse(0, role, 0);
    }


}
