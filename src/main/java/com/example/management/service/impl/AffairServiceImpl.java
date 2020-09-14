package com.example.management.service.impl;

import com.example.management.entity.Affair;
import com.example.management.entity.SendAffairParam;
import com.example.management.entity.Student;
import com.example.management.exception.AffairException;
import com.example.management.mapper.AffairMapper;
import com.example.management.service.AffairService;
import com.example.management.service.StuService;
import com.example.management.util.AffairUtil;
import com.example.management.util.MailUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@CacheConfig(cacheNames = "affair")
@Service
public class AffairServiceImpl implements AffairService {

    @Autowired
    private AffairMapper affairMapper;

    @Autowired
    private StuService stuService;

    private static final Logger LOG = LoggerFactory.getLogger(AffairServiceImpl.class);

    private static final String SUCCESS = "派发成功";

    @CachePut(value = "affair",key = "#id")
    @Override
    public boolean deleteAffairById(int id) {
        return  affairMapper.deleteByPrimaryKey(id);
    }

    @CachePut(value = "affair",key = "#id")
    @Override
    public boolean UnDeleteByPrimaryKey(int id) {
        return affairMapper.UndeleteByPrimaryKey(id);
    }

    @CachePut(value = "affair",key = "#affair.id")
    @Override
    public boolean addAffair(Affair affair) {
        return affairMapper.insert(affair);
    }

    @CachePut(value = "affair",key = "affair.id")
    @Override
    public boolean updateAffair(Affair affair) {
        return affairMapper.updateByPrimaryKey(affair);
    }

    @Cacheable(value = "affair",key = "#id")
    @Override
    public Affair getAffairById(int id) {
        return affairMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加分页
     */
    @Cacheable(key = "#root.methodName")
    @Override
    public PageInfo<Affair> getAll(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List <Affair> list;
        list=affairMapper.selectAll();
        PageInfo<Affair> pageInfo = new PageInfo<>(list);
        return  pageInfo;
    }


    @Override
    public String sendAffair(SendAffairParam sendAffairParam) {
        String result = null;
        List<Student> selectedStudents = null;

        try {
            judgeAffair(sendAffairParam.getContent());
        }catch (AffairException e){
            e.printStackTrace();
            LOG.error(e.getMessage());
            return e.getMessage();
        }

        switch (sendAffairParam.getMemCondition()){
            case AffairUtil.SEND_DEPT_MEMBERS:
                selectedStudents = stuService.getList(sendAffairParam.getSenderId());
                break;
            case AffairUtil.SEND_PERSONAL:
                selectedStudents.add(stuService.getStuByName(sendAffairParam.getReceiver(),sendAffairParam.getSenderId()));
                break;
            default:
                return "send affair failed: you didn't chose any type";
        }
        if (selectedStudents == null){
            return "send affair failed: no members were selected";
        }
        result = sendAffairCore(sendAffairParam, selectedStudents);
        return result;
    }

    private void judgeAffair(Affair affair)throws AffairException {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(affair.getEndTime())){
            throw new AffairException("send affair failed: this task already ended");
        }
        if (affair.getIsDeleted() == 1){
            throw new AffairException("send affair failed: this task has been deleted");
        }
        if (affair.getIsOK() == 1){
            throw new AffairException("send affair failed: this task has been finished");
        }
    }

    private String sendAffairCore(SendAffairParam sendAffairParam, List<Student> members) {
        Affair affair = sendAffairParam.getContent();
        if (members.isEmpty()){
            return "send affair failed: target is empty";
        }
        MailUtil mailUtil = new MailUtil();
        String result = mailUtil.sendMail(sendAffairParam.getTemplateType(), sendAffairParam.getSenderName(),affair.getTitle(), affair.getId(),affair.getEndTime(), members);
        if (!result.equals(SUCCESS)){
            return "send affair failed: send mail error";
        }else {
            return "send affair succeed ";
        }

    }

}
