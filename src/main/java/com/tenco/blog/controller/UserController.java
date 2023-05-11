package com.tenco.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tenco.blog.model.User;
import com.tenco.blog.service.UserService;

@Controller
public class UserController {

	@Autowired
	private HttpSession session;
	@Autowired
	private UserService userService;

	// 로그인 페이지

	@GetMapping("/auth/loginPage")
	public String loginPage() {

		return "/user/login_form";
	}

	// 로그인 처리
	// 회원가입 페이지
	@GetMapping("/auth/joinPage")
	public String joinPage() {

		return "/user/join_form";
	}

	// 회원가입 처리
	@PostMapping("/auth/userProc")
	public String saveUser(User user) {
		// 유효성검사 생략
		int result = userService.createUser(user);

		return "redirect:/auth/loginPage";
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout() {

		session.invalidate();

		return "redirect:/";
	}

}
