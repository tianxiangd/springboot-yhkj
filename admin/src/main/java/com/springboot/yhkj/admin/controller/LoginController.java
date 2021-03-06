package com.springboot.yhkj.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginView(HttpServletRequest request) {
        if (request.getSession().getAttribute("admin") != null)
            return "dashboard";
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("error", "登录失败");
        return "login";
    }



}