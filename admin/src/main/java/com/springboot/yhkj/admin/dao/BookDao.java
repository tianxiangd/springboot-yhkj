package com.springboot.yhkj.admin.dao;

import com.springboot.yhkj.admin.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends JpaRepository<Book,Integer> {
    @Override
    Page<Book> findAll(Pageable pageable);

    @Query("select m from Book m where m.title=?1")
    Page<Book> findAllByTitle(String Title, Pageable pageable);


    Book findBookById(Integer integer);

}
