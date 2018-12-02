package com.springboot.yhkj.admin.controller;

import com.springboot.yhkj.admin.model.UserGrade;
import com.springboot.yhkj.admin.service.UserGradeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
public class UserGradeController {

    @Resource
    private UserGradeService userGradeService;

    @GetMapping("/userGrade")
    public String getUserGradeView(){
        return "user/userGrade";
    }

    @PostMapping("/userGrade")
    @ResponseBody
    public Page<UserGrade> memberGrade(Integer currentPage){
        return userGradeService.findAll(currentPage);
    }

    @PostMapping("/updateUserGrade")
    @ResponseBody
    public String updateMemberGrade(UserGrade userGrade){
        try{
            userGradeService.updateUserGrade(userGrade);
        } catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @PostMapping("/addMemberGrade")
    @ResponseBody
    public UserGrade addMemberGrade(UserGrade userGrade){
        return userGradeService.add(userGrade);
    }

}
