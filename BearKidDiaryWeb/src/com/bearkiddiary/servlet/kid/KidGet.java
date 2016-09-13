package com.bearkiddiary.servlet.kid;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Family;
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
		String sCid = req.getParameter("Cid");
		String Range = req.getParameter("Range");

		if (sKid == null && Uphone == null && sFid == null && sCid == null) {
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

		Long Cid = null;
		if (sCid != null)
			Cid = Long.valueOf(sCid);

		if (Range == null) {
			Range = "Creator";
		}

		Set<Kid> set = null;
		System.out.println(Range);
		if (Uphone != null && Range.equals("ALL")) {
			Set<Family> familys = service.getAttendFamily(Uphone);
			if (familys != null) {
				set = new HashSet<>();
				for (Family f : familys) {
					Set<Kid> tmp = service.getKids(null, null, f.getFid(), null);
					set.addAll(tmp);
				}
			}
		}

		Set<Kid> tmp = service.getKids(Kid, Uphone, Fid, Cid);
		if (set == null)
			set = tmp;
		else if (tmp != null)
			set.addAll(tmp);

		if (set == null) {
			result.setResultCode(ResultCode.ERROR_NO_RESULT);
			result.setResultMessage("查询不正常，家庭可能不存在");
		} else {
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMessage("查询成功");
			result.setData(set);
		}
		out.write(gson.toJson(result));
		System.out.println(gson.toJson(result));
		out.close();
	}
}
