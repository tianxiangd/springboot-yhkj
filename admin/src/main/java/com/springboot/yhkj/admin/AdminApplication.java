package com.springboot.yhkj.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
    @RequestMapping("/")
    public String index() {
        return "dashboard";
    }

    @RequestMapping("/home")
    public String home() {
        return "dashboard";
    }

}
