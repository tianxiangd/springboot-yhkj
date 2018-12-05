package com.springboot.yhkj.admin.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.springboot.yhkj.admin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.yhkj.admin.model.News;
import com.springboot.yhkj.admin.service.NewsCategoryService;
import com.springboot.yhkj.admin.service.NewsService;
import com.springboot.yhkj.admin.util.Constant;
import com.springboot.yhkj.admin.util.PageUtil;

@Controller
public class NewsController {

    @Resource
    private NewsService newsService;

    @GetMapping("/deleteNews")
    public String getDeleteNewsView() {
        return "deleteNews";
    }

    @PostMapping("/deleteNews")
    @ResponseBody
    public String deleteNews(Integer id) {
        try {
            newsService.deleteNews(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

	@Resource
	private NewsCategoryService newsCategoryService;
	
	
	@RequestMapping("/admin/newsManage_{pageCurrent}_{pageSize}_{pageCount}")
	public String newsManage(News news, @PathVariable Integer pageCurrent, @PathVariable Integer pageSize, @PathVariable Integer pageCount, Model model) {
		
		//判断
		if(pageSize == 0) pageSize = 10;
		//由于Pageable是从0开始的 所以这里的页码做减1处理
		if(pageCurrent > 0) pageCurrent -= 1;

		List<News> newsListAll = newsService.findAll();
		int rows = newsListAll.size();
		if(pageCount == 0) pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;

		//Pageable pageable = PageRequest.of(pageCurrent, pageSize);
		Page<News> newsList= newsService.findAllNews(pageCurrent,pageSize);



		//文章分类
		NewsCategory newsCategory = new NewsCategory();
		newsCategory.setStart(0);
		newsCategory.setEnd(Integer.MAX_VALUE);
		List<NewsCategory> newsCategoryList = newsCategoryService.findAll();

		//输出
		model.addAttribute("newsCategoryList", newsCategoryList);
		model.addAttribute("newsList", newsList);
		String pageHTML = PageUtil.getPageContent("newsManage_{pageCurrent}_{pageSize}_{pageCount}?title="+news.getTitle()+"&category="+news.getCategory()+"&commendState="+news.getCommendState()+"&orderBy="+news.getOrderBy(), pageCurrent, pageSize, pageCount);
		model.addAttribute("pageHTML",pageHTML);
		model.addAttribute("news",news);
		
		return "news/newsManage";
	}
	
	
	//文章新增、修改跳转
	@GetMapping("/admin/newsEdit")
	public String newsEditGet(Model model,News news) {
		NewsCategory newsCategory = new NewsCategory();
		newsCategory.setStart(0);
		newsCategory.setEnd(Integer.MAX_VALUE);
		List<NewsCategory> newsCategoryList = newsCategoryService.findAll();
		model.addAttribute("newsCategoryList",newsCategoryList);
		try {
			if(news.getId()!=0){
				News newT = newsService.findnewsByid(news.getId());
				model.addAttribute("news",newT);
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return "news/newsEdit";
	}

	//文章新增、修改提交
	@PostMapping("/admin/newsEdit")
	public String newsEditPost(Model model,News news, @RequestParam MultipartFile[] imageFile,HttpSession httpSession) {
		for (MultipartFile file : imageFile) {
			if (file.isEmpty()) {
				System.out.println("文件未上传");
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				Random random = new Random();
				Date date = new Date();
				String strDate = sdf.format(date);
				String fileName = strDate + "_" + random.nextInt(1000) + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."),file.getOriginalFilename().length());
				//String realPath = httpSession.getServletContext().getRealPath("/userfiles");
				//System.out.println("realPath : "+realPath);
				/*try {
					FileUtils.copyInputStreamToFile(file.getInputStream(),new File(realPath, fileName));
					news.setImage("/userfiles/"+fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}*/
				String path=ClassUtils.getDefaultClassLoader().getResource("").getPath();
				File pathf= new File(path);


				//如果上传目录为/static/images/upload/，则可以如下获取：
				File upload = new File(pathf.getAbsolutePath(),"static/images/upload/");
				if(!upload.exists()) upload.mkdirs();

				try {
					// Get the file and save it somewhere
					byte[] bytes = file.getBytes();
 					Path pathimg = Paths.get(upload.getAbsolutePath() + fileName);
					Files.write(pathimg, bytes);
					news.setImage("/images/upload/"+fileName);

			/*redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");*/

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try{

			news.setBrowses(0);
			news.setComments(0);
			news.setLikes(0);
			news.setScore(0);
			news.setState(0);
			news.setAddDate(new Date());
			news.setUpdateDate(new Date());
			if(news.getId()!=0){
				newsService.updateNews(news);
			} else {
				newsService.addNews(news);
			}
		}catch (Exception e){
			newsService.addNews(news);
			e.printStackTrace();
		}


		return "redirect:newsManage_0_0_0";
	}
	
	@ResponseBody
	@PostMapping("/admin/newsEditState")
	public ResObject<Object> newsEditState(News news) {
		News newsO = newsService.findnewsByid(news.getId());
		
		if(news.getState()==0){
			news.setState(newsO.getState());
		}
		if(news.getCommendState()==0){
			news.setCommendState(newsO.getCommendState());
		}
		if(news.getBrowses()==0){
			news.setBrowses(newsO.getBrowses());
		}
		if(news.getLikes()==0){
			news.setLikes(newsO.getLikes());
		}
		if(news.getComments()==0){
			news.setComments(newsO.getComments());
		}
		if(news.getScore()==0){
			news.setScore(newsO.getScore());
		}
		newsService.updateNews(news);
		ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
		return object;
	}
	
}
