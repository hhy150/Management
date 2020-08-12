package com.example.management.app.controller;

import com.example.management.entity.ResultBody;
import com.example.management.service.CommonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController {

    private final CommonService commonService;
    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    /**
     *修改密码
     */
    @PostMapping("changePwd")
    public ResultBody changePwd(String newPwd){
        if(commonService.changePwd(newPwd))
            return ResultBody.success();
        return ResultBody.error("修改失败");
    }

}
