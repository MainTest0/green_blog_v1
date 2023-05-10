package com.tenco.blog.handler;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tenco.blog.dto.ApiErrorResponse;
import com.tenco.blog.dto.ExceptionFieldMessage;

@RestControllerAdvice // IoC
public class RestExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public void exception(Exception e) {
		
		System.out.println("======================");
		
		System.out.println(e.getClass().getName());// 예외발생한 클래스 이름
		System.out.println(e.getMessage());// 예외 메세지 출력
		
		System.out.println("======================");
	}
	
	@ExceptionHandler(value = IllegalIdentifierException.class)
	public String illegalArgumentException(IllegalArgumentException e) {
		
		
		return "<h1>" + e.getMessage() + "</h1>";
	}
	
//	@ExceptionHandler(value = MethodArgumentNotValidException.class)
//	public List<ExceptionFieldMessage> methodArgumentNotValidException(MethodArgumentNotValidException e) {
//		
//		List<ExceptionFieldMessage> errorList = new ArrayList<>();
//		
//		e.getBindingResult().getAllErrors().forEach( action -> {
//			action.getArguments();
//			FieldError fieldError = (FieldError)action;
//			String fieldName = fieldError.getField();
//			String message = fieldError.getDefaultMessage();
//			
//			ExceptionFieldMessage exceptionFieldMessage = 
//					ExceptionFieldMessage.builder().field(fieldName).Message(message).build();
//
//			errorList.add(exceptionFieldMessage);
//			
//		});
//		
//		System.out.println("MethodArgumentNotValidException 예외 발생");
//		System.out.println("여기타");
//		
//		return errorList;
//	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ApiErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
		
		List<ExceptionFieldMessage> errorList = new ArrayList<>();
		
		e.getBindingResult().getAllErrors().forEach( action -> {
			action.getArguments();
			FieldError fieldError = (FieldError)action;
			String fieldName = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			
			ExceptionFieldMessage exceptionFieldMessage = 
					ExceptionFieldMessage.builder().field(fieldName).Message(message).build();

			errorList.add(exceptionFieldMessage);
			
		});
		
		return ApiErrorResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value()).code("-1").resultCode("fail")
				.message("잘못된 요청입니다.").exceptionFieldMessages(errorList).build();
	}
	
}
