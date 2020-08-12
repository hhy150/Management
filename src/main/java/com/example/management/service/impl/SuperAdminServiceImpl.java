package com.example.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.management.entity.Admin;
import com.example.management.mapper.SuperAdminMapper;
import com.example.management.service.SuperAdminService;
import com.example.management.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminServiceImpl  extends ServiceImpl<SuperAdminMapper,Admin> implements SuperAdminService {

}
