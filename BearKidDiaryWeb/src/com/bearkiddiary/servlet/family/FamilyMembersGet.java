package com.bearkiddiary.servlet.family;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetFamilyMembersServlet
 */
@WebServlet("/family/members")
public class FamilyMembersGet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Result<Set<User>> result = new Result<>();

		String Uphone = request.getParameter("Uphone");
		String SFid = request.getParameter("Fid");
		String Range = request.getParameter("Range");

		if (Uphone == null && SFid == null) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		Long Fid = SFid == null ? null : Long.valueOf(SFid);

		Set<User> members = null;
		if (Range == null)
			Range = "ALL";
		if (Range.equals("Attend"))
			members = service.getFamilyMembers(Uphone, Fid);
		else if (Range.equals("Create")) {
			members = new HashSet<>();
			User creator = service.getFamilyCreator(Uphone, Fid);
			members.add(creator);
		} else// Range=="ALL"
			members = service.getFamilyMembersAndCreator(Uphone, Fid);

		result.setResultCode(ResultCode.SUCCESS);
		result.setData(members);
		out.write(gson.toJson(result));
		System.out.println(gson.toJson(result));
		out.close();
	}
}
