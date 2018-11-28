package com.springboot.yhkj.webui.service;

import com.springboot.yhkj.webui.mapper.UserMapper;
import com.springboot.yhkj.webui.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserMapper userMapper;

    public List<User> findAll(){


        return userMapper.findAll();
    }

    public int add(User user){

        return userMapper.add(user);

    }
    public boolean login(String userName ,String passWord){

        return userMapper.login(userName,passWord);

    }
    public User getUserById(String userName) {
        return this.userMapper.get(userName);
    }


}
