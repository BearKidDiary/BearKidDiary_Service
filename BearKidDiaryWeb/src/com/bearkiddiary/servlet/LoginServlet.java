package com.bearkiddiary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.service.Service;
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.Gson;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login.jsp")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Result<User> result;
	private Gson gson = new Gson();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		service = ServiceBean.getService(getServletContext());

		String Uphone = request.getParameter("Uphone");
		String Upsw = request.getParameter("Upsw");
		Login(Uphone, Upsw, out);
	}

	public void Login(String Uphone, String Upsw, PrintWriter writer) {
		result = new Result<>();
		User user = new User();
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (service.Login(Uphone, Upsw)) {
					result.setResultCode(0);
					result.setResultMessage("登录成功！");
					user.setUphone(Uphone);
					result.setData(user);
				} else {
					result.setResultCode(1);
					result.setResultMessage("登录失败，用户名或密码错误！");
					result.setData(user);
				}
				
				writer.write(gson.toJson(result));
			}
		}).start();

	}
}
