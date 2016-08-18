package com.bearkiddiary.servlet;

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
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.Gson;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/user/login")
public class Login extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Result<User> result;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		// Map<String, String> params=((Object) request).getParams();
		// Iterator it = params.keySet().iterator();
		// while(it.hasNext()){
		// String paramName = (String) it.next();
		// String paramValue = request.getParameter(paramName);
		// //处理你得到的参数名与值
		// System.out.println(paramName+"="+paramValue);
		// }

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
			result.setResultCode(0);
			result.setResultMessage("登录成功！");
			user.setUphone(Uphone);
			result.setData(user);
		} else {
			result.setResultCode(1);
			result.setResultMessage("登录失败，用户名或密码错误！");
			result.setData(user);
		}

		System.out.println(gson.toJson(result));
		writer.write(gson.toJson(result));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
