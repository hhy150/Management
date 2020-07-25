package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.management.entity.Student;
import com.example.management.mapper.StudentMapper;
import com.example.management.service.StudentService;
import com.example.management.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    private StudentMapper studentMapper;
    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper){
        this.studentMapper=studentMapper;
    }

    @Override
    public List<Student> getAllStuByDept(Long id) {
        QueryWrapper<Student> dept_id = new QueryWrapper<Student>()
                .eq("dept_id", id);
        List<Student> list = studentMapper.selectList(dept_id);
        if(list.size()==0)  return null;
        return list;
    }

    @Override
    public Student getStuByStuId(String stuId) {
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>()
                .eq("stuid", stuId);
        return  studentMapper.selectOne(wrapper);
    }

    @Override
    public Boolean insert(Student student) {
        student.setStuPassword(MD5Util.Md5(student.getStuPassword()));
        if(studentMapper.insert(student)==0) return false;
        return true;
    }


}
