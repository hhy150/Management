package com.example.management.mapper;

import com.example.management.entity.MemAffair;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemAffairMapper {

    @Update(
        "update  mem_affair  SET is_deleted= 1  where id = #{id}"
    )
    boolean deleteByPrimaryKey(Long id);

    @Update(
            "update mem_affair set is_deleted = 0 where id = #{id}"
    )
    boolean UndeleteByPrimaryKey(Long id);

    /**
    修改id类型为long
     */
    @Insert(
        "insert into mem_affair (id, mem_id, affair_id, is_deleted)" +
                "values (#{id}, #{memId}, #{affairId}, #{isDeleted})"
    )
    boolean insert(MemAffair record);

    @Select(
        "select id, mem_id, affair_id from mem_affair where id = #{id}"
    )
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="mem_id", property="memId", jdbcType=JdbcType.BIGINT),
        @Result(column="affair_id", property="affairId", jdbcType=JdbcType.BIGINT),
    })
    MemAffair selectByPrimaryKey(Long id);

    /**
    删去is_deleted
     */
    @Select({
        "select, id, mem_id, affair_id, from mem_affair"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="mem_id", property="memId", jdbcType=JdbcType.BIGINT),
        @Result(column="affair_id", property="affairId", jdbcType=JdbcType.BIGINT)
    })
    List<MemAffair> selectAll();


    @Update(
        "update mem_affair, set mem_id = #{memId,jdbcType=BIGINT}, affair_id = #{affairId}, where id = #{id}"
    )
    /*
    对修改操作删去is_deleted
     */
    boolean updateByPrimaryKey(MemAffair record);
}