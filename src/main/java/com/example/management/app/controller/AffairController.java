package com.example.management.app.controller;

import com.example.management.entity.Affair;
import com.example.management.entity.ResultBody;
import com.example.management.entity.SendAffairParam;
import com.example.management.service.AffairService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/affair")
public class AffairController {
    @Autowired
    AffairService affairService;

    private static final Logger LOG = LoggerFactory.getLogger(AffairController.class);

    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @PutMapping("delete")
    public ResultBody UndeleteAffairById(@RequestParam("id") Integer id){
        if(affairService.deleteAffairById(id))
            return ResultBody.success();
        return ResultBody.error("删除失败");
    }

    @PutMapping("undelete")
    public ResultBody deleteAffairById(@RequestParam("id") Integer id){
       if(affairService.UnDeleteByPrimaryKey(id))
           return ResultBody.success();
       return ResultBody.error("删除失败");
    }

    @PostMapping("add")
    public ResultBody addAffairById( @RequestBody Affair affair ){
        if(affairService.addAffair(affair))
            return ResultBody.success();
        return ResultBody.error("添加失败");
    }

    @PutMapping("update")
   public ResultBody UpdateAffair(@RequestBody Affair affair ){
        if(affairService.updateAffair(affair))
            return ResultBody.success();
        return ResultBody.error("更新失败");
    }

    @GetMapping("get")
    public ResultBody getAffairById(@RequestParam("id") Integer id){
        Affair affair = affairService.getAffairById(id);
        if(affair!=null)
            return ResultBody.success(affair);
        return  ResultBody.error("查找失败");
    }


    /**
     * 添加分页
     */
    @GetMapping("getAll")
    public ResultBody getAll(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                         @RequestParam(value = "pageSize",defaultValue = "5")int pageSize){

        PageInfo<Affair> affairs = affairService.getAll(pageNum, pageSize);
        return ResultBody.success(affairs);
    }


    @RequestMapping("/send")
    public ResultBody sendAffair(@RequestParam("affairId")int affairId, @RequestParam String templateType, String memCondition,@RequestParam String name) {
        SendAffairParam sendAffairParam = new SendAffairParam();
        Affair affair = affairService.getAffairById(affairId);
        if (affair == null){
            LOG.error("can't find the affair,try addAffair first?");
            return ResultBody.error("can't find the affair,try addAffair first?");
        }
        sendAffairParam.setSenderName(name);
        sendAffairParam.setMemCondition(memCondition);
        sendAffairParam.setTemplateType(templateType);
        sendAffairParam.setContent(affair);
        affairService.sendAffair(sendAffairParam);
        return ResultBody.success(affair);
    }

}
