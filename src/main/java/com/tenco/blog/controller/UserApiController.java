package com.tenco.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tenco.blog.dto.ResponseDto;
import com.tenco.blog.model.User;
import com.tenco.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;

	@PostMapping("/api/user/login")
	public ResponseDto<?> loginUser(@RequestBody User user){
		
		// 유효성 검사
		
		// 서비스 호출해서 결과값 받기
		User principal = userService.readUser(user);
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
	// 전통적인 로그인 방식은 사용하지 않음 !!
	// 시큐리티가 알아서 로그인 처리를 해준다.
	
}
