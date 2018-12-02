package com.springboot.yhkj.admin.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class User {

	@Id
	private String id;
	private String iconPath;
	private String userName;
	private String password;
	private String phone;
	private LocalDate birthday;
	private String sex;
	//会员等级
	@ManyToOne
	private UserGrade userGrade;
	//会员积分
	private Long userIntegral;
	//会员余额
	private Float balance;
	//会员状态 挂失、停用、正常
	private String state;
	private String email;


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthday() {
		return birthday.toString();
	}

	public void setBirthday(String birthday) {
		this.birthday = LocalDate.parse(birthday);
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public UserGrade getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(UserGrade  userGrade) {
		this.userGrade = userGrade;
	}

	public Long getUserIntegral() {
		return userIntegral;
	}

	public void setUserIntegral(Long userIntegral) {
		this.userIntegral = userIntegral;
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public LocalDate getLocalDate(){
		return this.birthday;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", iconPath='" + iconPath + '\'' +
				", memberName='" + userName + '\'' +
				", password='" + password + '\'' +
				", phone='" + phone + '\'' +
				", birthday=" + birthday +
				", sex='" + sex + '\'' +
				", memberGrade=" + userGrade +
				", memberIntegral=" + userIntegral +
				", balance=" + balance +
				", state='" + state + '\'' +
				'}';
	}
}
