package com.example.management.controller;

import com.example.management.entity.Affair;
import com.example.management.entity.SendAffairParam;
import com.example.management.service.AffairService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class AffairController {

    @Autowired
    private AffairService affairService;

    private static final Logger LOG = LoggerFactory.getLogger(AffairController.class);

    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/sendAffair")
    public Affair sendAffair(@RequestParam("title") String affairName, @RequestParam String templateType, String memCondition,@RequestParam String name) {
        SendAffairParam sendAffairParam = new SendAffairParam();
        Affair affair = affairService.getAffairByTitle(affairName);
        if (affair == null){
            LOG.error("can't find the affair,try addAffair first?");
            return null;
        }
        sendAffairParam.setSenderName(name);
        sendAffairParam.setMemCondition(memCondition);
        sendAffairParam.setTemplateType(templateType);
        sendAffairParam.setContent(affair);
        affairService.sendAffair(sendAffairParam);
        return affair;
    }

    @RequestMapping("/addAffair")
    public Affair addAffair(@RequestParam String title, String content,@RequestParam String startTime,@RequestParam String endTime){
        Affair affair = new Affair();
        affair.setTitle(title);
        LocalDateTime start = LocalDateTime.parse(startTime, df);
        LocalDateTime end = LocalDateTime.parse(endTime, df);
        affair.setStartTime(start);
        affair.setEndTime(end);
        if (StringUtils.hasText(content)){
            affair.setContent(content);
        }
        return affair;
    }

    @RequestMapping("/delAffair")
    public boolean delAffair(@RequestParam String title){
        boolean result = affairService.delAffairByTitle(title);
        return result;
    }

    @RequestMapping("/modifyAffair")
    public String modifyAffair(@RequestParam String title, String newTitle
            , String content,String startTime,String endTime){
        Affair affair = affairService.getAffairByTitle(title);
        if (affair == null){
            LOG.error("can't find the affair");
            return null;
        }
        if (StringUtils.hasText(newTitle)) {
            affair.setTitle(newTitle);
        }


        if (StringUtils.hasText(startTime)){
            LocalDateTime start = LocalDateTime.parse(startTime, df);
            affair.setStartTime(start);
        }
        if (StringUtils.hasText(endTime)){
            LocalDateTime end = LocalDateTime.parse(endTime, df);
            affair.setEndTime(end);
        }
        if (StringUtils.hasText(content)){
            affair.setContent(content);
        }

        boolean result = affairService.modifyAffair(affair);
        if (result){
            return "modifySuccess";
        }else {
            return "modifyFailed";
        }
    }

}
