package com.example.management.service;


import java.util.ArrayList;

public interface ResetPwdService{

    Boolean resetPwd(Long id, String newPwd,int type);
    Boolean resetPwds(ArrayList<Long> list, String newPwd,int type);
    Boolean resetAllPwd(String newPwd);
}
