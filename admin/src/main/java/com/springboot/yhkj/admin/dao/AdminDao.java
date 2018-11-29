package com.springboot.yhkj.admin.dao;

import com.springboot.yhkj.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>{

    Admin findByUsernameAndPassword(String username, String password);

    Admin findAdminByUsername(String username);
}
