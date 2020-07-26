package com.example.management.dept.dao;
import com.example.management.dept.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StuMapper {

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

    @Select("SELECT * FROM mem WHERE name=#{name} AND dept_id=#{deptId} AND is_deleted = 1")   //1没删0删
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
    Student getStuByName(@Param("name") String name,@Param("deptId") Long deptId);

    @Select("SELECT * FROM mem WHERE stuid=#{stuId} AND dept_id=#{deptId} AND is_deleted = 1")   //1没删0删
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
    Student getStuByStuId(@Param("stuId") String stuId,@Param("deptId") Long deptId);

    @Insert("INSERT INTO mem (name,stuid,password,dept_id,college,major,gender,phone,email,qq) VALUES(#{stuName}," +
            "#{stuId},#{stuPassword},#{deptId},#{stuCollege},#{stuMajor},#{stuGender},#{stuPhone},#{stuMail},#{stuQq})")
    void addStu(Student student);

    @Update("UPDATE mem SET name=#{stuName},college=#{stuCollege},major=#{stuMajor}," +
            "gender=#{stuGender},phone=#{stuPhone},email=#{stuMail},qq=#{stuQq} WHERE stuid=#{stuId}")
    void updateStu(Student student);
    @Update("UPDATE mem SET is_deleted=0 WHERE stuid=#{stuId}")
    void deleteStu(String stuId);
}
