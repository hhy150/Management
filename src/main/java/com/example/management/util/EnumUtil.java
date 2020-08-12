package com.example.management.util;

import com.example.management.entity.enums.GenderType;

public class EnumUtil {

    //从String转换成Enum

    //转换成性别的枚举类
    public static GenderType sex(String gender){
        GenderType sex =null;
        if(gender.equals("男"))  sex=GenderType.MAN;
        if(gender.equals("女")) sex=GenderType.WOMAN;
        return sex;
    }


}
