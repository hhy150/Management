package com.example.management.entity;

/**
 * 用于表示男女
 */
public enum GenderType {

    WOMAN(1,"女"),
    MAN(0,"男");

    private int type;
    private String gender;

    GenderType(int type, String gender) {
        this.type = type;
        this.gender = gender;
    }

    public int getType() {
        return type;
    }

    public String getGender() {
        return gender;
    }
}
