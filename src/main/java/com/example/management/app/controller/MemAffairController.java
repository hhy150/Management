package com.example.management.app.controller;

import com.example.management.entity.MemAffair;
import com.example.management.entity.ResultBody;
import com.example.management.service.MemAffairService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemAffairController {
    @Autowired
    MemAffairService memAffairSevice;

    @RequestMapping("/memaffair/delete")
    public ResultBody deleteAffairById(@RequestParam("id") Long id){
        if(memAffairSevice.deleteMemAffairById(id))
            return ResultBody.success();
        return ResultBody.error("删除失败");
    }

    @RequestMapping("/memaffair/undelete")
    public ResultBody unDeleteAffairById(@RequestParam("id") Long id){
        if(memAffairSevice.UnDeleteByPrimaryKey(id))
            return ResultBody.success();
        return ResultBody.error("撤销失败");
    };

    @RequestMapping("/memaffair/add")
    public ResultBody addAffairById(@RequestBody MemAffair memAffair){
        if(memAffairSevice.addMemAffair(memAffair))
            return ResultBody.success();
        return ResultBody.error("添加失败");
    }

    @RequestMapping("/memaffair/update")
    public ResultBody UpdateAffair(@RequestBody MemAffair memAffair){
        if(memAffairSevice.updateMemAffair(memAffair))
            return ResultBody.success();
        return ResultBody.error("更新成功");
    };
    /*不需要查找，故注释掉
    @RequestMapping("/memaffair/get")
    public ResultBody getAffairById(@RequestParam("id") Integer id){
        MemAffair memAffair = memAffairSevice.getAffairById(id);
        if(memAffair!=null)
            return ResultBody.success(memAffair);
        return  ResultBody.error("查找失败");
    }
*/
    /**
     *添加分页
     */
    @RequestMapping("/memaffair/getall")
    public ResultBody getAll(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "5")int pageSize){
        PageInfo<MemAffair> all = memAffairSevice.getAll(pageNum, pageSize);
        return ResultBody.success(all);
    }
}
