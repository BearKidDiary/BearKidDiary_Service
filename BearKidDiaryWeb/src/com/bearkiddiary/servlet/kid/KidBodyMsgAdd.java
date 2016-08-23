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

		String sHeight = req.getParameter("Height");
		String sWeight = req.getParameter("Weight");
		String sVisionLeft = req.getParameter("VisionLeft");
		String sVisionRight = req.getParameter("VisionRight");
		String sKid = req.getParameter("Kid");
		String sTime = req.getParameter("Time");

		if (sKid == null || sTime == null
				|| (sHeight == null && sWeight == null && sVisionLeft == null && sVisionRight == null)) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		Long Kid = Long.valueOf(sKid);
		Long time = Long.valueOf(sTime);

		int code = ResultCode.ERROR_MISSING_PARAMETER;
		if (sHeight != null) {
			Float height = Float.valueOf(sHeight);
			code = service.addKidBodyMsg(Kid, height, null, null, null, time);
		}
		if (code != ResultCode.ERROR_NO_KID && sWeight != null) {
			Float weight = Float.valueOf(sWeight);
			code = service.addKidBodyMsg(Kid, null, weight, null, null, time);
		}
		if (code != ResultCode.ERROR_NO_KID && (sVisionLeft != null || sVisionRight != null)) {
			Float visionLeft = null, visionRight = null;
			if (sVisionLeft != null)
				visionLeft = Float.valueOf(sVisionLeft);
			if (sVisionRight != null)
				visionRight = Float.valueOf(sVisionRight);

			code = service.addKidBodyMsg(Kid, null, null, visionLeft, visionRight, time);
		}
		
		result.setResultCode(code);
		if(code==ResultCode.SUCCESS){
			result.setResultMessage("添加成功");
		}
		if(code==ResultCode.ERROR_NO_KID){
			result.setResultMessage("孩子不存在");
		}
		out.write(gson.toJson(result));
		out.close();
	}
}
