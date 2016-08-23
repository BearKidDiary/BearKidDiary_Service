package com.bearkiddiary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.dao.FamilyDao;
import com.bearkiddiary.dao.impl.FamilyDaoImpl;
import com.bearkiddiary.utils.ResultCode;
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.Gson;

/**
 * Servlet implementation class CreateFamilyServlet
 */
@WebServlet("/family/create")
public class FamilyCreate extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result<User> result = new Result<>();
		PrintWriter out = response.getWriter();

		String Uphone = request.getParameter("Uphone");
		String Fname = request.getParameter("Fname");
		System.out.println("phone: " + Uphone + " name: " + Fname);
		if (Uphone == null) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		int code = service.createFamily(Uphone, Fname);
		result.setResultCode(code);
		if (code == ResultCode.ERROR_NO_USER) {
			result.setResultMessage("不存在对应用户");
		} else if (code == ResultCode.ERROR_EXIST_FAMILY) {
			result.setResultMessage("该用户已经有家庭了");
		} else {
			result.setResultMessage("创建成功");
		}
		System.out.println(gson.toJson(result));
		out.write(gson.toJson(result));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
