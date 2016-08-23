package com.bearkiddiary.servlet.kid;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.TimeLine;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

@WebServlet("/kid/timeline")
public class KidTimeLineGet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result result = new Result<>();
		PrintWriter out = resp.getWriter();

		String sKid = req.getParameter("Kid");
		String sUid = req.getParameter("Uid");
		String Uphone = req.getParameter("Uphone");
		String Order = req.getParameter("Order");
		String sPageSize = req.getParameter("PageSize");
		String sPageNum = req.getParameter("PageNum");

		
		if(sKid==null){
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}
		
		Long Kid = Long.valueOf(sKid);
		Long Uid = null;
		if (sUid != null)
			Uid = Long.valueOf(sUid);
		if (sPageSize != null && sPageNum != null) {
			int pageSize = Integer.valueOf(sPageSize);
			int pageNum = Integer.valueOf(sPageNum);
			List<TimeLine> list = service.getTimeLine(Kid, Uid, Uphone, Order, pageSize, pageNum);
			result.setData(list);
		} else {
			List<TimeLine> list = service.getTimeLine(Kid, Uid, Uphone, Order);
			result.setData(list);
		}

		out.write(gson.toJson(result));
		System.out.println(gson.toJson(result));
		out.close();
	}
}
