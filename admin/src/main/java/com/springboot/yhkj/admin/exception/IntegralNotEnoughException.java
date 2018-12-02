package com.springboot.yhkj.admin.exception;


public class IntegralNotEnoughException extends RuntimeException{

    public IntegralNotEnoughException(){
        super("账户积分不足");
    }

}
