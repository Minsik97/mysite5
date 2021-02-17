package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	//리스트 (리스트 + 검색기능)
	@RequestMapping (value = "/list2", method = {RequestMethod.GET, RequestMethod.POST})
	public String list2(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword, Model model) {	
		System.out.println("controller list2");
		
		List<BoardVo> boardList =  boardService.getBoardList2(keyword);
		model.addAttribute("list", boardList);
		
		return "/board/list2";
	}
	
	//리스트 (리스트 + 검색기능 + 페이징)
	@RequestMapping (value = "/list3", method = {RequestMethod.GET, RequestMethod.POST})
	public String list3(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			                     @RequestParam(value = "crtPage", required = false, defaultValue = "1") int crtPage,
			                     Model model) {	
		System.out.println("controller list3");
		System.out.println("keyword = " + keyword);
		System.out.println("crtPage = " + crtPage);
		
		Map<String, Object> pMap =  boardService.getBoardList3(keyword, crtPage);
		System.out.println(pMap);
		
		model.addAttribute("pMap", pMap);
		
		return "/board/list3";
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
