package com.bearkiddiary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.utils.ResultCode;

@WebServlet("/family/members/add")
public class FamilyMembersAdd extends BaseServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Result<Set<User>> result = new Result<>();

		String creatorPhone = request.getParameter("CreatorUphone");
		String memberPhone = request.getParameter("MemberUphone");
		String sfid = request.getParameter("Fid");
		String suid = request.getParameter("Uid");

		System.out.println(sfid + " , " + suid + " , " + creatorPhone + " , " + memberPhone);
		if ((creatorPhone == null && sfid == null) || (memberPhone == null && suid == null)) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		Long Fid = sfid == null ? null : Long.valueOf(sfid);
		Long Uid = suid == null ? null : Long.valueOf(suid);

		int code = service.addFamilyMembers(Fid, creatorPhone, Uid, memberPhone);
		result.setResultCode(code);
		if (code == ResultCode.SUCCESS) {
			result.setResultMessage("添加成功");
		} else if (code == ResultCode.ERROR_NO_FAMILY) {
			result.setResultMessage("不存在该家庭");
		} else if (code == ResultCode.ERROR_NO_USER) {
			result.setResultMessage("不存在该用户");
		}
		out.write(gson.toJson(result));
		out.close();
	}
}
