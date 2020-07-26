package com.example.management.dept.service;
import com.example.management.dept.pojo.Student;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface StuService {
    public PageInfo getStuList(int pageNum,int pageSize, HttpServletRequest request);
    public int addStu(Student student,HttpServletRequest request);
    public boolean deleteStu(Student student);
    public boolean updateStu(Student student);
    public Student getStuByName(String name,Long deptId);
    public Student getStuByStuId(String stuId,Long deptId);
    public List<Student> getList(Long deptId);


}
