package com.example.management.dept.dao;
import com.example.management.dept.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DeptMapper {

    @Select("SELECT * FROM department WHERE username=#{name} AND is_deleted = 1")
    Department getDeptByName(String name);


}
