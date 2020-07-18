package com.example.management.sevice;

import com.example.management.entity.Affair;
import com.example.management.entity.MemAffair;

import java.util.List;

public interface MemAffairSevice {
    boolean deleteMemAffairById(int id);
    boolean  UndeleteByPrimaryKey(int id);
    boolean addMemAffair(MemAffair memAffair);
    boolean updateMemAffair(MemAffair memAffair);
    MemAffair  getAffairById(int id);
    List<MemAffair> getAll();
}
