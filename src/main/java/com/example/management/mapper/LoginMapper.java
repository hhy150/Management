package com.example.management.mapper;

import com.example.management.entity.Admin;
import com.example.management.entity.Club;
import com.example.management.entity.Department;
import com.example.management.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoginMapper {

    @Select("SELECT * FROM mem WHERE stuid=#{stuId} AND is_deleted = 0 LIMIT 1")
    @Results({
            @Result(column="name", property="stuName"),
            @Result(column="stuid", property="stuId"),
            @Result(column="password", property="stuPassword"),
            @Result(column="dept_id", property="deptId"),
            @Result(column="college", property="stuCollege"),
            @Result(column="major", property="stuMajor"),
            @Result(column="gender", property="stuGender"),
            @Result(column="phone", property="stuPhone"),
            @Result(column="email", property="stuMail"),
            @Result(column="qq", property="stuQq"),
    })
    Student getStu(@Param("stuId") String stuId);




    @Select("SELECT * FROM department WHERE username=#{name} AND is_deleted = 0")
    Department getDeptByName(@Param("name") String name);


    @Select("SELECT * FROM department WHERE club_id=#{clubId} AND is_deleted = 0")
    List<Department> getDeptList(long clubId);

    @Select("SELECT * FROM club WHERE username=#{name} AND is_deleted = 0")
    Club getClubByName(String name);

    @Select("SELECT * FROM club WHERE is_deleted = 0")
    List<Club> getClubList();

    @Select("SELECT * FROM super_admin WHERE username=#{name}")
    Admin getSuperAdmin(String name);


}
