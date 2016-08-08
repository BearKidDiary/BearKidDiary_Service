package com.bearkiddiary.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.dao.FamilyDao;
import com.bearkiddiary.dao.impl.FamilyDaoImpl;
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.Gson;

/**
 * Servlet implementation class CreateFamilyServlet
 */
@WebServlet("/family/create")
public class CreateFamilyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private Result<User> result;
	private Gson gson = new Gson();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		service = ServiceBean.getService(getServletContext());

		String Uphone = request.getParameter("Uphone");
		String Fname = request.getParameter("Fname");
		System.out.println(Uphone + " , " + Fname);

		int code = service.createFamily(Uphone, Fname);
		result.setResultCode(code);
		if (code == FamilyDao.ERROR_NO_USER) {
			result.setResultMessage("�����ڶ�Ӧ�û�");
		} else if (code == FamilyDao.ERROR_ALREADY_HAVE_FAMILY) {
			result.setResultMessage("���û��Ѿ��м�ͥ��");
		} else {
			result.setResultMessage("�����ɹ�");
		}
		System.out.println(gson.toJson(result));
		out.write(gson.toJson(result));
	}
}
