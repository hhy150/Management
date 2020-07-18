package com.example.management.sevice;

import com.example.management.entity.Affair;

import java.util.List;

public interface AffairSevice {
    boolean deleteAffairById(int id);
    boolean  UndeleteByPrimaryKey(int id);
    boolean addAffair(Affair affair);
    boolean updateAffair(Affair affair);
    Affair  getAffairById(int id);
    List<Affair> getAll();
}
