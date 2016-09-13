package com.bearkiddiary.servlet.Org;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Group;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@WebServlet("/group")
public class getGroup extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result<Set<Group>> result = new Result<>();
		PrintWriter out = resp.getWriter();

		String sOid = req.getParameter("Oid");
		String sGid = req.getParameter("Gid");

		if (sOid == null && sGid == null) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		Long Oid = null;
		if (sOid != null)
			Oid = Long.valueOf(sOid);
		Long Gid = null;
		if (sGid != null)
			Gid = Long.valueOf(sGid);
		Set<Group> set = service.getGroup(Gid, Oid);
		result.setResultCode(ResultCode.SUCCESS);
		result.setResultMessage("查询成功");
		result.setData(set);
		out.write(gson.toJson(result));
		System.out.println(gson.toJson(result));
		out.close();
	}
}
