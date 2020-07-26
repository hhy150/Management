package com.example.management.dept.controller;


import com.example.management.dept.pojo.Student;
import com.example.management.dept.service.StuService;
import com.example.management.dept.util.ToolUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/dept")
public class StuController {

        @Autowired
        StuService stuService;


        @RequestMapping("/list")
        public Object getStuList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "5")int pageSize,
                                 HttpServletRequest request) {
            PageInfo pageInfo=new PageInfo();
            try {
                 pageInfo = stuService.getStuList(pageNum, pageSize, request);
            }catch (Exception e){
                System.out.println("空指针");
            }
            if (pageInfo.getList() == null) {
                return ToolUtil.result(null, ToolUtil.ERR);
            }
            return pageInfo;
        }

        @RequestMapping("/add")
        public Object addStu(@RequestBody Student student, HttpServletRequest request){

            if(stuService.addStu(student, request)==1) {
                return ToolUtil.result(null, ToolUtil.SUC);
            }else if(stuService.addStu(student, request)==2) {
                return ToolUtil.result("用户存在", ToolUtil.ERR);
            }else {
                return ToolUtil.result(null, ToolUtil.ERR);
            }
        }
        @RequestMapping("/update")
        public Object update(@RequestBody Student student){
            if(stuService.updateStu(student)){
                return ToolUtil.result(null, ToolUtil.SUC);
            }
            return ToolUtil.result(null, ToolUtil.ERR);
        }
        @RequestMapping("/delete")
        public Object delete(@RequestBody Student student){
            if(stuService.deleteStu(student)){
                return ToolUtil.result(null, ToolUtil.SUC);
            }
            return ToolUtil.result(null, ToolUtil.ERR);
        }


}
