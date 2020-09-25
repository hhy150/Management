package com.example.management.mapper;

import com.example.management.entity.Affair;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AffairMapper {


    @Update("update affair set is_deleted = 1 WHERE id=#{id}")
    boolean deleteByPrimaryKey(Long id);

    @Update("update affair set is_deleted = 0 where id = #{id} AND is_deleted=1")
    boolean UndeleteByPrimaryKey(Long id);

    @Insert(
        "INSERT INTO affair （id，end_time，start_time, title,content, is_OK, type,is_deleted，club_id)VALUES (#{id}," +
                "#{endTime},#{startTime},#{title},#{content},#{isOK },#{type},#{isDeleted},#{clubId}) "
    )
    @Options(useGeneratedKeys = true,keyColumn = "id")
    boolean insert(Affair record);

    @Select("SELECT * FROM affair WHERE id=#{id} AND is_deleted=0")
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

    @Select("select * from  affair  where is_deleted=0 ")
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

    @Update(
        "UPDATE affair SET end_time = #{endTime} ,start_time = #{startTime}, title = #{title}" +
                ", content = #{content}, is_OK = #{isOK}, type = #{type}, where id = #{id}"
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
    boolean updateByPrimaryKey(Affair affair);
}