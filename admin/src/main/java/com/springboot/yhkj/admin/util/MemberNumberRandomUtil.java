package com.springboot.yhkj.admin.util;

import java.text.SimpleDateFormat;


public class MemberNumberRandomUtil {

    public static SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmssSS");

    public synchronized static String randomMemberNumber(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return date.format(System.currentTimeMillis());
    }
}
