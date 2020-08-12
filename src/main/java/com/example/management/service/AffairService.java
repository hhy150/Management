package com.example.management.service;

import com.example.management.entity.Affair;
import com.example.management.entity.SendAffairParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AffairService {
    Affair getAffairById(int id);

    boolean deleteAffairById(int id);

    boolean addAffair(Affair affair);

    boolean UnDeleteByPrimaryKey(int id);

    boolean updateAffair(Affair affair);

    PageInfo<Affair> getAll(int pageNum, int pageSize);

    String sendAffair(SendAffairParam sendAffairParam);
}
