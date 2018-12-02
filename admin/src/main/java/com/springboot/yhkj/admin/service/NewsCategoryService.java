package com.springboot.yhkj.admin.service;

import java.util.List;

import com.springboot.yhkj.admin.dao.NewsCategoryDao;
import com.springboot.yhkj.admin.model.News;
import com.springboot.yhkj.admin.model.NewsCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.springboot.yhkj.admin.model.NewsCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class NewsCategoryService {

    @Resource
    private NewsCategoryDao newsCategoryDao;

    public List<NewsCategory> findAll(){
        return newsCategoryDao.findAll();
    }

    public NewsCategory findById(NewsCategory newsCategory){
        return newsCategoryDao.findNewsCategoryById(newsCategory.getId());
    }
    @Transactional
    public void updateNewsCategory(NewsCategory newsCategory){
        if (newsCategoryDao.findById(newsCategory.getId())!=null){
            newsCategoryDao.save(newsCategory);
            return;
        }
        throw new RuntimeException("news中不存在当前的id:"+newsCategory.getId());
    }

    public NewsCategory addNewsCategory(NewsCategory newsCategory){
        //return newsCategoryDao.save(newsCategory);
        return newsCategoryDao.save(newsCategory);
    }

    public void updateState(NewsCategory newsCategory){

        newsCategoryDao.updateState(newsCategory.getId(),newsCategory.getState());
    }

	/*@Select("SELECT * FROM `inspur`.`news_category` where id = #{id};")
	NewsCategory findById(NewsCategory newsCategory);

	@Select({
		"<script>",
		"SELECT * FROM `inspur`.`news_category`",
		"WHERE state = 1",
			"<when test='name!=null'>",
				"AND name LIKE CONCAT('%',#{name},'%')",
			"</when>",
			"order by addDate desc limit #{start},#{end}",
		"</script>"
	})
	List<NewsCategory> list(NewsCategory newsCategory);

	@Select({
		"<script>",
		"SELECT count(*) FROM `inspur`.`news_category`",
		"WHERE state = 1",
			"<when test='name!=null'>",
				"AND name LIKE CONCAT('%',#{name},'%')",
			"</when>",
		"</script>"
	})
	int count(NewsCategory newsCategory);

	@Insert("INSERT INTO `inspur`.`news_category` (`id`, `name`, `description`, `image`, `addDate`, `state`) VALUES (null, #{name}, #{description}, #{image}, now(), 1);")
	int insert(NewsCategory newsCategory);

	@Update("UPDATE `inspur`.`news_category`SET `name` = #{name}, `description` = #{description}, `image` = #{image} WHERE `id` = #{id};")
	int update(NewsCategory newsCategory);

	@Update("UPDATE `inspur`.`news_category`SET `state` = #{state} WHERE `id` = #{id};")
	int updateState(NewsCategory newsCategory);*/
}
