package com.example.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.management.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Student> {
    @Select("SELECT * FROM mem WHERE dept_id=#{deptId} AND is_deleted = 0")
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

    @Select("SELECT * FROM mem WHERE name=#{name} AND dept_id=#{deptId} AND is_deleted = 0")
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
    Student getStuByName(@Param("name") String name, @Param("deptId") Long deptId);

    @Select("SELECT * FROM mem WHERE stuid=#{stuId} AND dept_id=#{deptId} AND is_deleted = 0")   //0没删1删，换了一下
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
    Student getStuByStuId(@Param("stuId") String stuId, @Param("deptId") Long deptId);

    @Insert("INSERT INTO mem (name,stuid,password,dept_id,college,major,gender,phone,email,qq) VALUES(#{stuName}," +
            "#{stuId},#{stuPassword},#{deptId},#{stuCollege},#{stuMajor},#{stuGender},#{stuPhone},#{stuMail},#{stuQq})")
    int addStu(Student student);

    @Update("UPDATE mem SET name=#{stuName},college=#{stuCollege},major=#{stuMajor}," +
            "gender=#{stuGender},phone=#{stuPhone},email=#{stuMail},qq=#{stuQq} WHERE stuid=#{stuId}")
    void updateStu(Student student);

    @Update("UPDATE mem SET is_deleted=0 WHERE stuid=#{stuId}")
    void deleteStu(String stuId);

}
