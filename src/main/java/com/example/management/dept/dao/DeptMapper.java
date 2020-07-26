package com.example.management.dept.dao;
import com.example.management.dept.pojo.Department;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DeptMapper {

    @Select("SELECT * FROM department WHERE username=#{name} AND club_id=#{clubId} AND is_deleted = 1")
    @Results({
        @Result(column="club_id", property="comId")
    })
    Department getDeptByName(@Param("name") String name, @Param("clubId") Long clubId);


}
