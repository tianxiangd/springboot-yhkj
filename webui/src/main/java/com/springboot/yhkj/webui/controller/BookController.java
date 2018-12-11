package com.springboot.yhkj.webui.controller;


import com.springboot.yhkj.webui.pojo.Book;
import com.springboot.yhkj.webui.service.BookService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

    @Autowired
    private BookService  bookService;

    @RequestMapping(value = "/book/index")
    public ModelAndView index(ModelAndView modelAndView) {

        System.out.println("PageController:page()");
        modelAndView.setViewName("book/index");

        return modelAndView;


    }

    @RequestMapping(value = "book/details",method= RequestMethod.GET)
    public String getNewsDetails(@RequestParam("id") int id, ModelMap map) {
        Book book =bookService.findBookById(id);
        map.addAttribute("Book", book);
        return "book/details";
    }
}
