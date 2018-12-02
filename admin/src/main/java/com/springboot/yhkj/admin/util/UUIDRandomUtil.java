package com.springboot.yhkj.admin.util;

import java.util.UUID;


public class UUIDRandomUtil {

    public static String get32UUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

}
