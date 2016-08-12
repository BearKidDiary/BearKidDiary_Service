package com.bearkiddiary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.utils.ResultCode;
import com.bearkiddiary.bean.Family;

@WebServlet("/family")
public class FamilyGet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Result<Set<Family>> result = new Result<>();

		String Uphone = req.getParameter("Uphone");
		String sFid = req.getParameter("Fid");
		String Range = req.getParameter("Range");

		if (Uphone == null && sFid == null) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		Set<Family> set = null;
		if (sFid != null) {
			Long Fid = Long.valueOf(sFid);
			Family family = service.getCreatedFamily(Uphone, Fid);
			set = new HashSet<>();
			set.add(family);
		} else {
			if (Range == null)
				Range = "ALL";
			System.out.println(Range);

			if (Range.equals("Attend")) {

				set = service.getAttendFamily(Uphone);

			} else if (Range.equals("Create")) {

				set = new HashSet<>();
				Family family = service.getCreatedFamily(Uphone, null);
				if (family != null)
					set.add(family);

			} else {// Range==ALL

				set = new HashSet<>();
				Family family = service.getCreatedFamily(Uphone, null);
				set.add(family);
				set.addAll(service.getAttendFamily(Uphone));

			}
		}

		result.setResultCode(ResultCode.SUCCESS);
		result.setData(set);
		out.write(gson.toJson(result));
		out.close();
		return;
	}
}
