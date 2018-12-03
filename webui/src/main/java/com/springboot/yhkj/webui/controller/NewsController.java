package com.springboot.yhkj.webui.controller;
import com.github.pagehelper.PageInfo;
import com.springboot.yhkj.webui.pojo.News;
import com.springboot.yhkj.webui.service.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;
    private static int PageSize = 10;

    @RequestMapping(value = "news")
    public String index (HttpServletRequest request) {

        System.out.println("PageController:page()");

      //  List<User> list = userService.findAll();


      //  request.setAttribute("userlist", list);

        return "/news/index";
    }
    @RequestMapping("news/index")
    public String eachNews(WebRequest webRequest){
        // 模拟数据库数据保存到List集合
       /* List<News> newsList = new ArrayList<>();
        newsList.add(new News(1, "巴扎黑1", "2018-1-1",100,"ab"));
        newsList.add(new News(2, "巴扎黑2", "2018-1-1",100,"qq"));
        newsList.add(new News(3, "巴扎黑3", "2018-1-1",100,"qw"));
        newsList.add(new News(4, "巴扎黑4", "2018-1-1",100,"qq"));
        newsList.add(new News(5, "巴扎黑5", "2018-1-1",100,"qq"));*/

        List<News> newsList = newsService.findAll();
        // 保存数据到request作用范围域
        webRequest.setAttribute("newsList", newsList, RequestAttributes.SCOPE_REQUEST);
        return "/news/index";
    }

    @RequestMapping("news/list")
    public String getNewsList(@ModelAttribute News news, ModelMap map) {
        news.setPageSize(PageSize);
        PageInfo<News> pageInfo = new PageInfo<News>(newsService.search(news));

        map.addAttribute("pageInfo", pageInfo);
        return "news/list";
    }

    @RequestMapping(value = "news/details",method= RequestMethod.GET)
    public String getNewsDetails(@RequestParam("id") int id, ModelMap map) {
        News news =newsService.findNewsById(id);
        map.addAttribute("News", news);
        return "news/details";
    }

    @PostMapping("news/queryIndexNews")
    @ResponseBody
    public List<News> queryIndexNews() {
        News news= new News();
        news.setPageNum(1);
        news.setPageSize(5);
        return newsService.search(news);
    }
}
