package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/board")

public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//리스트
	@RequestMapping (value = "/list")
	public String list(Model model) {
		System.out.println("controller list");
		
		List<BoardVo> list = boardService.list();
		
		model.addAttribute("list", list);
		
		return "/board/list";
	}
	
	@RequestMapping(value = "/writeForm")
	public String writeForm() {
		System.out.println("controller writeForm");
		
		return "/board/writeForm";
	}
	
	@RequestMapping (value = "/write")
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("controller write");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		int userNo = authUser.getNo();
		
		boardVo.setUser_no(userNo);
		
		boardService.insert(boardVo);

		return "redirect:/board/list";
	}
	
	@RequestMapping (value = "/read")
	public String read(@RequestParam("no") int no, Model model ) {
		System.out.println("controller read");
		System.out.println("controller: " + no );
		
		boardService.hitUp(no);
		
		BoardVo boardVo = boardService.read(no);
		
		model.addAttribute("boardVo", boardVo);
		
		return "/board/read";
	}
	
	@RequestMapping(value = "/modifyForm")
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("controller modifyForm");
		
		BoardVo boardVo = boardService.read(no);
		
		model.addAttribute("boardVo", boardVo);
		
		return "/board/modifyForm";
	}
	
	@RequestMapping (value = "/modify")
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("controller modify");
		
		boardService.modify(boardVo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam("no") int no) {
		System.out.println("controller delete");
		
		boardService.delete(no);
		
		return "redirect:/board/list";
	}
	

}
