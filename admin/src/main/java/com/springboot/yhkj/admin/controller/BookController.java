package com.springboot.yhkj.admin.controller;

import com.springboot.yhkj.admin.model.Book;
import com.springboot.yhkj.admin.service.BookService;
import com.springboot.yhkj.admin.util.PageUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/deleteBook")
    public String getDeleteNewsView() {
        return "deleteBook";
    }

    @PostMapping("/deleteBook")
    @ResponseBody
    public String deleteNews(Integer id) {
        try {
            bookService.deleteBooks(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/admin/bookManage_{pageCurrent}_{pageSize}_{pageCount}")
    public String newsManage(Book book, @PathVariable Integer pageCurrent, @PathVariable Integer pageSize, @PathVariable Integer pageCount, Model model) {

        //判断
        if(pageSize == 0) pageSize = 10;
        //由于Pageable是从0开始的 所以这里的页码做减1处理
        if(pageCurrent > 0) pageCurrent -= 1;

        List<Book> newsListAll = bookService.findAll();
        int rows = newsListAll.size();
        if(pageCount == 0) pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;


        Page<Book> books= bookService.findAllBooks(pageCurrent,pageSize);




        //输出

        String pageHTML = PageUtil.getPageContent("bookManage_{pageCurrent}_{pageSize}_{pageCount}?title="+book.getId(), pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML",pageHTML);
        model.addAttribute("books",books);

        return "book/bookManage";
    }


    //新增、修改跳转
    @GetMapping("/admin/bookEdit")
    public String bookEditGet(Model model,Book book) {

        try{
            if(book!=null){
                if(book.getId()!=0){

                    model.addAttribute("book", bookService.findbookByid(book.getId()));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }



        return "book/bookEdit";
    }

    //新增、修改提交
    @PostMapping("/admin/bookEdit")
    public String bookEditPost(Model model, Book book, @RequestParam MultipartFile[] imageFile, HttpSession httpSession) {
        for (MultipartFile file : imageFile) {
            if (file.isEmpty()) {
                System.out.println("文件未上传");
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                Random random = new Random();
                Date date = new Date();
                String strDate = sdf.format(date);
                String fileName = strDate + "_" + random.nextInt(1000) + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."),file.getOriginalFilename().length());
                String realPath = httpSession.getServletContext().getRealPath("/userfiles");
                System.out.println("realPath : "+realPath);
                try {
                    FileUtils.copyInputStreamToFile(file.getInputStream(),new File(realPath, fileName));
                    book.setCoverpath("/userfiles/"+fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(book.getId()!=0){
            bookService.updateBook(book);
        } else {
            bookService.addBook(book);
        }
        return "redirect:bookManage_0_0_0";
    }

}
