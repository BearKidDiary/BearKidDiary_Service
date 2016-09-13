package com.bearkiddiary.servlet.Org;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

@WebServlet("/group/delete")
public class DeleteGroup extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result<Object> result = new Result<>();
		PrintWriter out = resp.getWriter();

		String sGid = req.getParameter("Gid");

		if (sGid == null) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		Long Gid = Long.valueOf(sGid);

		int res = service.deleteGroup(Gid);
		result.setResultCode(res);
		if (res == ResultCode.ERROR_NO_GROUP) {
			result.setResultMessage("不存在该分组");
		} else if (res == ResultCode.SUCCESS) {
			result.setResultMessage("删除成功");
		}

		out.write(gson.toJson(result));
		System.out.println(gson.toJson(result));
		out.close();
	}
}
