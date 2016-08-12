package com.bearkiddiary.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.utils.ResultCode;

@WebServlet("/kid/add")
public class KidAdd extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result<Kid> result = new Result<>();
		PrintWriter out = resp.getWriter();

		String Kname = req.getParameter("Kname");
		String sKbirthday = req.getParameter("Kbirthday");
		String Kavatar = req.getParameter("Kavatar");
		String Ksex = req.getParameter("Ksex");
		String Kask = req.getParameter("Kask");
		String sKflowers = req.getParameter("Kflowers");
		String sFid = req.getParameter("Fid");
		String Uphone = req.getParameter("Uphone");

		if (Kname == null || (sFid == null && Uphone == null)) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		Long Kbirthday = null;
		if (sKbirthday != null)
			Kbirthday = Long.valueOf(sKbirthday);

		Integer Kflowers = null;
		if (sKflowers != null)
			Kflowers = Integer.valueOf(sKflowers);

		Long Fid = null;
		if (sFid != null)
			Fid = Long.valueOf(sFid);

		int code = service.addKid(Kname, Kbirthday, Kavatar, Ksex, Kask, Kflowers, Fid, Uphone);
		result.setResultCode(code);
		if (code == ResultCode.ERROR_NO_FAMILY)
			result.setResultMessage("不存在该家庭");
		if (code == ResultCode.SUCCESS)
			result.setResultMessage("添加孩子成功");

		out.write(gson.toJson(result));
		out.close();
	}
}
