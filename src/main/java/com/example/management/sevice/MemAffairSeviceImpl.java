package com.example.management.sevice;

import com.example.management.entity.Affair;
import com.example.management.entity.MemAffair;
import com.example.management.repository.AffairMapper;
import com.example.management.repository.MemAffairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemAffairSeviceImpl implements MemAffairSevice {
   @Autowired
    MemAffairMapper memAffairMapper;

    @Override
    public boolean deleteMemAffairById(int id) {
        return memAffairMapper.deleteByPrimaryKey(id);
    }

    @Override
    public boolean UndeleteByPrimaryKey(int id) {
        return memAffairMapper.deleteByPrimaryKey(id);
    }

    @Override
    public boolean addMemAffair(MemAffair memAffair) {
        return memAffairMapper.insert(memAffair);
    }

    @Override
    public boolean updateMemAffair(MemAffair memAffair) {
        return memAffairMapper.updateByPrimaryKey(memAffair);
    }

    @Override
    public MemAffair getAffairById(int id) {
        return memAffairMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MemAffair> getAll() {
        List <MemAffair> list;
        list=memAffairMapper.selectAll();
        return  list;
    }
}
