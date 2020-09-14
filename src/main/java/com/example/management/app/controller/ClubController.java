package com.example.management.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.management.entity.Club;
import com.example.management.entity.Department;
import com.example.management.entity.ResultBody;
import com.example.management.entity.Student;
import com.example.management.mapper.LoginMapper;
import com.example.management.service.ClubService;
import com.example.management.service.LoginService;
import com.example.management.service.StuService;
import com.example.management.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/club")
public class ClubController {

    private StuService stuService;
    private ClubService clubService;
    @Autowired
    public ClubController(ClubService clubService, StuService stuService){
        this.clubService=clubService;

        this.stuService = stuService;
    }

    /**
     *增加部门
     */
    @PostMapping("add")
    public ResultBody add(@Valid Club club) {
        if(!clubService.insert(club))
            return ResultBody.error("添加失败");
        return ResultBody.success();
    }

    /**
     *删除部门
     */
    @PutMapping("delete/{id}")
    public ResultBody delete(@PathVariable Long id){
       if(clubService.deleteById(id))
              return ResultBody.success();
       return ResultBody.error("删除失败,该社团不存在或者该社团下还有部门");
    }

    /**
     *更新社团,不应该更新密码吧
     */
    @PutMapping("update")
    public ResultBody update(@Valid Club community){
        Club club = clubService.getById(community.getId());
        community.setPassword(MD5Util.Md5(club.getPassword()));
        if(!clubService.updateById(community))
            return ResultBody.error("更新失败");
        return ResultBody.success();
    }

    /**
     *根据名称查找社团
     */
    @GetMapping("{name}")
    public ResultBody getByName(@PathVariable String name,
                                @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                @RequestParam(defaultValue = "5", value = "pageSize") Integer pageSize){
        IPage<Club> clubs = clubService.getBatchByName(name, pageNum, pageSize);
        if(clubs==null)
            return ResultBody.error("查找失败");
        return ResultBody.success(clubs);
    }

    /**
     *获取社团列表
     */
    @GetMapping("list")
    public ResultBody getAll(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "5", value = "pageSize") Integer pageSize){
        IPage<Club> clubs = clubService.getAllClub(pageNum, pageSize);
        return  ResultBody.success(clubs);
    }



     //通过stuId查找学生
    @GetMapping("stu")
    public ResultBody getStuById(String stuId){
        Student stu = stuService.getStuByStuId(stuId);
        if(stu!=null)
            return ResultBody.success(stu);
        return ResultBody.error("查询失败");
    }

    //获取club下部门数
    @RequestMapping("deptCount")
    public ResultBody getDeptCount(){
        return ResultBody.success(clubService.getDeptCount());
    }
}
