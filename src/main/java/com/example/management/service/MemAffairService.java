package com.example.management.service;

import com.example.management.entity.MemAffair;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MemAffairService {
    boolean deleteMemAffairById(Long id);
    boolean  UnDeleteByPrimaryKey(Long id);
    boolean addMemAffair(MemAffair memAffair);
    boolean updateMemAffair(MemAffair memAffair);
    MemAffair getAffairById(Long id);
    /**
     修改id类型为long
     */
    PageInfo<MemAffair> getAll(int pageNum, int pageSize);
}
