package com.example.management.dept.service;
import com.example.management.dept.pojo.Student;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;


public interface StuService {
    public PageInfo getStuList(int pageNum,int pageSize, HttpServletRequest request);
    public int addStu(Student student,HttpServletRequest request);
    public boolean deleteStu(Student student);
    public boolean updateStu(Student student);


    }
