package com.example.management.mLogin.dao;

import com.example.management.mLogin.pojo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoginMapper {

    @Select("SELECT * FROM mem WHERE stuid=#{stuId} AND dept_id=deptId AND is_deleted = 1")   //1没删0删
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
    Student getStu(@Param("stuId") String stuId,@Param("deptId") Long deptId);

    @Select("SELECT * FROM mem WHERE dept_id=#{deptId} AND is_deleted = 1")   //1没删0删
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
    List<Student> getStuList(long deptId);

    @Select("SELECT * FROM department WHERE username=#{name} AND club_id=#{clubId} AND is_deleted = 1")
    Department getDeptByName(@Param("name") String name,@Param("clubId") Long clubId);


    @Select("SELECT * FROM department WHERE club_id=#{clubId} AND is_deleted = 1")
    List<Department> getDeptList(long clubId);

    @Select("SELECT * FROM club WHERE username=#{name} AND is_deleted = 1")
    Club getClubByName(String name);

    @Select("SELECT * FROM club WHERE is_deleted = 1")
    List<Club> getClubList();

    @Select("SELECT * FROM super_admin WHERE username=#{name}")
    Admin getSuperAdmin(String name);


}
