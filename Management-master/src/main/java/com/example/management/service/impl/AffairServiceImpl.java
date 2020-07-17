package com.example.management.service.impl;

import com.example.management.AffairException;
import com.example.management.dao.AffairDao;
import com.example.management.entity.Affair;
import com.example.management.entity.SendAffairParam;
import com.example.management.entity.Student;

import com.example.management.service.AffairService;
import com.example.management.util.AffairUtil;
import com.example.management.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class AffairServiceImpl implements AffairService {

    @Autowired
    private AffairDao affairDao;

    private static final String SUCCESS = "派发成功";


    @Override
    public String sendAffair(SendAffairParam sendAffairParam) {
        String result = null;
        List<Student> selectedStudents = null;

        try {
            judgeAffair(sendAffairParam.getContent());
        }catch (AffairException e){
            e.printStackTrace();
            return null;
        }

        switch (sendAffairParam.getMemCondition()){
            case AffairUtil.SEND_ALL:
                selectedStudents = affairDao.getMemberByDeptId(sendAffairParam.getSenderId());
                break;
            case AffairUtil.SEND_CONDITIONAL:
                selectedStudents = affairDao.getMemberByParam(sendAffairParam);
                break;
            case AffairUtil.SEND_PERSONAL:
                selectedStudents = affairDao.getMemberByName(sendAffairParam.getReceiver());
                break;
            default:
                return "send affair failed: you didn't chose any type";
        }
        if (selectedStudents == null){
            return "no members were selected";
        }
        result = sendAffairCore(sendAffairParam, selectedStudents);
        return result;
    }

    @Override
    public boolean delAffairByTitle(String affairTitle) {
        boolean result = affairDao.delAffairByTitle(affairTitle);
        return result;
    }

    @Override
    public Affair addAffair(Affair affair) {
        Affair result = affairDao.addAffair(affair);
        return result;
    }

    @Override
    public boolean modifyAffair(Affair affair) {
        affairDao.modifyAffair(affair);
        return true;
    }

    @Override
    public Affair getAffairByTitle(String title) {
        Affair affair = affairDao.getAffairByTitle(title);
        return affair;
    }

    @Override
    public Affair getAffairById(long id) {
        Affair affair = affairDao.getAffairById(id);
        return affair;
    }

    private void judgeAffair(Affair affair)throws AffairException {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(affair.getEndTime())){
            throw new AffairException("这个任务已经过期了！");
        }
        if (affair.getIsDeleted() == 1){
            throw new AffairException("这个任务已被删除！");
        }
        if (affair.getIsOK() == 1){
            throw new AffairException("这个任务已经完成");
        }
    }

    private String sendAffairCore(SendAffairParam sendAffairParam, List<Student> members) {
        Affair affair = sendAffairParam.getContent();
        if (members.isEmpty()){
            return "派发任务失败";
        }
        MailUtil mailUtil = new MailUtil();
        String result = mailUtil.sendMail(sendAffairParam.getTemplateType(), sendAffairParam.getSenderName(), affair.getTitle(), affair.getEndTime(), members);
        if (!result.equals(SUCCESS)){
            return "派发任务失败：util未返回预期值";
        }else {
            return "派发任务成功";
        }

    }
}
