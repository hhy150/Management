package com.example.management.entity;


public class AuthURL {
   //可访问的路径的规则
   public static String[] STU_AUTH = {"/common/*","login/check"};
   public static String[] CLUB_AUTH ={"/common/*","/resetPwd/*","/affair/*","/dept/*","/stu/*","club/update","login/*"};
   public static String[] DEPT_AUTH ={"/common/*","/resetPwd/*","/affair/send","/stu/*","login/*"};
   public static String[] SUPER_ADMIN_AUTH={"/common/*","/resetPwd/*","/club/*","/dept/*","login/*"};

    /*
     * 权限需求分析:
     * 1.登录权限，没有登录的人先登录，而且任何路径都不可访问，登录路径之外。
     * 2.superAdmin：通过查找社团成员/增删改查社团和部门/修改密码//重置密码
     * 3.clubAdmin:  增删改查社团成员和部门/获得成员和部门列表/派发任务/增删改查任务/  //修改密码/重置密码
     * 4.deptAdmin:  增删改查社团成员/ 获得成员列表/ 派发任务/增删改查任务/修改密码/重置密码
     * 5.mem:        修改密码
    */
 }
