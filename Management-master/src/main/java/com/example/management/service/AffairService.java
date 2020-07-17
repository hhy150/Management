package com.example.management.service;


import com.example.management.entity.Affair;
import com.example.management.entity.SendAffairParam;

public interface AffairService {
    String sendAffair(SendAffairParam sendAffairParam);

    boolean delAffairByTitle(String affairTitle);

    Affair addAffair(Affair affair);

    boolean modifyAffair(Affair affair);

    Affair getAffairByTitle(String title);

    Affair getAffairById(long id);
}
