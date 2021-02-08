package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")

public class UserController {
	
	//필드
	@Autowired
	private UserService userService;
	
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
		System.out.println(userVo.toString());
		
		userService.join(userVo);
		
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
		
		UserVo authUser = userService.login(userVo);

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
		
		//세션에서 no값 가져오기
		int no = ((UserVo) session.getAttribute("authUser")).getNo();
		
		//세션값이 없으면 --> 로그인폼으로 리다이렉트
		
		//내가 짠 코드 
		//UserVo authUser = (UserVo)session.getAttribute("authUser");
		//int no = authUser.getNo();
		
		// 회원정보 가져오기
		UserVo userVo = userService.modifyForm(no);
		
		// jsp에 데이터 보내기
		model.addAttribute("userVo", userVo);
		
		return "user/modifyForm";
	}
	
	@RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("modify");
		System.out.println(userVo);
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		userVo.setNo(authUser.getNo());
		
		userService.modify(userVo);
		
		authUser.setName(userVo.getName());
		
		return "redirect:/";
	}
	
	//회원가입 - 아이디 체크
	@ResponseBody
	@RequestMapping (value = "/idcheck", method = {RequestMethod.GET, RequestMethod.POST})
	public String idcheck(@RequestParam("id") String id, @RequestParam("password") String password) {
		//패스워드는 테스트용으로 추가함
		
		System.out.println("idcheck");
		System.out.println("idcheck = " + id);
		System.out.println("password = " + password);
		
		String result = userService.idcheck(id);
		
		System.out.println(result);
		
		//return  "redirect:/user/joinForm?result=" + result;
		return  result;  //@ResponseBody --> response의 body영역에 data만 보낸다.(return 값)
	}
	
	
	
}
