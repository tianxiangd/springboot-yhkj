package com.springboot.yhkj.webui.mapper;

import com.springboot.yhkj.webui.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("userMapper")
public interface UserMapper {

    @Select("select * from user")
    List<User> findAll();


    @Insert({ "insert into user(id, username, password) values(#{id}, #{username}, #{password})" })

    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(User user);

    @Select("select * from user where username=#{username} and password=#{password}")
    public boolean login(@Param("username") String userName, @Param("password") String passWord);

    @Select("select * from user where username=#{username}")
    public User get(@Param("username") String userName);
}
