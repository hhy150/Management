package com.example.management.service.impl;

import com.example.management.entity.MemAffair;
import com.example.management.mapper.MemAffairMapper;
import com.example.management.service.MemAffairService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@CacheConfig(cacheNames = "memAffair")
@Service
public class MemAffairSeviceImpl implements MemAffairService {
   @Autowired
   MemAffairMapper memAffairMapper;

    @CacheEvict(key = "#id",condition = "#result==true")
    @Override
    public boolean deleteMemAffairById(int id) {
        return memAffairMapper.deleteByPrimaryKey(id);
    }

    @CachePut(key = "#id",condition = "#result==true")
    @Override
    public boolean UnDeleteByPrimaryKey(int id) {
        return memAffairMapper.deleteByPrimaryKey(id);
    }

    @CachePut(key = "#memAffair.id",condition = "#result==true")
    @Override
    public boolean addMemAffair(MemAffair memAffair) {
        return memAffairMapper.insert(memAffair);
    }

    @CachePut(key = "#memAffair.id",condition = "#result==true")
    @Override
    public boolean updateMemAffair(MemAffair memAffair) {
        return memAffairMapper.updateByPrimaryKey(memAffair);
    }

    @Cacheable(key = "#id")
    @Override
    public MemAffair getAffairById(int id) {
        return memAffairMapper.selectByPrimaryKey(id);
    }

    @Cacheable(key = "#root.methodName")
    @Override
    public PageInfo<MemAffair> getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List <MemAffair> list;
        list=memAffairMapper.selectAll();
        PageInfo<MemAffair> pageInfo = new PageInfo<>(list);
        return  pageInfo;
    }
}
