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
import com.bearkiddiary.utils.ImageUtil;
import com.bearkiddiary.utils.ResultCode;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@WebServlet("/kid/timeline/add")
public class KidTimeLineAdd extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result result = new Result<>();
		PrintWriter out = resp.getWriter();

		String content = req.getParameter("Treleasecontent");
		String sTime = req.getParameter("Treleasetime");
		String image1 = req.getParameter("Timage1");
		String image2 = req.getParameter("Timage2");
		String image3 = req.getParameter("Timage3");
		String type = req.getParameter("Ttype");
		String sLogoType = req.getParameter("Ttypelogo");
		String sUid = req.getParameter("Uid");
		String Uphone = req.getParameter("Uphone");
		String sKid = req.getParameter("Kid");
		
		String TimageStr1 = req.getParameter("TimageStr1");
		String TimageStr2 = req.getParameter("TimageStr2");
		String TimageStr3 = req.getParameter("TimageStr3");

		if (content == null || sTime == null || type == null || (sUid == null && Uphone == null) || sKid == null) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		Long time = Long.valueOf(sTime);

		Long Uid = null;
		if (sUid != null)
			Uid = Long.valueOf(sUid);

		Long Kid = Long.valueOf(sKid);

		
		System.out.println(sLogoType);
		Integer logoType = 0;
		if (sLogoType != null)
			logoType = Integer.valueOf(sLogoType);

		int code = service.addTimeLine(content, time, image1, image2, image3, type, logoType, Uid, Uphone, Kid);
		
		if(TimageStr1 != null){
			if(ImageUtil.saveImage(TimageStr1, image1, imagePath + "/timeline")){
				System.out.println("保存" + image1 + "成功！");
			}
		}
		if(TimageStr2 != null){
			if(ImageUtil.saveImage(TimageStr2, image2, imagePath + "/timeline")){
				System.out.println("保存" + image2 + "成功！");
			}
		}
		
		if(TimageStr3 != null){
			if(ImageUtil.saveImage(TimageStr3, image3, imagePath + "/timeline")){
				System.out.println("保存" + image3 + "成功！");
			}
		}
		
		result.setResultCode(code);

		if (code == ResultCode.ERROR_NO_USER) {
			result.setResultMessage("不存在该发布者");
		}
		if (code == ResultCode.ERROR_NO_KID) {
			result.setResultMessage("不存在该孩子");
		}
		if (code == ResultCode.SUCCESS) {
			result.setResultMessage("添加成功");
		}
		
		out.write(gson.toJson(result));
		System.out.println(gson.toJson(result));
		out.close();
	}
}
