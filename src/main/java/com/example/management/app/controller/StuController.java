package com.example.management.app.controller;

import com.example.management.entity.ResultBody;
import com.example.management.entity.Student;
import com.example.management.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/stu")
public class StuController {

    @Autowired
    StuService stuService;

    @RequestMapping("/add")
    public ResultBody addStu( @RequestBody Student student, HttpServletRequest request){

            if(stuService.addStu(student, request)==1) {
                return ResultBody.success();
            }else if(stuService.addStu(student, request)==2) {
                return ResultBody.error("用户存在");
            }else {
                return ResultBody.error("添加失败");
        }
    }
    @RequestMapping("/update")
    public ResultBody update(@RequestBody Student student){
        if(stuService.updateStu(student))
            return ResultBody.success();
        return ResultBody.error("更新失败");
    }

    @RequestMapping("/delete")
    public ResultBody delete(@RequestBody Student student){
        if(stuService.deleteStu(student))
            return ResultBody.success();
        return ResultBody.error("删除失败");
    }



    @GetMapping("count")
    public ResultBody getStuCount(){
        return ResultBody.success(stuService.getStuCount());
    }

    @RequestMapping("export")
    public ResultBody export(String fileName) throws IOException {
        if(stuService.export(fileName))
            return ResultBody.success();
        return ResultBody.error("导出失败");
    }

    @PostMapping("import")
    public ResultBody readExcel(MultipartFile file, String password) throws IOException {

        if(stuService.readExcel(file,password))
            return ResultBody.success();
        return ResultBody.error("导入失败");
    }

}
