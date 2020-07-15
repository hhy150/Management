package com.example.management.entity;

public enum AffairType{

    ACTIVITY(1,"活动"),
    TASK(2,"任务"),
    BRICK(3,"搬砖");
    private Integer id;
    private String type;

    AffairType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
