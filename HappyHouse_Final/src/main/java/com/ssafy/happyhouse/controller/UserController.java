package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.UserException;
import com.ssafy.happyhouse.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;
	
	@ExceptionHandler
	public ModelAndView longinError(UserException ex) {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("msg", ex.getMessage());
		return mav;
	}
	@ExceptionHandler
	public ModelAndView handler(Exception ex) {
		ModelAndView mav = new ModelAndView("error/error");
		mav.addObject("msg", "회원 정보 처리 중 오류 발생");
		return mav;
	}
	@PostMapping("/login")
	public String login(String userId, String userPw, HttpSession session) {
		session.setAttribute("userInfo", service.login(userId, userPw));
		return "index";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	@GetMapping("/search")
	public String search(String userId, Model model) {
		model.addAttribute("user", service.search(userId));
		return "user/search";
	}
	
	@PostMapping("/find")
	public String find(String userId,String userEmail,String userName,Model model) {
		String pass=service.findPass(userId,userEmail, userName);
		System.out.println(pass);
		model.addAttribute("msg", "비밀번호 : "+pass);
		return "index";
	}
	@PostMapping("/insert")
	public String insert(UserDto user,Model model) {
		service.insert(user);
		model.addAttribute("msg", "회원가입에 성공");
		return "index";
	}
	@PostMapping("/update")
	public String update(UserDto user) {
		service.update(user);
		return "redirect:list";
	}
	@GetMapping("/list")
	public String list(Model model) {
		List<UserDto> list =service.searchAll();
		model.addAttribute("users", list);
		return "user/list";
	}
	@PostMapping("/delete")
	public String delete(UserDto user) {
		service.delete(user.getUserId());
		return "redirect:list";
	}

}
