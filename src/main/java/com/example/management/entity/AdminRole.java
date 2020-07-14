package com.example.management.entity;

public enum AdminRole {

    CLUB(0, "社团管理员"),
    DEPARTMENT(1, "社团部门管理员");

    private int value;
    private String role;

    AdminRole(int value, String role) {
        this.value = value;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}