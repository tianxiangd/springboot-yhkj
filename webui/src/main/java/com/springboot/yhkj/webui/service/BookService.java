package com.springboot.yhkj.webui.service;

import com.github.pagehelper.PageHelper;
import com.springboot.yhkj.webui.mapper.BookMapper;
import com.springboot.yhkj.webui.mapper.NewsMapper;
import com.springboot.yhkj.webui.pojo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.yhkj.webui.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    @Autowired
    public BookMapper bookMapper;

    public List<Book> findAll() {


        return bookMapper.findAll();
    }
    public List<Book> search() {
        return bookMapper.findAll();
    }

    public News findBookById(int id) {
        return bookMapper.findBookById(id);
    }

}