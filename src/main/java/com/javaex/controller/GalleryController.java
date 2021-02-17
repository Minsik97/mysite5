package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	//전체 리스트 출력
	public String list(Model model) {
		System.out.println("[GalleryController.list()]");
		
		List<GalleryVo> list = galleryService.list();
		
		model.addAttribute("list", list);
		
		return "/gallery/list";
	}
	
	//파일 업로드
	@RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String upload(@ModelAttribute GalleryVo galleryVo, @RequestParam("file")MultipartFile file, HttpSession session) {
		System.out.println("[GalleryController.upload()]");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		int userNo = authUser.getNo();
		
		
		galleryVo.setUser_no(userNo);
		
		galleryService.upLoad(file, galleryVo);
		
		return "redirect:/gallery/list";
	}
	
	//파일 읽기
	@ResponseBody
	@RequestMapping (value = "/read",method = {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo read(@RequestParam("no") int no) {
		System.out.println("[GalleryController.read()]");
		
		GalleryVo galleryVo = galleryService.read(no);
		
		return galleryVo;
	}
	
	//파일 삭제하기
	@ResponseBody
	@RequestMapping (value = "/delete",method = {RequestMethod.GET, RequestMethod.POST})
	public int delete(@RequestParam("no") int no) {
		System.out.println("[GalleryController.delete()]");
		
		int count = galleryService.delete(no);
		
		return count;
	}
	
}
