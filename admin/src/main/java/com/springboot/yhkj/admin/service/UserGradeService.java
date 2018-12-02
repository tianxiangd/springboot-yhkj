package com.springboot.yhkj.admin.service;

import com.springboot.yhkj.admin.model.UserGrade;
import com.springboot.yhkj.admin.dao.UserGradeDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserGradeService {

    @Resource
    private UserGradeDao userGradeDao;

    public List<UserGrade> findAll(){
        return userGradeDao.findAll();
    }

    public List<UserGrade> findUserGradeByGradeName(String name){
        return userGradeDao.findUserGradeByGradeName(name);
    }

    public Page<UserGrade> findAll(Integer currentPage){
        Pageable pageable = new PageRequest(currentPage, 5);
        return userGradeDao.findAll(pageable);
    }

    @Transactional
    public void updateUserGrade(UserGrade  userGrade){
        if (userGradeDao.findUserGradeById(userGrade.getId())!=null){
            userGradeDao.save(userGrade);
            return;
        }
        throw new RuntimeException("MemberGrade中不存在当前的id:"+userGrade.getId());
    }

    public UserGrade add(UserGrade userGrade){
        return userGradeDao.save(userGrade);
    }
}
