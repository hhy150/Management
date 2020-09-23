package com.example.management.mapper;

import com.example.management.entity.Affair;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AffairMapper {

    //待修改
    @Update("update affair set is_deleted = 1 WHERE id=#{id}")
    boolean deleteByPrimaryKey(Long id);

    @Update("update affair, set is_deleted = 0, where id = #{id}")
    boolean UndeleteByPrimaryKey(Long id);

    @Insert(
        "INSERT INTO affair （id，end_time，start_time, title,content, is_OK, type,is_deleted，club_id)VALUES (#{id}," +
                "#{endTime},#{startTime},#{title},#{content},#{isOk},#{type},#{isDeleted},#{club_id}) "
    )
    boolean insert(Affair record);

    @Select("SELECT * FROM affair WHERE id=#{id}")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_OK", property="isOK", jdbcType=JdbcType.BIT),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="is_deleted", property="isDeleted", jdbcType=JdbcType.BIT),
        @Result(column="club_id", property="clubId", jdbcType=JdbcType.INTEGER)
    })
    Affair selectByPrimaryKey(Long id);
    /**
     修改id类型为long
     */
    @Select(
        "select, id, end_time, start_time, title, content, is_OK, type, is_deleted，club_id, from affair"
    )
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_OK", property="isOK", jdbcType=JdbcType.BIT),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="is_deleted", property="isDeleted", jdbcType=JdbcType.BIT),
        @Result(column="club_id", property="clubId", jdbcType=JdbcType.INTEGER)
    })
    List<Affair> selectAll();
/*
* 删去对club_id和is_deleted的修改
* */
    @Update(
        "UPDATE affair SET end_time = #{endTime,jdbcType=TIMESTAMP}, start_time = #{startTime,jdbcType=TIMESTAMP}, " + "title = #{title,jdbcType=VARCHAR}, " +
                "content = #{content,jdbcType=VARCHAR}, is_OK = #{isOk,jdbcType=BIT}, type = #{type,jdbcType=INTEGER}, where id = #{id,jdbcType=BIGINT}"
    )
    boolean updateByPrimaryKey(Affair affair);
}