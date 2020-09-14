package com.example.management.app.controller;

import com.example.management.entity.ResultBody;
import com.example.management.service.ResetPwdService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/resetPwd")
public class ResetPwdController {


    private final ResetPwdService resetPwdService;
    public ResetPwdController(ResetPwdService resetPwdService) {
        this.resetPwdService = resetPwdService;
    }

    /**
     *重置一个密码
     */
    @PutMapping("{id}")
    public ResultBody resetPwd(@PathVariable Long id, String newPwd,int type){
      if(resetPwdService.resetPwd(id,newPwd,type))
          return ResultBody.success();
      return ResultBody.error("重置失败");
    }

     /**
     *重置多个密码
     */
    @PutMapping("batch")
    public ResultBody resetPwds(@RequestBody  ArrayList<Long> list, String newPwd, int type) {
        if( resetPwdService.resetPwds(list,newPwd,type))
            return ResultBody.success();
        return ResultBody.error("重置失败");
    }

    /**
     *重置所有密码
     */
    @PutMapping("all")
    public ResultBody resetAllPwd(String newPwd){
        if(resetPwdService.resetAllPwd(newPwd))
            return ResultBody.success();
        return ResultBody.error("重置失败");
    }
}

