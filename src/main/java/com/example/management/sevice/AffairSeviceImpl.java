package com.example.management.sevice;

import com.example.management.entity.Affair;
import com.example.management.repository.AffairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AffairSeviceImpl implements AffairSevice {
    @Autowired
    AffairMapper affairMapper;

    @Override
    public boolean deleteAffairById(int id) {
        return  affairMapper.deleteByPrimaryKey(id);
    }

    @Override
    public boolean UndeleteByPrimaryKey(int id) {
        return affairMapper.UndeleteByPrimaryKey(id);
    }

    @Override
    public boolean addAffair(Affair affair) {
        return affairMapper.insert(affair);
    }

    @Override
    public boolean updateAffair(Affair affair) {
        return affairMapper.updateByPrimaryKey(affair);
    }

    @Override
    public Affair getAffairById(int id) {
        return affairMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Affair> getAll() {
        List <Affair> list;
        list=affairMapper.selectAll();
        return  list;
    }
}
