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

@WebServlet("/kid/update")
public class KidUpdate extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result<Kid> result = new Result<>();
		PrintWriter out = resp.getWriter();

		String sKid = req.getParameter("Kid");
		String Kname = req.getParameter("Kname");
		String sKbirthday = req.getParameter("Kbirthday");
		String Kavatar = req.getParameter("Kavatar");
		String Ksex = req.getParameter("Ksex");
		String Kask = req.getParameter("Kask");
		String sKflowers = req.getParameter("Kflowers");

		if (sKid == null) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("�������������");
			out.write(gson.toJson(result));
			out.close();
			return;
		}
		Long Kid = Long.valueOf(sKid);
		Long Kbirthday = null;
		if (sKbirthday != null)
			Kbirthday = Long.valueOf(sKbirthday);

		Integer Kflowers = null;
		if (sKflowers != null)
			Kflowers = Integer.valueOf(sKflowers);

		int code = service.updateKid(Kid, Kname, Kbirthday, Kavatar, Ksex, Kask, Kflowers);
		result.setResultCode(code);
		if (code == ResultCode.ERROR_NO_KID) {
			result.setResultMessage("�����ڸú���");
		}
		if (code == ResultCode.ERROR_EXIST_KID) {
			result.setResultMessage("�Ѵ���������ͬ�ĺ���");
		}
		if (code == ResultCode.SUCCESS) {
			result.setResultMessage("���³ɹ�");
		}
		out.write(gson.toJson(result));
	}
}