package com.bearkiddiary.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.User;
import com.bearkiddiary.service.Service;
import com.bearkiddiary.utils.ServiceBean;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/user/regist")
public class Register extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		User user = new User();
		boolean isSuccess = false;
		user.setUphone(request.getParameter("Uphone"));
		user.setUpsw(request.getParameter("Upsw"));
		if (user.getUphone() == null || user.getUpsw() == null) {
			isSuccess = false;
		} else {
			isSuccess = service.Register(user);
		}
		System.out.println(isSuccess);
		out.println(isSuccess);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
