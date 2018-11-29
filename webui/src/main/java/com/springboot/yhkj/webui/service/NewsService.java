package com.springboot.yhkj.webui.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.yhkj.webui.mapper.NewsMapper;

import com.springboot.yhkj.webui.pojo.News;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    public NewsMapper newsMapper;

    public List<News> findAll() {


        return newsMapper.findAll();
    }
    public List<News> search(News news) {
        PageHelper.startPage(news.getPageNum(), news.getPageSize());
        return newsMapper.doSelect(news);
    }

}
