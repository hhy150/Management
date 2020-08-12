package com.example.management.service;

import com.example.management.entity.Student;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


public interface StuService {

     int addStu(Student student, HttpServletRequest request);
     boolean deleteStu(Student student);
     boolean updateStu(Student student);
     Student getStuByName(String name, Long deptId);
     List<Student> getList(Long deptId);

     Integer getStuCount();
     Boolean export(String fileName) throws IOException;
     Boolean readExcel(MultipartFile file,String password) throws IOException;

     Student getStuByStuId(String stuId);

}
