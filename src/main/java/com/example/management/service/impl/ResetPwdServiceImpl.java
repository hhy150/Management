package com.example.management.service.impl;

import com.example.management.entity.*;
import com.example.management.mapper.ClubMapper;
import com.example.management.mapper.DepartmentMapper;
import com.example.management.mapper.StudentMapper;
import com.example.management.service.ResetPwdService;
import com.example.management.util.ConstantUtils;
import com.example.management.util.MD5Util;
import com.sun.mail.imap.protocol.BODY;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Service
public class ResetPwdServiceImpl implements ResetPwdService  {

    private  StudentMapper studentMapper;
    private  ClubMapper clubMapper;
    private  DepartmentMapper departmentMapper;
    private  HttpSession httpSession;

    @Autowired
    public ResetPwdServiceImpl(ClubMapper clubMapper, DepartmentMapper departmentMapper, StudentMapper studentMapper, HttpSession httpSession){
        this.clubMapper=clubMapper;
        this.departmentMapper=departmentMapper;
        this.studentMapper=studentMapper;
        this.httpSession = httpSession;
    }

    private final static Logger log= LoggerFactory.getLogger(ResetPwdServiceImpl.class);

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

    private boolean hasAuth(int type){
        int role=getAttributes(httpSession).getRole();
        //没有权限的不可以修改
        switch (role){
            case 2:
                if(type != RoleParam.STUDENT){
                    log.info("没有权限");
                    return false;
                }
            case 3:
                if(type == RoleParam.CLUB_ADMIN){
                    log.info("没有权限");
                    return false;
                }
        }
        return true;
    }


    //重置一个密码
    @Override
    public Boolean resetPwd(Long id, String newPwd,int type) {
        if(!hasAuth(type))
            return false;
        int b= 0;
        newPwd= MD5Util.Md5(newPwd);
        switch (type) {
            case RoleParam.CLUB_ADMIN:
                Club club = clubMapper.selectById(id);
                club.setPassword(newPwd);
                b = clubMapper.updateById(club);
                break;
            case RoleParam.DEPT_ADMIN:
                Department department = departmentMapper.selectById(id);
                department.setPassword(newPwd);
                b = departmentMapper.updateById(department);
                break;
            case RoleParam.STUDENT:
                Student student = studentMapper.selectById(id);
                student.setStuPassword(newPwd);
                b = studentMapper.updateById(student);
                break;
        }
        if(b==0) return false;
        return true;
    }


    //重置多人密码
    @Override
    public Boolean resetPwds(ArrayList<Long> list, String newPwd,int type){
        //ArrayList<Long> 不合适
        if(!hasAuth(type))
            return false;
        newPwd= MD5Util.Md5(newPwd);
        switch (type) {
            case RoleParam.CLUB_ADMIN:
                for (Long id : list) {
                    Club club =clubMapper.selectById(id);
                    club.setPassword(newPwd);
                    int b = clubMapper.updateById(club);
                    if(b==0) return false;
                }
                return true;
            case RoleParam.DEPT_ADMIN:
                for (Long id : list) {
                    Department department = departmentMapper.selectById(id);
                    department.setPassword(newPwd);
                    int b = departmentMapper.updateById(department);
                    if(b==0) return false;
                }
                return true;
            case RoleParam.STUDENT:
                for (Long id : list) {
                    Student student=studentMapper.selectById(id);
                    System.out.println(student);
                    student.setStuPassword(newPwd);
                    int b = studentMapper.updateById(student);
                    if(b==0) return false;
                }
                return true;
            default:
                return false;
        }
    }


    //全部重置
    @Override
    public Boolean resetAllPwd(String newPwd){
        int role=getAttributes(httpSession).getRole();
        newPwd= MD5Util.Md5(newPwd);
        switch (role){
            case 2:
                return stu(studentMapper,newPwd);
            case 3:
                return dept(departmentMapper,newPwd)&&stu(studentMapper,newPwd);
            case 4:
                return club(clubMapper,newPwd)&&dept(departmentMapper,newPwd)&&stu(studentMapper,newPwd);
        }
        return true;
    }



    @NotNull
    static Boolean stu(StudentMapper studentMapper,String newPwd){
        List<Student> students3 = studentMapper.selectList(null);
        for (Student stu: students3) {
            stu.setStuPassword(newPwd);
            int i = studentMapper.updateById(stu);
            if (i==0) return false;
        }
        return true;
    }
    @NotNull
    static Boolean club(ClubMapper clubMapper,String newPwd){
        List<Club> clubs = clubMapper.selectList(null);
        for (Club club: clubs) {
            club.setPassword(newPwd);
            int i = clubMapper.updateById(club);
            if (i==0) return false;
        }
        return true;
    }

    @NotNull
    static Boolean dept(DepartmentMapper departmentMapper,String newPwd){
        List<Department> departments3 = departmentMapper.selectList(null);
        for (Department dept:departments3) {
            dept.setPassword(newPwd);
            int i = departmentMapper.updateById(dept);
            if (i==0) return false;
        }
        return true;
    }
}
