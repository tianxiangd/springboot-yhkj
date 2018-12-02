package com.springboot.yhkj.admin.dao;

import com.springboot.yhkj.admin.model.UserGrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserGradeDao extends JpaRepository<UserGrade, String> {

    UserGrade findUserGradeById(Integer id);

    List<UserGrade> findUserGradeByGradeName(String gradeName);

    Page<UserGrade> findAll(Pageable pageable);

}
