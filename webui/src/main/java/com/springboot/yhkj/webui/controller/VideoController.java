package com.springboot.yhkj.webui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VideoController {
    @RequestMapping(value = "/video/index")
    public ModelAndView index(ModelAndView modelAndView) {
        System.out.println("PageController:page()");
        modelAndView.setViewName("video/index");
        return modelAndView;
    }
}
