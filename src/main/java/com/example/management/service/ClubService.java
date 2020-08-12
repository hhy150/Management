package com.example.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.management.entity.Club;


public interface ClubService extends IService<Club> {


    IPage<Club> getBatchByName(String name,int pageNum,int pageSize);

    //Department getDeptByName(String clubName, String deptName);

    Boolean deleteById(Long id);

    Boolean insert(Club club);

    IPage<Club> getAllClub(int pageNum, int pageSize);

/*
    IPage<Department> getBatchDeptByClub(String clubName,int pageNum,int pageSize);

    IPage<Student> getBatchStuByDept(String clubName,String deptName,int pageNum,int pageSize);

    Student selectByStuId(String clubName,String deptName,String stuId);
*/

    Integer getDeptCount();


}
