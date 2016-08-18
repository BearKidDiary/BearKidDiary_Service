package com.bearkiddiary.servlet.User;

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
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ParameterDecode;
import com.bearkiddiary.utils.ResultCode;
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.Gson;

/**
 * Servlet implementation class UpdateUserInfo
 */
@WebServlet(name = "UpdateUserInfo", urlPatterns = "/user/updateinfo")
public class UserInfoUpdate extends BaseServlet {
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
		
		int update = 0;
		if(Uname != null){
			//解码
			String name = ParameterDecode.decode(Uname);
			update = service.updateUser(Uphone, User.NAME, name);
		}else if(Uarea != null){
			String area = ParameterDecode.decode(Uarea);
			update = service.updateUser(Uphone, User.AREA, area);
		}else if(Uemail != null){
			Uemail = ParameterDecode.decode(Uemail);
			update = service.updateUser(Uphone, User.EMAIL, Uemail);
		}else {
			System.out.println("没有任何数据！");
		}
		
		if(update > 0){
			result = new Result<User>();
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMessage("更新数据成功！");
		}else {
			result = new Result<User>();
			result.setResultCode(ResultCode.ERROR_COMMIT);
			result.setResultMessage("更新数据失败！");
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
