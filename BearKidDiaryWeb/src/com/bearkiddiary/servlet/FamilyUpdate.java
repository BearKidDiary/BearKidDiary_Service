package com.bearkiddiary.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.utils.ResultCode;

@WebServlet("/family/update")
public class FamilyUpdate extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result<Family> result = new Result<>();
		PrintWriter out = resp.getWriter();

		String Uphone = req.getParameter("Uphone");
		String sFid = req.getParameter("Fid");
		String Fname = req.getParameter("Fname");

		if (Fname == null || (Uphone == null && sFid == null)) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		Long Fid = null;
		if (sFid != null)
			Fid = Long.valueOf(sFid);
		int code = service.updateFamily(Uphone, Fid, Fname);

		result.setResultCode(code);
		if (code == ResultCode.ERROR_NO_FAMILY) {
			result.setResultMessage("不存在该家庭");
		}
		out.write(gson.toJson(result));
		out.close();
	}
}
