package com.example.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.management.entity.Club;
import com.example.management.entity.Department;
import com.example.management.entity.ResultBody;
import com.example.management.entity.Student;
import com.example.management.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/club")
public class ClubController {

    private ClubService clubService;
    @Autowired
    public ClubController(ClubService clubService){
        this.clubService=clubService;
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
     *更新社团
     */
    @PutMapping("update")
    public ResultBody update(@Valid Club community){
        if(clubService.updateById(community))
            return ResultBody.error("更新失败");
        return ResultBody.success();
    }

    /**
     *根据名称查找社团
     */
    @GetMapping("{name}")
    public ResultBody getByName(@PathVariable String name){
        Club club = clubService.getByName(name);
        if(club==null)
            return ResultBody.error("查找失败");
        return ResultBody.success(club);
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
    /**
     *获取某社团下部门列表
     */
    @GetMapping("{clubName}/list")
    public ResultBody getAllDeptByClub(@PathVariable String clubName,
                                       @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                       @RequestParam(defaultValue = "5", value = "pageSize") Integer pageSize ){
        IPage<Department> dept = clubService.getBatchDeptByClub(clubName, pageNum, pageSize);
        //如果为空，就是说明club不存在
        if(dept==null)
            return ResultBody.error("获取列表失败，社团信息错误");
        return ResultBody.success(dept);
    }

    /**
     *获取社团下部门
     */
   @GetMapping("{clubName}/{deptName}")
    public ResultBody getOneDeptByClub(@PathVariable("clubName") String clubName,
                                       @PathVariable("deptName")String deptName){
       Department dept = clubService.getDeptByName(clubName, deptName);
       if(dept==null)
           return ResultBody.error("查找失败");
       return ResultBody.success(dept);
    }

    /**
     *获取社团下部门成员列表
     */
    @GetMapping("{clubName}/{deptName}/list")
    public ResultBody getAllStuByDept(@PathVariable("clubName") String clubName,
                                         @PathVariable("deptName")String deptName,
                                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                         @RequestParam(defaultValue = "5", value = "pageSize") Integer pageSize){
        IPage<Student> stu = clubService.getBatchStuByDept(clubName, deptName, pageNum, pageSize);
        if(stu==null)
            return ResultBody.error("获取列表失败,社团或部门信息错误");
        return ResultBody.success(stu);
    }

    /**
     *获取社团-部门的某成员
     */
    @GetMapping("{clubName}/{deptName}/{stuId}")
    public ResultBody getOneStuByDept(@PathVariable("clubName") String clubName,
                                   @PathVariable("deptName")String deptName,
                                   @PathVariable("stuId")String stuId){
        Student student = clubService.selectByStuId(clubName, deptName, stuId);
        if(student==null)
            return ResultBody.error("查找失败");
        return ResultBody.success(student);
    }
}
