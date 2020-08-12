package com.example.management.service;

import com.example.management.entity.MemAffair;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MemAffairService {
    boolean deleteMemAffairById(int id);
    boolean  UnDeleteByPrimaryKey(int id);
    boolean addMemAffair(MemAffair memAffair);
    boolean updateMemAffair(MemAffair memAffair);
    MemAffair getAffairById(int id);
    PageInfo<MemAffair> getAll(int pageNum, int pageSize);
}
