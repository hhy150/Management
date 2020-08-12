package com.example.management.entity;

public class SessionAttributes {

    private int role;
    private String username;

    public SessionAttributes(int role, String username) {
        this.role = role;
        this.username = username;
    }

    public int getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }
}
