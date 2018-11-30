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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class NewsService {

    @Resource
    private NewsDao newsDao;


    public Page<News> findAllNews(Integer currentPage){
        if (currentPage == null){
            currentPage = 1;
        }
        Pageable pageable = new PageRequest(currentPage, 3, Sort.Direction.ASC, "id");
        return newsDao.findAll(pageable);
    }
    public News addNews(News news){
        return newsDao.save(news);
    }
    public void deleteNews(Integer newsId){

        newsDao.deleteById(newsId);
    }

    @Transactional
    public void updateNews(News news){
        if (newsDao.findById(news.getId())!=null){
            newsDao.save(news);
            return;
        }
        throw new RuntimeException("MemberGrade中不存在当前的id:"+news.getId());
    }


	/*News findById(News news);


	List<News> list(News news);


	int count(News news);

	int insert(News news);

	int update(News news);

	int updateState(News news);*/
	
}
