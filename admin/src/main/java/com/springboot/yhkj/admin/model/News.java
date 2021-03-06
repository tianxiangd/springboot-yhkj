package com.springboot.yhkj.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class News extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String title;
	private String description;
	private Integer category;

	private String image;
	private String content;
	private Date adddate;

	private Date updatedate;

	private Integer state;

	//private Integer commendState;


	private Integer browses;

	private Integer likes;

	private Integer comments;

	private Integer score;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}



	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public Date getAddDate() {
		return adddate;
	}

	public void setAddDate(Date adddate) {
		this.adddate = adddate;
	}

	public Date getUpdateDate() {
		return updatedate;
	}

	public void setUpdateDate(Date updatedate) {
		this.updatedate = updatedate;
	}


	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/*public Integer getCommendState() {
		return commendState;
	}

	public void setCommendState(Integer commendState) {
		this.commendState = commendState;
	}*/

	public Integer getBrowses() {
		return browses;
	}

	public void setBrowses(Integer browses) {
		this.browses = browses;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}





}
