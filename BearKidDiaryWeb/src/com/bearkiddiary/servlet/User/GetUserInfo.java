package com.bearkiddiary.servlet.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

/**
 * Servlet implementation class GetUserInfo
 */
@WebServlet("/user/info")
public class GetUserInfo extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Result<User> result;
	private PrintWriter out;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		result = new Result();
		out = response.getWriter();
		String Uphone = request.getParameter(User.PHONE);
		
		User user = service.getUserInfo(Uphone);
		if(user == null){
			result.setResultCode(ResultCode.ERROR_NO_USER);
			result.setResultMessage("不存在该用户!");
		}else{
			user.setUpsw(null);
			result.setData(user);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMessage("获取个人信息成功！");
		}
		out.write(gson.toJson(result));
	}

}
