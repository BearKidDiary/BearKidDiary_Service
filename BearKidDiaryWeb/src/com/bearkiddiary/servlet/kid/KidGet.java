package com.bearkiddiary.servlet.kid;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

@WebServlet("/kid")
public class KidGet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result<Set<Kid>> result = new Result<>();
		PrintWriter out = resp.getWriter();

		String sKid = req.getParameter("Kid");
		String Uphone = req.getParameter("Uphone");
		String sFid = req.getParameter("Fid");

		if (sKid == null && Uphone == null && sFid == null) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		Long Kid = null;
		if (sKid != null)
			Kid = Long.valueOf(sKid);

		Long Fid = null;
		if (sFid != null)
			Fid = Long.valueOf(sFid);

		Set<Kid> set = service.getKids(Kid, Uphone, Fid);
		if (set == null) {
			result.setResultCode(ResultCode.ERROR_NO_RESULT);
			result.setResultMessage("查询不正常，家庭可能不存在");
		} else {
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMessage("查询成功");
			result.setData(set);
		}
		out.write(gson.toJson(result));
		out.close();
	}
}
