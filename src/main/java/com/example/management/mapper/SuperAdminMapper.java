package com.example.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.management.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SuperAdminMapper  extends BaseMapper<Admin> {

}
