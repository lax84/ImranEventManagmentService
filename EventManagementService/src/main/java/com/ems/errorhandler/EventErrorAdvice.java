package com.ems.errorhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ems.exceptions.CityNameNotFoundException;

@ControllerAdvice //any controller the error occurs the dispatcher servlet redirects to this class
public class EventErrorAdvice {
	
	@ExceptionHandler(value=CityNameNotFoundException.class)
	@ResponseBody
	public String handleError(CityNameNotFoundException ex) {
		
		return ex.getMessage();
	}

}
