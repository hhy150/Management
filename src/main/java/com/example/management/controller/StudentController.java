package com.example.management.controller;

import com.example.management.entity.Student;
import com.example.management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;
    @Autowired
    public StudentController (StudentService studentService){
        this.studentService=studentService;
    }

    @GetMapping("{stuId}")
    public Student getStudentByStuId(@PathVariable("stuId") String stuId){
        return studentService.getStuByStuId(stuId);
    }

    @PostMapping("add")
    public Boolean add(@Valid  Student student) {
        return  studentService.insert(student);
    }

    @PutMapping("delete/{id}")
    public Boolean delete(@PathVariable Integer id){
        return studentService.removeById(id);
    }

    @PutMapping("deleteBatch")
    public Boolean deleteBatch(List<Long> list){
        return studentService.removeByIds(list);
    }

    @PostMapping("update")
    public Boolean update(@Valid  Student student){
        return studentService.updateById(student);
    }

}
