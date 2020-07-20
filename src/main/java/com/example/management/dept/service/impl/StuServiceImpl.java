package com.example.management.dept.service.impl;


import com.example.management.dept.dao.DeptMapper;
import com.example.management.dept.dao.StuMapper;
import com.example.management.dept.pojo.Department;
import com.example.management.dept.pojo.Student;
import com.example.management.dept.service.StuService;
import com.example.management.mLogin.util.ConstantUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    StuMapper stuMapper;
    @Autowired
    DeptMapper deptMapper;

    @Override
    public PageInfo getStuList(int pageNum,int pageSize, HttpServletRequest request) {
        String name=String.valueOf(request.getSession().getAttribute(ConstantUtils.USER_NAME));
        Department department=deptMapper.getDeptByName(name);
        PageHelper.startPage(pageNum, pageSize);
        List<Student> list=stuMapper.getStuList(department.getId());
        return new PageInfo<>(list);
    }

    @Override
    public boolean addStu(Student student,HttpServletRequest request) {
        String name= String.valueOf(request.getSession().getAttribute(ConstantUtils.USER_NAME));
        Student stu=stuMapper.getStuByName(student.getStuName());
        if(stu==null) {
            Department department=deptMapper.getDeptByName(name);
            stu.setDeptId(department.getId());
            stuMapper.addStu(student);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStu(Student student){
        Student stu=stuMapper.getStuByName(student.getStuName());
        if(stu!=null){
            stuMapper.updateStu(student);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteStu(Student student) {
        if(student.getStuId()!=null) {
            stuMapper.deleteStu(student.getStuId());
            return true;
        }else{
            return false;
        }
    }
}
