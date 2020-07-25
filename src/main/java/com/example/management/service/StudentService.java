package com.example.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.management.entity.Student;

import java.util.List;


public interface StudentService extends IService<Student> {

    List<Student> getAllStuByDept(Long id);

    Student getStuByStuId(String stuId);

    Boolean insert(Student student);
}
