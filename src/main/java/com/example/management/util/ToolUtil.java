package com.example.management.util;

import com.example.management.entity.Club;
import com.example.management.entity.Department;
import com.example.management.entity.Student;

import java.util.List;
import java.util.regex.Pattern;

public class ToolUtil {

    public static List<Club> saveClub(List<Club> list){
        for (Club club : list) {
            club.setPassword("****");
            club.setUsername("****");
        }
        return list;
    }
    public static List<Student> saveStu(List<Student> list){
        for (Student student : list) {
            student.setStuPassword("****");
        }
        return list;
    }
    public static List<Department> saveDept(List<Department> list,Long clubId){
        for (Department department : list) {
            department.setUsername("****");
            department.setPassword("****");
            department.setComId(clubId);
        }
        return list;
    }

    public static int getRole(String username){
        String student = "[A-Z][0-9]*";
        if("admin".equals(username)){
            return 4;
        }else if(Pattern.matches(student,username)){
            return 1;
        }else if(username.contains("-")){
            return 2;
        }else {
            return 3;
        }
    }

}
