package com.capgemini.bankapp2.Exception;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(value=UserNotFoundException.class)
			public String handleError(HttpServletRequest request,UserNotFoundException exception) {
		request.setAttribute("error", exception.getMessage());
		return "err";
	}

	
	
	@ExceptionHandler(value=InvalidDetailsException.class)
	public String handleError1(HttpServletRequest request,InvalidDetailsException exception) {
request.setAttribute("error", exception.getMessage());
return "err";
}
}
