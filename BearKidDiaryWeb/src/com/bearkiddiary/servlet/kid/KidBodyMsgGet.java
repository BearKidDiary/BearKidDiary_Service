package com.bearkiddiary.servlet.kid;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Height;
import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.Vision;
import com.bearkiddiary.bean.Weight;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

@WebServlet("/kid/body")
public class KidBodyMsgGet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result<List> result = new Result<>();
		PrintWriter out = resp.getWriter();

		String sPageNum = req.getParameter("PageNum");
		String sPageSize = req.getParameter("PageSize");
		String sKid = req.getParameter("Kid");
		String Range = req.getParameter("Range");
		String Order = req.getParameter("Order");

		if (sKid == null) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		Long Kid = Long.valueOf(sKid);

		if (Order == null || !Order.equals("asc")) {
			Order = "desc";
		}
		if (Range == null) {
			Range = "Height";
		}
		if (sPageNum != null && sPageSize != null) {
			int pageNum = Integer.valueOf(sPageNum);
			int pageSize = Integer.valueOf(sPageSize);

			if (Range.equals("Weight")) {
				List<Weight> weight = service.getWeight(Kid, Order, pageNum, pageSize);
				result.setData(weight);
			} else if (Range.equals("Vision")) {
				List<Vision> vision = service.getVision(Kid, Order, pageNum, pageSize);
				result.setData(vision);
			} else {// Range==Height
				List<Height> height = service.getHeight(Kid, Order, pageNum, pageSize);
				result.setData(height);
			}
		} else {
			if (Range.equals("Weight")) {
				List<Weight> weight = service.getWeight(Kid, Order);
				result.setData(weight);
			} else if (Range.equals("Vision")) {
				List<Vision> vision = service.getVision(Kid, Order);
				result.setData(vision);
			} else {// Range==Height
				List<Height> height = service.getHeight(Kid, Order);
				result.setData(height);
			}
		}
		out.write(gson.toJson(result));
		out.close();
	}
}
