package com.springboot.yhkj.webui.controller;


import com.springboot.yhkj.webui.pojo.News;
import com.springboot.yhkj.webui.service.NewsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("NewsController相关api")
public class NewsApiController {
    @Autowired
    private NewsService newsService;
    @ApiOperation("获取新闻信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="id",dataType="String",required=true,value="新闻id",defaultValue="1"),
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value="api/news/details",method= RequestMethod.GET)
    public News getNewsDetailsForApi(@RequestParam("id") int id) {
        News news =newsService.findNewsById(id);

        return news;
    }
}
