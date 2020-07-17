package com.example.management.dao;


import com.example.management.entity.Affair;
import com.example.management.entity.SendAffairParam;
import com.example.management.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AffairDao {
    List<Student> getMemberByParam(SendAffairParam sendAffairParam);

    List<Student> getAllMember();

    List<Student> getMemberByName(String memberName);

    List<Student> getMemberByDeptId(long dept_id);

    Affair getAffairByTitle(String title);

    Affair getAffairById(long id);

    boolean delAffairByTitle(String title);

    Affair addAffair(Affair affair);

    void modifyAffair(Affair affair);


}
