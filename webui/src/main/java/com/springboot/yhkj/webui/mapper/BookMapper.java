package com.springboot.yhkj.webui.mapper;



import com.springboot.yhkj.webui.pojo.Book;

import com.springboot.yhkj.webui.pojo.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("bookMapper")
public interface BookMapper {

    @Select("select * from book LIMIT 10")
    List<Book> findAll();

    @Select("select * from book where id= #{id}")
    News  findBookById(@Param("id") int id);

}
