package com.springboot.yhkj.admin.dao;

import com.springboot.yhkj.admin.model.News;
 import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable; 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query;  
import java.time.LocalDate; 
import java.util.List; 


public interface NewsDao extends JpaRepository<News, Integer> {

    News findNewsById(String id);  

    Page<News> findAll(Pageable pageable);

    @Query("select m from news m where m.title=?1")
    Page<News> findAllByTitle(String Title, Pageable pageable);


}
