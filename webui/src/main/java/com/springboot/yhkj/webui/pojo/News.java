package com.springboot.yhkj.webui.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//新闻表
public class News extends Basepojo {
    public News() {

    }

    //新闻id
    private Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    //新闻标题
    private String Title;
    public String getTitle() {
        return Title;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }

    //发表时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+5:30")
    private Date addDate;
    public Date getAddDate() {
        return addDate;
    }
    public void Date(Date addDate) {
        this.addDate = addDate;
    }

    //点击次数
    private Integer Browses;
    public Integer getBrowses() {
        return Browses;
    }
    public void setBrowses(Integer Browses) {
        this.Browses = Browses;
    }

    //新闻内容
    private String Content;
    public String getContent() {
        return Content;
    }
    public void setContent(String Content) {
        this.Content = Content;
    }

    //图片地址
   private String Image;
    public String getImage() {
        return Image;
    }
    public void setImage(String Image) {
        this.Image = Image;
    }
}

