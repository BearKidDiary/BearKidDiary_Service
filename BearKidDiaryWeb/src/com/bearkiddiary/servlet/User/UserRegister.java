package com.bearkiddiary.servlet.User;

import java.io.IOException;
import java.io.PrintWriter;

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
		boolean isSuccess = false;
		
		String Uphone = request.getParameter(User.PHONE);
		String Upsw = request.getParameter(User.PSW);
		
		user.setUphone(Uphone);
		user.setUpsw(Upsw);
		
		if (user.getUphone() == null || user.getUpsw() == null) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("�������������");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		isSuccess = service.Register(user);

		// ����һ����ͥ
		int code = ResultCode.ERROR_NO_RESULT;
		if (isSuccess)
			code = service.createFamily(user.getUphone(), user.getUphone() + "�ļ�ͥ");
		result.setResultCode(code);
		
		user = new User();
		if (code == ResultCode.SUCCESS) {
			result.setResultMessage("ע��ɹ�");
		}
		if (code == ResultCode.ERROR_NO_RESULT) {
			result.setResultMessage("ע��ʧ��");
		}
		out.println(gson.toJson(result));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
