package com.example.management.entity;

public enum AffairType {

    TASK(0,"任务"),
    ACTIVITY(1,"活动"),
    BRICK(2,"搬砖");
    private int value ;
    private String type;

    AffairType(int value, String type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
