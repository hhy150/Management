package com.example.management.entity.enums;
import com.baomidou.mybatisplus.core.enums.IEnum;

public enum GenderType implements IEnum<Integer> {

    WOMAN(1,"女"),
    MAN(0,"男");

    private Integer value;
    private String gender;

    GenderType(Integer value, String gender) {
        this.value = value;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public Integer getValue() {
        //查询问题出在这里。
        return this.value;
    }
}
