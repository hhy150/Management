package com.example.management.service.impl;

import com.example.management.entity.Club;
import com.example.management.entity.Department;
import com.example.management.entity.RoleParam;
import com.example.management.entity.Student;
import com.example.management.mapper.ClubMapper;
import com.example.management.mapper.DepartmentMapper;
import com.example.management.mapper.StudentMapper;
import com.example.management.service.ResetPwdService;
import com.example.management.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class ResetPwdServiceImpl implements ResetPwdService  {

    private  StudentMapper studentMapper;
    private  ClubMapper clubMapper;
    private  DepartmentMapper departmentMapper;

    @Autowired
    public ResetPwdServiceImpl(ClubMapper clubMapper, DepartmentMapper departmentMapper, StudentMapper studentMapper){
        this.clubMapper=clubMapper;
        this.departmentMapper=departmentMapper;
        this.studentMapper=studentMapper;

    }

    /**
     * 重置一个密码
     */

    public Boolean resetPwd(Long id, String newPwd,int type) {
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

     /**
     * 重置多人密码
     *
     */
    public Boolean resetPwds(ArrayList<Long> list, String newPwd, Integer type){
        //ArrayList<Long> 不合适
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

    /**
     * 全部重置
     */
    public Boolean resetAllPwd(String newPwd){
        newPwd= MD5Util.Md5(newPwd);
        List<Club> clubs = clubMapper.selectList(null);
        for (Club club: clubs) {
            club.setPassword(newPwd);
            int i = clubMapper.updateById(club);
            if (i==0) return false;
        }
        List<Department> departments = departmentMapper.selectList(null);
        for (Department dept:departments) {
            dept.setPassword(newPwd);
            int i = departmentMapper.updateById(dept);
            if (i==0) return false;
        }
        List<Student> students = studentMapper.selectList(null);
        for (Student stu: students) {
            stu.setStuPassword(newPwd);
            int i = studentMapper.updateById(stu);
            if (i==0) return false;
        }
        return true;
    }
}
