package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/guestbook")

public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("/addList")
	public String list(Model model) {
		System.out.println("controller llist");
		
		List<GuestbookVo> gbList = guestbookService.gbList();
		
		model.addAttribute("gbList", gbList);
		
		return "/guestbook/addList";
	}
	
	@RequestMapping("/add")
	public String insert(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("controller 등록");
		
		guestbookService.insert(guestbookVo);
			
		return "redirect:/guestbook/addList";
	}
	
	@RequestMapping("/deleteForm")
	public String deleteForm() {
		System.out.println("deleteForm");
		
		return "/guestbook/deleteForm";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("delete");
		
		int ex = guestbookService.delete(guestbookVo);
		
		if(ex == 1) {//삭제 성공
			System.out.println("삭제 성공");
			return "redirect:/guestbook/addList";
		}else {
			System.out.println("삭제 실패");
			return "guestbook/passwordcheck";
		}
	}
	
	// ajaxList
	@RequestMapping (value = "ajaxList", method = {RequestMethod.GET, RequestMethod.POST})
	public String ajaxList() {
		System.out.println("controller ajaxList");
		
		return "guestbook/ajaxList";
	}
	
	
	
	
	
	
}
