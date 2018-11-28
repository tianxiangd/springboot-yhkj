package com.springboot.yhkj.webui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SubjectBaseController {
    @RequestMapping(value = "/subjectbase/index")
    public ModelAndView index(ModelAndView modelAndView) {

        System.out.println("PageController:page()");
        modelAndView.setViewName("subjectbase/index");
        return modelAndView;

    }
}
