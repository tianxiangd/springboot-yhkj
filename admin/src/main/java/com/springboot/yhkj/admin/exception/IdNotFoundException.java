package com.springboot.yhkj.admin.exception;


public class IdNotFoundException extends RuntimeException{

    public IdNotFoundException(){
        super("id找不到");
    }
}
