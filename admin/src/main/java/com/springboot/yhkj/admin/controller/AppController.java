package com.springboot.yhkj.admin.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.yhkj.admin.model.ResObject;
import com.springboot.yhkj.admin.model.User;
import com.springboot.yhkj.admin.util.Constant;

@RestController
public class AppController {
	
	/*
	 * GET请求
	 */
	@GetMapping("/app/login")
	public ResObject<Object> login(User user) {
		System.out.println("UserName :" + user.getUserName());
		System.out.println("Password" + user.getPassword());
		ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, user, null);
		return object;
	}
	
	/*
	 * POST请求
	 */
	@PostMapping("/app/register")
	public ResObject<Object> register(User user) {
		System.out.println("UserName :" + user.getUserName());
		System.out.println("Password" + user.getPassword());
		ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, user, null);
		return object;
	}
	
	/*
	 * 单文件上传
	 */
	@PostMapping("/app/upload")
	public ResObject<Object> upload(User user,@RequestParam MultipartFile imageFile,HttpSession httpSession) {
		System.out.println("UserName :" + user.getUserName());
		System.out.println("Password" + user.getPassword());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String strDate = sdf.format(date);
		String fileName = strDate + imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().indexOf("."),imageFile.getOriginalFilename().length());
		String realPath = httpSession.getServletContext().getRealPath("/userfiles");
		System.out.println("realPath : "+realPath);
		/*try {

			FileUtils.copyInputStreamToFile(imageFile.getInputStream(),new File(realPath, fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
*/


		File upload = new File(realPath);
		if(!upload.exists()) upload.mkdirs();
		try {
			// Get the file and save it somewhere
			byte[] bytes = imageFile.getBytes();
			Path pathimg = Paths.get(realPath + fileName);
			Files.write(pathimg, bytes);

			/*redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");*/

		} catch (IOException e) {
			e.printStackTrace();
		}
		ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, user, null);
		return object;
	}
	
	/*
	 * 多文件上传
	 */
	@PostMapping("/app/uploads")
	public ResObject<Object> uploads(User user, @RequestParam MultipartFile[] imageFile,HttpSession httpSession) {
		System.out.println("UserName :" + user.getUserName());
		System.out.println("Password" + user.getPassword());
		for (MultipartFile file : imageFile) {
			if (file.isEmpty()) {
				System.out.println("文件未上传");
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				Random random = new Random();
				Date date = new Date();
				String strDate = sdf.format(date);
				String fileName = strDate  + random.nextInt(1000) + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."),file.getOriginalFilename().length());
				String realPath = httpSession.getServletContext().getRealPath("/userfiles");
				System.out.println("realPath : "+realPath);
				System.out.println("fileName : "+fileName);
				/*try {
					FileUtils.copyInputStreamToFile(file.getInputStream(),new File(realPath, fileName));
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
		ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, user, null);
		return object;
	}

}
