package com.ems.exceptions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class AccessException implements AccessDeniedHandler//Interface
{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
	response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	
	response.setHeader("WWW-Authenticate","Basic Authentication");
	
	PrintWriter out=response.getWriter();
	
	out.println("Response Status 403"+accessDeniedException.getMessage());
		
	}

}
