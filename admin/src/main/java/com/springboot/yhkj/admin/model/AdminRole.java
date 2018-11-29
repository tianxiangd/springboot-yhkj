package com.springboot.yhkj.admin.model;

public enum AdminRole {
    //超级管理员，super
    S_ADMIN("S_ADMIN"),
    //普通管理员，general
    G_ADMIN("G_ADMIN");

    private String role;

    AdminRole(String role){
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}