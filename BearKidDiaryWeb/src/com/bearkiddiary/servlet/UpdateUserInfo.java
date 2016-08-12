package com.bearkiddiary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.Gson;

/**
 * Servlet implementation class UpdateUserInfo
 */
@WebServlet(name = "UpdateUserInfo", urlPatterns = "/updateinfo.jsp")
public class UpdateUserInfo extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private Result<User> result;
    private User user;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		user = new User();
		
		String Uphone = request.getParameter(User.PHONE);
		String Uname = request.getParameter(User.NAME);
		String Uarea = request.getParameter(User.AREA);
		String Uemail = request.getParameter(User.EMAIL);
		
		user.setUphone(Uphone);
		
		int updateId = -1;
		if(Uname != null){
			//解码
			String name = new String(Uname.getBytes("iso-8859-1"), "UTF-8");
			name = URLDecoder.decode(name, "UTF-8");
			updateId = service.update(Uphone, User.NAME, name);
		}else if(Uarea != null){
			String area = new String(Uarea.getBytes("iso-8859-1"), "UTF-8");
			area = URLDecoder.decode(area, "UTF-8");
			updateId = service.update(Uphone, User.AREA, area);
		}else if(Uemail != null){
			Uemail = new String(Uemail.getBytes("iso-8859-1"), "UTF-8");
			Uemail = URLDecoder.decode(Uemail, "UTF-8");
			updateId = service.update(Uphone, User.EMAIL, Uemail);
		}else {
			System.out.println("没有任何数据！");
		}
		
		if(updateId > 0){
			result = new Result<User>();
			result.setResultCode(0);
			result.setResultMessage("更新数据成功！");
			result.setData(user);
		}else {
			result = new Result<User>();
			result.setResultCode(1);
			result.setResultMessage("更新数据失败！");
			result.setData(user);
		}
		out.write(gson.toJson(result));
		System.out.println(gson.toJson(result));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
