package com.springboot.yhkj.admin.controller;

import com.springboot.yhkj.admin.model.User;
import com.springboot.yhkj.admin.model.UserGrade;
import com.springboot.yhkj.admin.exception.IdNotFoundException;
import com.springboot.yhkj.admin.exception.IntegralNotEnoughException;
//import cn.lger.service.GiftService;
import com.springboot.yhkj.admin.service.UserGradeService;
import com.springboot.yhkj.admin.service.UserService;
import com.springboot.yhkj.admin.util.FileUploadUtil;
import com.springboot.yhkj.admin.util.MemberNumberRandomUtil;
import com.springboot.yhkj.admin.util.UUIDRandomUtil;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;



@Controller
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private UserGradeService userGradeService;
    @Resource
    private BCryptPasswordEncoder encoder;


    @GetMapping("/addUser")
    public String getAddUserView() {
        return "User/addUser";
    }

    @PostMapping("/addUser")
    public String addUser(User user, String gradeName, MultipartFile icon, Map<String, Object> model) {
        //处理上传文件
        try {
            if (icon == null)
                return "error";
            if (icon.getOriginalFilename().equals(""))
                user.setIconPath("/img/icon/common.jpg");
            else
                user.setIconPath(FileUploadUtil.upload(icon, "/img/icon/", UUIDRandomUtil.get32UUID()));
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        user.setId(MemberNumberRandomUtil.randomMemberNumber());
        //通过会员等级名获取会员类型
        List<UserGrade> list = userGradeService.findUserGradeByGradeName(gradeName);
        //保证输入的会员名是存在的
        if (list.get(0) == null)
            return "error";
        //设置会员类型
        user.setUserGrade(list.get(0));
        user.setState("正常");
        user.setBalance((float) 0);
        user.setUserIntegral(0L);
        user.setPassword(encoder.encode(user.getPassword()));
//        System.out.println(member);
        user = userService.addUser(user);

        model.put("user", user);
        return "user/addUserSuccess";
    }

    @GetMapping("/getGrade")
    @ResponseBody
    public List<UserGrade> getGrade() {
        return userGradeService.findAll();
    }

    @GetMapping("/queryUser")
    public String getQueryMemberView() {
        return "user/queryUser";
    }

    @PostMapping("/queryUser")
    @ResponseBody
    public Page<User> queryUser(Integer currentPage, String userName) {
        if (userName == null || userName.trim().equals(""))
            return userService.findUsers(currentPage);
        return userService.findUsersByUserName(currentPage, userName);
    }

    @GetMapping("/modifyUserState")
    public String getModifyUserStateView() {
        return "user/modifyUserState";
    }

    @PostMapping("/modifyUserState")
    @ResponseBody
    public String modifyUserStateView(String id, String state) {
        userService.modifyUserState(id, state);
        return "modifyUserState";
    }

    @GetMapping("/deleteUser")
    public String getDeleteUserView() {
        return "user/deleteUser";
    }

    @PostMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(String id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @GetMapping("/balanceRecharge")
    public String getBalanceRechargeView() {
        return "/balanceRecharge";
    }

    @PostMapping("/balanceRecharge")
    @ResponseBody
    public String balanceRecharge(String id, String balance) {
        try {
            userService.balanceRecharge(id, balance);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @GetMapping("/integralLottery")
    public String getIntegralLotteryView() {
        return "integralLottery";
    }

    @PostMapping("/integralLottery")
    @ResponseBody
    public User integralLottery(Integer allIntegral) {
        if (allIntegral == null)
            return null;
        try {
            return userService.integralLottery(allIntegral);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
