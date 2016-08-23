package com.bearkiddiary.servlet.course;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

@WebServlet("/course/students/remove")
public class CourseStudentsRemove extends BaseServlet {
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
			result.setResultMessage("�������������");
			out.write(gson.toJson(result));
			out.close();
			return;
		}
		Long Cid = Long.valueOf(sCid);
		Long Kid = Long.valueOf(sKid);

		int code = service.removeKidFromCourse(Cid, Kid);
		result.setResultCode(code);
		if (code == ResultCode.SUCCESS) {
			result.setResultMessage("ɾ���ɹ�");
		}
		if (code == ResultCode.ERROR_NO_KID) {
			result.setResultMessage("�γ��в����ڸú��ӻ򲻴��ڸñ�ŵĺ���");
		}
		if (code == ResultCode.ERROR_NO_COURSE) {
			result.setResultMessage("�����ڸÿγ�");
		}
		out.write(gson.toJson(result));
		out.close();
	}
}
