package com.springboot.yhkj.admin.dao;


import com.springboot.yhkj.admin.model.NewsCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Repository
public interface NewsCategoryDao  extends JpaRepository<NewsCategory, Integer>  {
    @Override
    Page<NewsCategory> findAll(Pageable pageable);

    @Override
    List<NewsCategory> findAll();



    NewsCategory findNewsCategoryById(Integer id);

   /* @Update("UPDATE `inspur`.`news_category`SET `state` = #{state} WHERE `id` = #{id};")
    int updateState(NewsCategory newsCategory);*/


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update NewsCategory n set n.start=?2 where n.id=?1",nativeQuery = true)
    int updateState(Integer Id, Integer state);



}
