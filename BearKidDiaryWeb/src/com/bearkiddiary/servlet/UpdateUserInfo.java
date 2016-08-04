package com.bearkiddiary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.User;
import com.bearkiddiary.utils.ServiceBean;

/**
 * Servlet implementation class UpdateUserInfo
 */
@WebServlet(name = "UpdateUserInfo", urlPatterns = "/updateinfo.jsp")
public class UpdateUserInfo extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		service = ServiceBean.getService(getServletContext());
		
		String Uphone = request.getParameter(User.PHONE);
		String Uname = request.getParameter(User.NAME);
		String Uarea = request.getParameter(User.AREA);
		String Uemail = request.getParameter(User.EMAIL);
		
		int result = -1;
		if(Uname != null){
			//解码
			String name = new String( Uname.getBytes("iso-8859-1"), "UTF-8");
			name = URLDecoder.decode(name, "UTF-8");
			result = service.update(Uphone, User.NAME, name);
		}else if(Uarea != null){
			String area = new String(Uarea.getBytes("iso-8859-1"), "UTF-8");
			area = URLDecoder.decode(area, "UTF-8");
			result = service.update(Uphone, User.AREA, area);
		}else if(Uemail != null){
			result = service.update(Uphone, User.EMAIL, Uemail);
		}else {
			System.out.println("没有任何数据！");
		}
		
		System.out.println(result + "");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
