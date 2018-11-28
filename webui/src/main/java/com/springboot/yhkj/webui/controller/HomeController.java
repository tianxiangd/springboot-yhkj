package com.springboot.yhkj.webui.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @RequestMapping(value = "/home/index")
    public ModelAndView findAll(ModelAndView modelAndView, HttpSession httpSession) {

        System.out.println("PageController:page()");
        modelAndView.setViewName("Home/index");
        //request.setAttribute("userlist", list);
         httpSession.getAttribute("UserName");
        return modelAndView;
    }
}
