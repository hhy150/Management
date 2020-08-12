package com.example.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.management.entity.Club;
import com.example.management.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubMapper extends BaseMapper<Club> {

    int getStuCountByClub(Long id);

    //使用MP更方便
    int getDeptCount(Long id);

    List<Student> selectAllStuByClub(Long id);

}
