package com.springboot.yhkj.admin.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.yhkj.admin.model.NewsCategory;
import com.springboot.yhkj.admin.model.ResObject;
import com.springboot.yhkj.admin.service.NewsCategoryService;
import com.springboot.yhkj.admin.util.Constant;
import com.springboot.yhkj.admin.util.PageUtil;

@Controller
public class NewsCategoryController {

    @Resource
    private NewsCategoryService newsCategoryService;
    //文章分类列表
    @RequestMapping("/admin/newsCategoryManage_{pageCurrent}_{pageSize}_{pageCount}")
    public String newsCategoryManage(NewsCategory newsCategory,@PathVariable Integer pageCurrent,@PathVariable Integer pageSize,@PathVariable Integer pageCount, Model model) {
        //判断
        if(pageSize == 0) pageSize = 10;
        if(pageCurrent == 0) pageCurrent = 1;
        //int rows = newsCategoryService.count(newsCategory);
        //if(pageCount == 0) pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;

        //查询
        //newsCategory.setStart((pageCurrent - 1)*pageSize);
        //newsCategory.setEnd(pageSize);
        List<NewsCategory> list = newsCategoryService.findAll();

        //输出
        model.addAttribute("list", list);
        String pageHTML = PageUtil.getPageContent("newsCategoryManage_{pageCurrent}_{pageSize}_{pageCount}?name="+newsCategory.getName(), pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML",pageHTML);
        model.addAttribute("newsCategory",newsCategory);
        return "news/newsCategoryManage";
    }


    @GetMapping("/admin/newsCategoryEdit")
    public String newsCategoryEditGet(Model model,NewsCategory newsCategory) {
        if(newsCategory.getId()!=0){
            NewsCategory newsCategoryT = newsCategoryService.findById(newsCategory);
            model.addAttribute("newsCategory",newsCategoryT);
        }
        return "news/newsCategoryEdit";
    }

    //文章分类新增、修改提交

    @PostMapping("/admin/newsCategoryEdit")
    public String newsCategoryEditPost(Model model,NewsCategory newsCategory, @RequestParam MultipartFile[] imageFile,HttpSession httpSession) {
        for (MultipartFile file : imageFile) {
            if (file.isEmpty()) {
                System.out.println("文件未上传");
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = new Date();
                String strDate = sdf.format(date);
                String fileName = strDate + file.getOriginalFilename().substring(
                        file.getOriginalFilename().indexOf("."),
                        file.getOriginalFilename().length());
                String realPath = httpSession.getServletContext().getRealPath("/userfiles");
                System.out.println("realPath : "+realPath);
               /* try {
                    FileUtils.copyInputStreamToFile(file.getInputStream(),new File(realPath, fileName));
                    newsCategory.setImage("/userfiles/"+fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                File upload = new File(realPath);
                if(!upload.exists()) upload.mkdirs();

                try {
                    // Get the file and save it somewhere
                    byte[] bytes = file.getBytes();
                    Path pathimg = Paths.get(realPath + fileName);
                    Files.write(pathimg, bytes);

			/*redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");*/

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(newsCategory.getId()!=0){
            newsCategoryService.updateNewsCategory(newsCategory);
        } else {
            newsCategoryService.addNewsCategory(newsCategory);
        }
        return "redirect:newsCategoryManage_0_0_0";
    }

    @ResponseBody
    @PostMapping("/admin/newsCategoryEditState")
    public ResObject<Object> newsCategoryEditState(NewsCategory newsCategory) {
        newsCategoryService.updateState(newsCategory);
        ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
        return object;
    }
	/*@Autowired
	private NewsCategoryService newsCategoryService;
	
	//文章分类列表
	@RequestMapping("/admin/newsCategoryManage_{pageCurrent}_{pageSize}_{pageCount}")
	public String newsCategoryManage(NewsCategory newsCategory,@PathVariable Integer pageCurrent,@PathVariable Integer pageSize,@PathVariable Integer pageCount, Model model) {
		//判断
		if(pageSize == 0) pageSize = 10;
		if(pageCurrent == 0) pageCurrent = 1;
		int rows = newsCategoryService.count(newsCategory);
		if(pageCount == 0) pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;
		
		//查询
		newsCategory.setStart((pageCurrent - 1)*pageSize);
		newsCategory.setEnd(pageSize);
		List<NewsCategory> list = newsCategoryService.list(newsCategory);
		
		//输出
		model.addAttribute("list", list);
		String pageHTML = PageUtil.getPageContent("newsCategoryManage_{pageCurrent}_{pageSize}_{pageCount}?name="+newsCategory.getName(), pageCurrent, pageSize, pageCount);
		model.addAttribute("pageHTML",pageHTML);
		model.addAttribute("newsCategory",newsCategory);
		return "news/newsCategoryManage";
	}
	

	@GetMapping("/admin/newsCategoryEdit")
	public String newsCategoryEditGet(Model model,NewsCategory newsCategory) {
		if(newsCategory.getId()!=0){
			NewsCategory newsCategoryT = newsCategoryService.findById(newsCategory);
			model.addAttribute("newsCategory",newsCategoryT);
		}
		return "news/newsCategoryEdit";
	}
	
	//文章分类新增、修改提交

	@PostMapping("/admin/newsCategoryEdit")
	public String newsCategoryEditPost(Model model,NewsCategory newsCategory, @RequestParam MultipartFile[] imageFile,HttpSession httpSession) {
		for (MultipartFile file : imageFile) {
			if (file.isEmpty()) {
				System.out.println("文件未上传");
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date = new Date();
				String strDate = sdf.format(date);
				String fileName = strDate + file.getOriginalFilename().substring(
								file.getOriginalFilename().indexOf("."),
								file.getOriginalFilename().length());
				String realPath = httpSession.getServletContext().getRealPath("/userfiles");
				System.out.println("realPath : "+realPath);
				try {
					FileUtils.copyInputStreamToFile(file.getInputStream(),new File(realPath, fileName));
					newsCategory.setImage("/userfiles/"+fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(newsCategory.getId()!=0){
			newsCategoryService.update(newsCategory);
		} else {
			newsCategoryService.insert(newsCategory);
		}
		return "redirect:newsCategoryManage_0_0_0";
	}
	
	@ResponseBody
	@PostMapping("/admin/newsCategoryEditState")
	public ResObject<Object> newsCategoryEditState(NewsCategory newsCategory) {
		newsCategoryService.updateState(newsCategory);
		ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
		return object;
	}*/
	
}
