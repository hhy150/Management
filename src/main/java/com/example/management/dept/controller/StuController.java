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

        private ToolUtil toolUtil;

        @RequestMapping("/list")
        public Object getStuList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "5")int pageSize,
                                 HttpServletRequest request) {
            PageInfo pageInfo = stuService.getStuList(pageNum, pageSize, request);
            if (pageInfo == null) {
                return toolUtil.result(null, ToolUtil.ERR);
            }
            return toolUtil.result(pageInfo, ToolUtil.SUC);
        }

        @RequestMapping("/add")
        public Object addStu(@RequestBody Student student, HttpServletRequest request){

            if(stuService.addStu(student, request)) {
                   return toolUtil.result(null, ToolUtil.SUC);
               }
            return toolUtil.result(null,ToolUtil.ERR);
        }
        @RequestMapping("/update")
        public Object update(@RequestBody Student student){
            if(stuService.updateStu(student)){
                return toolUtil.result(null, ToolUtil.SUC);
            }
            return toolUtil.result(null, ToolUtil.ERR);
        }
        @RequestMapping("/delete")
        public Object delete(@RequestBody Student student){
            if(stuService.deleteStu(student)){
                return toolUtil.result(null, ToolUtil.SUC);
            }
            return toolUtil.result(null, ToolUtil.ERR);
        }

}
