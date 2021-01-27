package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")

public class UserController {
	
	//필드
	@Autowired
	private UserDao userDao;
	
	//생성자
	
	//메소드g/s
	
	//일반 메소드
	//회원가입 폼
	@RequestMapping(value = "/joinForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("/user/joinForm");
		
		
		return "user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value = "/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("/user/join");
		System.out.println(userVo);
		
		int count = userDao.insert(userVo);
		
		return "user/joinOk";
	}
	
	//로그인 폼
	@RequestMapping(value = "/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("/user/loginForm");
		
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/login");
		System.out.println(userVo.toString());
		
		UserVo authUser = userDao.selectUser(userVo);
		
		if(authUser == null) {
			System.out.println("로그인 실패");
			return "redirect:/user/loginForm?result=fail";
		} else {
			System.out.println("로그인 성공");
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		}

	}
	
	//로그아웃
	@RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("logout");
		
		session.removeAttribute("authUser");
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	//수정 폼
	@RequestMapping(value = "/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, HttpSession session) {
		System.out.println("modifyForm");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		int no = authUser.getNo();
		
		UserVo userVo = userDao.getUser(no);
		
		model.addAttribute("userVo", userVo);
		
		return "user/modifyForm";
	}
	
	@RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("modify");
		System.out.println(userVo);
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		userVo.setNo(authUser.getNo());
		
		userDao.modify(userVo);
		
		authUser.setName(userVo.getName());
		
		return "redirect:/";
	}
	
	
}
