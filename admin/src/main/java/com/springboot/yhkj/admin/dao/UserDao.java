package com.springboot.yhkj.admin.dao;


import com.springboot.yhkj.admin.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface UserDao extends JpaRepository<User, String> {

    User findUserById(String id);

    Page<User> findAll(Pageable pageable);

    @Query("select m from User m where m.userName = ?1")
    Page<User> findAllByUserName(String userName, Pageable pageable);

    @Query("select count(m.id) from  User m")
    int queryAllCount();

    List<User> findByBirthday(LocalDate birthday);
}
