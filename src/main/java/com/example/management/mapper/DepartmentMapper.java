package com.example.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.management.entity.Department;
import com.example.management.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper extends BaseMapper<Department> {
    @Select("SELECT * FROM department WHERE username=#{name} AND club_id=#{clubId} AND is_deleted = 0")
    @Results({
            @Result(column="club_id", property="comId")
    })
    Department getDeptByName(@Param("name") String name, @Param("clubId") Long clubId);


    @Select(" SELECT COUNT(`id`) num FROM mem WHERE `dept_id`=#{id} AND is_deleted = 0")
    int getStuCountByDept(Long id);


    List<Student> selectAllStuByDept(Long id);
}
