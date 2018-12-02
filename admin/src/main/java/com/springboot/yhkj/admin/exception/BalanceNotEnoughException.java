package com.springboot.yhkj.admin.exception;


public class BalanceNotEnoughException extends RuntimeException{

    public BalanceNotEnoughException(){
        super("余额不足");
    }

}
