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
public class CreateFamily extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result<User> result = new Result<>();
		PrintWriter out = response.getWriter();

		String Uphone = request.getParameter("Uphone");
		String Fname = request.getParameter("Fname");
		System.out.println("phone: " + Uphone + " name: " + Fname);
		if (Uphone == null || Fname == null) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("�������������");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		int code = service.createFamily(Uphone, Fname);
		result.setResultCode(code);
		if (code == ResultCode.ERROR_NO_USER) {
			result.setResultMessage("�����ڶ�Ӧ�û�");
		} else if (code == ResultCode.ERROR_ALREADY_HAVE_FAMILY) {
			result.setResultMessage("���û��Ѿ��м�ͥ��");
		} else {
			result.setResultMessage("�����ɹ�");
		}
		System.out.println(gson.toJson(result));
		out.write(gson.toJson(result));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
