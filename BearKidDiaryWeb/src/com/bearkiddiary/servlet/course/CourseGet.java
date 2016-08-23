package com.bearkiddiary.servlet.course;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Course;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

@WebServlet("/course")
public class CourseGet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result<Set<Course>> result = new Result<>();
		PrintWriter out = resp.getWriter();

		String sCid = req.getParameter("Cid");
		String sOid = req.getParameter("Oid");
		String sUid = req.getParameter("Uid");
		String Uphone = req.getParameter("Uphone");
		String sKid = req.getParameter("Kid");

		if (sCid == null && sOid == null && sUid == null && Uphone == null && sKid == null) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		Long Cid = null;
		if (sCid != null)
			Cid = Long.valueOf(sCid);

		Long Uid = null;
		if (sUid != null)
			Uid = Long.valueOf(sUid);

		Long Oid = null;
		if (sOid != null)
			Oid = Long.valueOf(sOid);

		Long Kid = null;
		if (sKid != null)
			Kid = Long.valueOf(sKid);

		Set<Course> set = service.getCourse(Cid, Uid, Uphone, Oid, Kid);
		result.setData(set);

		if (set == null) {
			result.setResultCode(ResultCode.ERROR_NO_RESULT);
			result.setResultMessage("请检查参数的合法性");
		} else {
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMessage("查询成功");
		}
		out.write(gson.toJson(result));
		out.close();
	}
}
