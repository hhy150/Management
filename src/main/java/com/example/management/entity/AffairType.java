package com.example.management.entity;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum AffairType implements IEnum<Integer> {

    ACTIVITY(1,"活动"),
    TASK(2,"任务"),
    BRICK(3,"搬砖");
    private int value;
    private String type;

    AffairType(int value, String type) {
        this.value=value;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Integer getValue() {
        return  value;
    }

}
