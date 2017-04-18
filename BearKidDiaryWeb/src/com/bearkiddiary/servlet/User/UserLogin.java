package com.bearkiddiary.servlet.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.easemob.TokenUtil;
import com.bearkiddiary.service.Service;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ImageUtil;
import com.bearkiddiary.utils.ResultCode;
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.Gson;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/user/login")
public class UserLogin extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Result<Map<String, List<Organization>>> result;
	private Map<String, List<Organization>> list_map;
	
	private PrintWriter out = null;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		out = response.getWriter();
		
		String Uphone = request.getParameter("Uphone");
		String Upsw = request.getParameter("Upsw");
//		System.out.println("" + new Date().getTime());
		Login(Uphone, Upsw, out);
	}

	public void Login(String Uphone, String Upsw, PrintWriter writer) {
		result = new Result<>();
		// TODO Auto-generated method stub
		if (service.Login(Uphone, Upsw)) {
			list_map = service.getUserOrganizations(Uphone);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMessage("登录成功！");
			result.setData(list_map);
		} else {
			result.setResultCode(ResultCode.ERROR_LOGIN);
			result.setResultMessage("登录失败，用户名或密码错误！");
			result.setData(list_map);
		}

//		System.out.println(gson.toJson(result));
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
