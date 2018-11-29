package com.springboot.yhkj.admin.service;

import com.springboot.yhkj.admin.dao.AdminDao;
import com.springboot.yhkj.admin.model.Admin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService {

	@Resource
	private AdminDao adminDao;

	public Admin findAdminByUsernameAndPassword(String username, String password){
		return adminDao.findByUsernameAndPassword(username, password);
	}
	/*@Select("SELECT * FROM `inspur`.`admin` where userName = #{userName} and password = #{password} and state = 1;")
	Admin findByNameAndPassword(Admin admin);
	@Insert("INSERT INTO `inspur`.`admin` (`id`, `userName`, `password`, `realName`, `age`, `phoneNumber`, `headPicture`, `addDate`, `updateDate`, `state`) VALUES (null, #{userName}, #{password}, #{realName}, #{age}, #{phoneNumber}, #{headPicture}, now(), now(), 1);")
	int insert(Admin admin);
	Admin findByUsernameAndPassword(String username, String password);
	Admin findAdminByUsername(String username);*/
}
