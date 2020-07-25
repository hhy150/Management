package com.example.management.controller;

import com.example.management.service.ResetPwdService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@RestController
public class ResetPwdController {


    private final ResetPwdService resetPwdService;
    public ResetPwdController(ResetPwdService resetPwdService) {
        this.resetPwdService = resetPwdService;
    }

    /**
     *重置一个密码
     */
    @PutMapping("/resetPwd/{id}")
    public Boolean resetPwd(@PathVariable Long id, String newPwd, Integer type){
      return resetPwdService.resetPwd(id,newPwd,type);
    }

    /**
     *重置多个密码
     */
    @PutMapping("/resetPwds")
    public Boolean resetPwds(ArrayList<Long> list, String newPwd, Integer type) {
        return resetPwdService.resetPwds(list,newPwd,type);
    }

    /**
     *重置所有密码
     */
    @PutMapping("/resetAllPwd")
    public Boolean resetAllPwd(String newPwd){
        return resetPwdService.resetAllPwd(newPwd);
    }
}

