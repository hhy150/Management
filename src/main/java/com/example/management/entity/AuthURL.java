package com.example.management.entity;


public class AuthURL {
   //可访问的路径的规则
   public static String[] STU_AUTH = {"/common/*","/login/check"};
   public static String[] CLUB_AUTH ={"/common/*","/resetPwd/*","/affair/*","/dept/**","/stu/*","/club/update","/login/*"};
   public static String[] DEPT_AUTH ={"/common/*","/resetPwd/*","/affair/send","/stu/**","/login/*","/memaffair/*"};
   public static String[] SUPER_ADMIN_AUTH={"/common/*","/resetPwd/*","/club/*","/dept/**","/login/*"};
 }
