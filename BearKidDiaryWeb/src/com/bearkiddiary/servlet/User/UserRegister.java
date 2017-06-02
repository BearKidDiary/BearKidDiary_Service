package com.bearkiddiary.servlet.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.easemob.EasemobIMUsers;
import com.bearkiddiary.easemob.IMUserAPI;
import com.bearkiddiary.service.Service;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;
import com.bearkiddiary.utils.ServiceBean;

import io.swagger.client.model.RegisterUsers;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/user/regist")
public class UserRegister extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private User user;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Result<User> result = new Result<>();

		user = new User();
		Long Uid = (long) 0;
		
		String Uphone = request.getParameter(User.PHONE);
		String Upsw = request.getParameter(User.PSW);
		String Uname = request.getParameter(User.NAME);
		
		user.setUphone(Uphone);
		user.setUpsw(Upsw);
		user.setUname(Uname);
		
		if (user.getUphone() == null || user.getUpsw() == null) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}
		user.setUavatar("default.jpg");
		Uid = service.Register(user);
		
		//根据返回的Uphone进行环信注册，以保证唯一
		if(Uid > 0){
			IMUserAPI api = new EasemobIMUsers();
			io.swagger.client.model.User user = new io.swagger.client.model.User();
			user.setUsername(Uphone);
			user.setPassword(Upsw);
			
			RegisterUsers IMUser = new RegisterUsers();
			IMUser.add(user);
			Object obj = api.createNewIMUserSingle(IMUser);
			System.out.println(obj.toString());
		}
		
		// 创建一个家庭
		int code = ResultCode.ERROR_NO_RESULT;
		if (Uid > 0)
			code = service.createFamily(user.getUphone(), user.getUphone() + "的家庭");
		result.setResultCode(code);
		
		user = new User();
		if (code == ResultCode.SUCCESS) {
			result.setResultMessage("注册成功");
			result.setData(new User());
		}
		if (code == ResultCode.ERROR_NO_RESULT) {
			result.setResultMessage("注册失败");
			result.setData(new User());
		}
		out.write(gson.toJson(result));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
