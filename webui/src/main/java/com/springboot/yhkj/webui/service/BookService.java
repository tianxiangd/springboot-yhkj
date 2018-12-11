package com.springboot.yhkj.webui.service;


import com.springboot.yhkj.webui.mapper.BookMapper;
import com.springboot.yhkj.webui.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Book findBookById(int id) {
        return bookMapper.findBookById(id);
    }

}