package com.springboot.yhkj.admin.dao;


import com.springboot.yhkj.admin.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsDao extends JpaRepository<News, Integer> {
    @Override
    Page<News> findAll(Pageable pageable);

    @Query("select m from news m where m.title=?1")
    Page<News> findAllByTitle(String Title, Pageable pageable);

}
