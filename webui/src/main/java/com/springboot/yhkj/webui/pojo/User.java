package com.springboot.yhkj.webui.pojo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

//用户表
public class User extends Basepojo{

    public User() {
    }
    @NotEmpty(message="用户名不能为空！")
    private String username;

    private String nickName;

    @Size(min=6,max=10,message = "密码长度必须6到10位")
    private String password;

    private Integer age;

    private Integer sex;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    private Integer id;
    public void setId(Integer id) {
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
