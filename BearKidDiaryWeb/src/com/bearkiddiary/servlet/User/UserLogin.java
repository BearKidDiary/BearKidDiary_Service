package com.bearkiddiary.servlet.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Observable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.service.Service;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.Gson;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/user/login")
public class UserLogin extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Result<User> result;
	
	private PrintWriter out = null;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		out = response.getWriter();
		
		String Uphone = request.getParameter("Uphone");
		String Upsw = request.getParameter("Upsw");
		System.out.println(Uphone);
		Login(Uphone, Upsw, out);
	}

	public void Login(String Uphone, String Upsw, PrintWriter writer) {
		result = new Result<>();
		User user = new User();
		// TODO Auto-generated method stub
		if (service.Login(Uphone, Upsw)) {
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMessage("µÇÂ¼³É¹¦£¡");
		} else {
			result.setResultCode(ResultCode.ERROR_LOGIN);
			result.setResultMessage("µÇÂ¼Ê§°Ü£¬ÓÃ»§Ãû»òÃÜÂë´íÎó£¡");
		}

		System.out.println(gson.toJson(result));
		writer.write(gson.toJson(result));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		if(out != null){
			out.close();
		}
	}
}
