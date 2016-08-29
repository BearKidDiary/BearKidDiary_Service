package com.bearkiddiary.servlet.kid;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

@WebServlet("/kid/body/add")
public class KidBodyMsgAdd extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result<Kid> result = new Result<>();
		PrintWriter out = resp.getWriter();

		String sHeight = req.getParameter("Hheight");
		String sWeight = req.getParameter("Wweight");
		String sVisionLeft = req.getParameter("Vleft");
		String sVisionRight = req.getParameter("Vright");
		String sKid = req.getParameter("Kid");
		String sHTime = req.getParameter("Htime");
		String sWTime = req.getParameter("Wtime");
		String sVTime = req.getParameter("Vtime");

		boolean ok = (sKid != null && ((sHeight != null && sHTime != null) || (sWeight != null && sWTime != null)
				|| ((sVisionLeft != null || sVisionRight != null) && sVTime != null)));
		if (!ok) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		Long Kid = Long.valueOf(sKid);
		Long time = null;

		int code = ResultCode.ERROR_MISSING_PARAMETER;
		if (sHeight != null) {
			Float height = Float.valueOf(sHeight);
			time = Long.valueOf(sHTime);
			code = service.addKidBodyMsg(Kid, height, null, null, null, time);
		}
		if (code != ResultCode.ERROR_NO_KID && sWeight != null) {
			Float weight = Float.valueOf(sWeight);
			time = Long.valueOf(sWTime);
			code = service.addKidBodyMsg(Kid, null, weight, null, null, time);
		}
		if (code != ResultCode.ERROR_NO_KID && (sVisionLeft != null || sVisionRight != null)) {
			time = Long.valueOf(sVTime);
			Float visionLeft = null, visionRight = null;
			if (sVisionLeft != null)
				visionLeft = Float.valueOf(sVisionLeft);
			if (sVisionRight != null)
				visionRight = Float.valueOf(sVisionRight);

			code = service.addKidBodyMsg(Kid, null, null, visionLeft, visionRight, time);
		}

		result.setResultCode(code);
		if (code == ResultCode.SUCCESS) {
			result.setResultMessage("添加成功");
		}
		if (code == ResultCode.ERROR_NO_KID) {
			result.setResultMessage("孩子不存在");
		}
		out.write(gson.toJson(result));
		out.close();
	}
}
