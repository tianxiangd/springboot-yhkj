package com.springboot.yhkj.webui.mapper.Provider;

import com.springboot.yhkj.webui.pojo.Book;

import org.apache.ibatis.annotations.Param;

public class BookProvider {
    public String doSelect(@Param("book") Book book) {
        //SQL sql = new SQL();
        //sql.SELECT("[id]", "[title]");
       // sql.FROM("[News]");
       /* if (!news.getTitle().isEmpty()) {
            sql.WHERE("[title] = #{news.title}");
        }
        if (news.getId() != 0) {
            sql.WHERE("[id] = #{news.id}");
        }*/
        return "select * from book";
}
}
