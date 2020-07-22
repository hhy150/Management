package com.example.management.dept.util;

import org.springframework.stereotype.Component;

public class ToolUtil {


    public final static String SUC ="成功";
    public final static String ERR ="失败";


    public static String result(Object data,String msg){

        return "result{" +
                " data='" + data + '\'' +
                ", msg=" + msg +
                '}';
    }
}
