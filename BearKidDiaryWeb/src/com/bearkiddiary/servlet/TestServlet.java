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
@WebServlet(name = "TestServlet", urlPatterns = "/registtest.jsp")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Service service;
    
//	@Override
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=GBK");
//		PrintWriter out = response.getWriter();
//		service = (Service) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("service");
//		
//		User user = new User();
//		boolean isSuccess = false;
//		user.setUphone(request.getParameter("Uphone"));
//		user.setUpsw(request.getParameter("Upsw"));
//		if(user.getUphone() == null || user.getUpsw() == null){
//			isSuccess = false;
//		}else {
//			isSuccess = service.Register(user);
//		}
//		System.out.println(isSuccess);
//		out.println(isSuccess);
//	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		service = (Service) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("service");
		
		User user = new User();
		boolean isSuccess = false;
		user.setUphone(request.getParameter("Uphone"));
		user.setUpsw(request.getParameter("Upsw"));
		if(user.getUphone() == null || user.getUpsw() == null){
			isSuccess = false;
		}else {
			isSuccess = service.Register(user);
		}
		System.out.println(isSuccess);
		out.println(isSuccess);
	}
}
