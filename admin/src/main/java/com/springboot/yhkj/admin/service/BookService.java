package com.springboot.yhkj.admin.service;

import com.springboot.yhkj.admin.dao.BookDao;
import com.springboot.yhkj.admin.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
public class BookService {
    @Resource
    private BookDao bookDao;


    public Page<Book> findAllBooks(Integer currentPage, Integer pageSize){
        if (currentPage == null){
            currentPage = 0;
        }
        Pageable pageable = new PageRequest(currentPage, pageSize, Sort.Direction.ASC, "id");
        return bookDao.findAll(pageable);
    }

    public Book addBook(Book book){
        return bookDao.save(book);
    }
    public Book findbookByid(Integer id){return bookDao.findBookById(id);}
    public List<Book> findAll(){
        return bookDao.findAll();
    }
    public void deleteBooks(Integer bookId){

        bookDao.deleteById(bookId);
    }

    @Transactional
    public void updateBook(Book book){
        if (bookDao.findById(book.getId())!=null){
            bookDao.save(book);
            return;
        }
        throw new RuntimeException("不存在当前的id:"+book.getId());
    }
}


