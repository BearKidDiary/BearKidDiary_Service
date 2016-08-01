package com.bearkiddiary.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bearkiddiary.bean.User;
import com.bearkiddiary.service.Service;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Service service;
    
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		
		service = (Service) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("Service");
		User user = new User();
		user.setName("hhh");
		user.setPassword("123");
		boolean isSuccess = service.Login(user);
		System.out.println(isSuccess);
		out.println(isSuccess);
	}
}
