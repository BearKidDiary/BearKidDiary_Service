package com.bearkiddiary.servlet.course;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

@WebServlet("/course/students/add")
public class CourseStudentsAdd extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result result = new Result<>();
		PrintWriter out = resp.getWriter();

		String sCid = req.getParameter("Cid");
		String sKid = req.getParameter("Kid");

		if (sCid == null || sKid == null) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}
		Long Cid = Long.valueOf(sCid);
		Long Kid = Long.valueOf(sKid);

		int code = service.addKidToCourse(Cid, Kid);
		result.setResultCode(code);
		if (code == ResultCode.SUCCESS) {
			result.setResultMessage("添加成功");
		}
		if (code == ResultCode.ERROR_NO_KID) {
			result.setResultMessage("不存在该孩子");
		}
		if (code == ResultCode.ERROR_NO_COURSE) {
			result.setResultMessage("不存在该课程");
		}
		if (code == ResultCode.ERROR_EXIST_KID) {
			result.setResultMessage("课程中已有该孩子");
		}
		out.write(gson.toJson(result));
		out.close();
	}
}
