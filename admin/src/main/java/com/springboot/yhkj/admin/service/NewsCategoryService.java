package com.springboot.yhkj.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.springboot.yhkj.admin.model.NewsCategory;
import org.springframework.stereotype.Service;

@Service
public class NewsCategoryService {
	
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
