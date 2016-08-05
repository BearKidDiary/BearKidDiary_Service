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
@WebServlet(name = "LoginServlet", urlPatterns = "/login.jsp")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Result<User> result;
	private Gson gson = new Gson();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		service = ServiceBean.getService(getServletContext());

//		Map<String, String> params=((Object) request).getParams();
//		Iterator it = params.keySet().iterator();
//		while(it.hasNext()){
//		    String paramName = (String) it.next();
//		    String paramValue = request.getParameter(paramName);
//		    //������õ��Ĳ�������ֵ
//		    System.out.println(paramName+"="+paramValue);
//		}
		
		String Uphone = request.getParameter("Uphone");
		String Upsw = request.getParameter("Upsw");
		System.out.println(Uphone);
		String user = request.getParameter("User");
		System.out.println(request.getRequestURI());
		Login(Uphone, Upsw, out);
	}

	public void Login(String Uphone, String Upsw, PrintWriter writer) {
		result = new Result<>();
		User user = new User();
		// TODO Auto-generated method stub
		if (service.Login(Uphone, Upsw)) {
			result.setResultCode(0);
			result.setResultMessage("��¼�ɹ���");
			user.setUphone(Uphone);
			result.setData(user);
		} else {
			result.setResultCode(1);
			result.setResultMessage("��¼ʧ�ܣ��û������������");
			result.setData(user);
		}

		System.out.println(gson.toJson(result));
		writer.write(gson.toJson(result));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}
