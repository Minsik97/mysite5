/*
package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/rboard")	

public class RboardController {
	
	@Autowired
	private RboardService rboardService;
	
	//리스트
	@RequestMapping (value="/list")
	public String list(Model model) {
		System.out.println("controller list");
		
		List<RboardVo> list = rboardService.list();
		System.out.println(list);
		
		model.addAttribute("list", list);
		
		return "/rboard/list";
	}
	
	//글쓰기 폼
	@RequestMapping(value = "/writeForm")
	public String writeForm() {
		System.out.println("controller writeForm");
		
		return "/rboard/writeForm";
	}
	
	// 글쓰기
	@RequestMapping (value = "/write")
	public String write (RboardVo rboardVo, HttpSession session) {
		System.out.println("controller write");
		System.out.println(rboardVo);
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		int userNo = authUser.getNo();
		
		rboardVo.setUser_no(userNo);
		
		rboardService.write(rboardVo);
		
		return  "redirect:/rboard/list";
	}
	
	@RequestMapping(value = "/comment")
	
	
}
*/