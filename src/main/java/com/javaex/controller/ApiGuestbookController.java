package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/api/guestbook")
public class ApiGuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	//전체리스트 가져오기(ajax)
	@ResponseBody
	@RequestMapping (value = "/list")
	public List<GuestbookVo> list() {
		System.out.println("[ApiGuestbookController] list");
		
		return guestbookService.gbList();
	}
	
	
	
	//글작성(ajax)
	//리스폰스바디에 데이터를 담는다(리턴형식을 json으로 바꾸기 위해 사용)
	@ResponseBody
	@RequestMapping(value = "/write")
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[ApiGuestbookController] write");
		System.out.println(guestbookVo);
		
		//입력된 vo값을 전달하고 저장된 vo를 받아야 한다.
		GuestbookVo vo = guestbookService.writeResultVo(guestbookVo);
		
		return vo;
	}
	
	//글작성2(ajax-json)
		//리스폰스바디에 데이터를 담는다(리턴형식을 json으로 바꾸기 위해 사용)
		@ResponseBody
		@RequestMapping(value = "/write2")
		public GuestbookVo write2(@RequestBody GuestbookVo guestbookVo) {
			System.out.println("[ApiGuestbookController] write2");
			System.out.println(guestbookVo);
			
			//입력된 vo값을 전달하고 저장된 vo를 받아야 한다.
			GuestbookVo vo = guestbookService.writeResultVo(guestbookVo);
			
			return vo;
		}
	
	
	//글삭제(ajax)
	@ResponseBody
	@RequestMapping(value="/remove")
	public int remove(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[ApiGuestbookController] remove");
		System.out.println(guestbookVo);
		
		int count = guestbookService.delete(guestbookVo);
		System.out.println(count);
		
		return count;
	}

}
