package com.springboot.yhkj.webui.mapper.Provider;

import com.springboot.yhkj.webui.pojo.News;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class NewsProvider {
    public String doSelect(@Param("news") News news) {
        //SQL sql = new SQL();
        //sql.SELECT("[id]", "[title]");
       // sql.FROM("[News]");
       /* if (!news.getTitle().isEmpty()) {
            sql.WHERE("[title] = #{news.title}");
        }
        if (news.getId() != 0) {
            sql.WHERE("[id] = #{news.id}");
        }*/
        return "select * from news";
}
}
