package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.management.entity.*;
import com.example.management.mapper.ClubMapper;
import com.example.management.mapper.DepartmentMapper;
import com.example.management.mapper.StudentMapper;
import com.example.management.mapper.SuperAdminMapper;
import com.example.management.service.CommonService;
import com.example.management.util.ConstantUtils;
import com.example.management.util.MD5Util;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CommonServiceImpl implements CommonService {


    private StudentMapper studentMapper;
    private ClubMapper clubMapper;
    private DepartmentMapper departmentMapper;
    private SuperAdminMapper superAdminMapper;
    private HttpSession httpSession;
    @Autowired
    public CommonServiceImpl(ClubMapper clubMapper, DepartmentMapper departmentMapper, StudentMapper studentMapper, SuperAdminMapper superAdminMapper, HttpSession httpSession){
        this.clubMapper=clubMapper;
        this.departmentMapper=departmentMapper;
        this.studentMapper=studentMapper;
        this.superAdminMapper = superAdminMapper;
        this.httpSession = httpSession;
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
    public Boolean changePwd(String newPwd) {
        //此处需要判断一下
        int role=attributes().getRole();
        String username =attributes().getUsername();
        newPwd= MD5Util.Md5(newPwd);
        //1社员2社团部门管理员3社团admin4superadmin 0 no suc
        switch (role){
            case 1:
                UpdateWrapper<Student> wrapper = new UpdateWrapper<Student>()
                        .eq("stuid", username)
                        .set("password", newPwd);
                studentMapper.update(null,wrapper);
                return true;
            case 2:
                UpdateWrapper<Department> wrapper2 = new UpdateWrapper<Department>()
                        .eq("username", username)
                        .set("password", newPwd);
                departmentMapper.update(null,wrapper2);
                return true;
            case 3:
                UpdateWrapper<Club> wrapper3 = new UpdateWrapper<Club>()
                        .eq("username", username)
                        .set("password", newPwd);
                clubMapper.update(null,wrapper3);
                return true;
            case 4:
                UpdateWrapper<Admin> wrapper4 = new UpdateWrapper<Admin>()
                        .eq("username", username)
                        .set("password", newPwd);
                superAdminMapper.update(null,wrapper4);
                return true;
        }
         return false;
    }



}
