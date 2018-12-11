package com.springboot.yhkj.webui.controller;


import com.github.pagehelper.PageInfo;
import com.springboot.yhkj.webui.pojo.Book;
import com.springboot.yhkj.webui.pojo.News;
import com.springboot.yhkj.webui.service.BookService;
import com.springboot.yhkj.webui.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
@Controller
public class SearchController {

    @Autowired
    public NewsService newsService;
    @Autowired
    public BookService bookService;
    private static int PageSize = 10;

    @RequestMapping(value = "search/list",method= RequestMethod.GET)
    public String searchList(@RequestParam("type") int type,@RequestParam("page") int page, ModelMap map){
        if(type==2){
          /*  Book book =new Book();
            book.setPageNum(page);
            book.setPageSize(PageSize);*/
           //List<Book> books=bookService.findAll();
            PageInfo<Book> pageInfo = new PageInfo<Book>(bookService.search());
            map.addAttribute("pageInfo", pageInfo);

        }
        return "search/list";
    }

}
