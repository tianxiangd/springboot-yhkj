package com.springboot.yhkj.webui.mapper;
import com.springboot.yhkj.webui.mapper.Provider.NewsProvider;
import com.springboot.yhkj.webui.pojo.News;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("newsMapper")
public interface NewsMapper {


    @Select("select id,title,adddate,browses,image from News")
    List<News> findAll();

    @SelectProvider(type = NewsProvider.class, method = "doSelect")
    @Results(id = "newsResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title")
    })
    List<News> doSelect(@Param("news") News news);


}
