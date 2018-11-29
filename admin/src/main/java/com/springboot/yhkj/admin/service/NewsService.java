package com.springboot.yhkj.admin.service;

import java.util.List;

import com.springboot.yhkj.admin.dao.AdminDao;
import com.springboot.yhkj.admin.dao.NewsDao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.springboot.yhkj.admin.model.News;
import com.springboot.yhkj.admin.util.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NewsService {

    @Resource
    private NewsDao newsDao;

    public News addNews(News member){
        return newsDao.save(member);
    }

    public News findNewsById(String id){
        return newsDao.findNewsById(id);
    }

	/*News findById(News news);


	List<News> list(News news);


	int count(News news);

	int insert(News news);

	int update(News news);

	int updateState(News news);*/
	
}
