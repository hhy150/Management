package com.example.management.entity;


public class AuthURL {
   //可访问的路径的规则
   //注意/* 和/** 的区别
   public static String[] STU_AUTH = {"/common/**","login/logout"};
   public static String[] CLUB_AUTH ={"/common/**", "/resetPwd/**","memaffair/**",
           "/affair/**", "/department/**","/stu/**","/club/update","/login/**"};
   public static String[] DEPT_AUTH ={"/common/**","/resetPwd/**",
           "/affair/send","/stu/**","/login/**"};
   public static String[] SUPER_ADMIN_AUTH={"/common/**","/resetPwd/**",
           "/club/**","/department/**","/login/**"};
 }
