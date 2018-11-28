package com.springboot.yhkj.webui.controller;


import com.springboot.yhkj.webui.pojo.User;
import com.springboot.yhkj.webui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/findAll")
    public ModelAndView findAll(ModelAndView modelAndView) {

        System.out.println("PageController:page()");

        List<User> list = userService.findAll();
        modelAndView.setViewName("Home/index");
        //request.setAttribute("userlist", list);

        return modelAndView;


    }
    @GetMapping("/user/login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("/user/login");
        return modelAndView;
    }

    @PostMapping("/user/login")
    public ModelAndView login(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult,HttpSession httpSession){
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            modelAndView.setViewName("user/login");
            return modelAndView;
        }
        String userName = user.getUsername();
        String password = user.getPassword();

        try {
            boolean login=userService.login(userName,password);

            if(login){

                ModelAndView mv = new ModelAndView();
                //封装要显示到视图的数据
                // mv.addObject("msg","hello myfirst mvc");
                //视图名
                //mv.setViewName("hello");
                mv.addObject("userName",userName);
                mv.setViewName("home/index");
                httpSession.setAttribute("UserName", userName);
                return new ModelAndView(new RedirectView("/home/index"));
            }else {
                modelAndView.addObject("error","用户名或密码错误！");
                modelAndView.setViewName("user/login");
                return modelAndView;

            }
        }
        catch (Exception e){
            modelAndView.addObject("error","用户名或密码错误！");
            modelAndView.setViewName("user/login");
            return modelAndView;
        }



        //return  modelAndView;

    }

    @GetMapping("/user/regist")
    public ModelAndView regist (ModelAndView modelAndView){
        modelAndView.setViewName("/user/regist");
        return modelAndView;
    }
    @PostMapping("/user/regist")
    public ModelAndView regist(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult,HttpSession httpSession){
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            modelAndView.setViewName("user/login");
            return modelAndView;
        }
        String userName = user.getUsername();
        String password = user.getPassword();
        try {

            User userNew =userService.getUserById(userName);
            if(userNew!=null){
                modelAndView.addObject("error","该账户已经存在！");
                modelAndView.setViewName("user/regist");
                return modelAndView;
            }else{
                int regist=userService.add(user);
                if(regist>0){

                    ModelAndView mv = new ModelAndView();
                    //封装要显示到视图的数据
                    // mv.addObject("msg","hello myfirst mvc");
                    //视图名
                    //mv.setViewName("hello");
                    mv.addObject("userName",userName);
                    mv.setViewName("home/index");
                    httpSession.setAttribute("UserName", userName);
                    return new ModelAndView(new RedirectView("/home/index"));
                }else {
                    modelAndView.addObject("error","注册失败请稍后再试！");
                    modelAndView.setViewName("user/regist");
                    return modelAndView;

                }
            }


        }
        catch (Exception e){
            modelAndView.addObject("error","注册失败请稍后再试！");
            modelAndView.setViewName("user/regist");
            return modelAndView;
        }

    }


}



