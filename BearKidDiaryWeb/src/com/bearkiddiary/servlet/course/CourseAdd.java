package com.bearkiddiary.servlet.course;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

@WebServlet("/course/add")
public class CourseAdd extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result result = new Result<>();
		PrintWriter out = resp.getWriter();

		String sCclasstime = req.getParameter("Cclasstime");
		String sCendtime = req.getParameter("Cendtime");
		String sCtime = req.getParameter("Ctime");
		String sCofftime = req.getParameter("Cofftime");
		String Cbackground = req.getParameter("Cbackground");
		String Cdesc = req.getParameter("Cdesc");
		String Cname = req.getParameter("Cname");
		String Cimage = req.getParameter("Cimage");
		String sCmonday = req.getParameter("Cmonday");
		String sCtuesday = req.getParameter("Ctuesday");
		String sCwednesday = req.getParameter("Cwednesday");
		String sCthursday = req.getParameter("Cthursday");
		String sCfriday = req.getParameter("Cfriday");
		String sCsaturday = req.getParameter("Csaturday");
		String sCsunday = req.getParameter("Csunday");
		String steacherUid = req.getParameter("teacherUid");
		String teacherUphone = req.getParameter("teacherUphone");
		String sapproverUid = req.getParameter("approverUid");
		String approverUphone = req.getParameter("approverUphone");
		String sOid = req.getParameter("Oid");

		if (sOid == null || Cname == null || (steacherUid == null && teacherUphone == null)
				|| (sapproverUid == null && approverUphone == null)) {
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
			out.write(gson.toJson(result));
			out.close();
			return;
		}

		Long teacherUid = null;
		if (steacherUid != null)
			teacherUid = Long.valueOf(steacherUid);
		Long approverUid = null;
		if (sapproverUid != null)
			approverUid = Long.valueOf(sapproverUid);
		Long Oid = null;
		if (sOid != null)
			Oid = Long.valueOf(sOid);
		Long Cclasstime = null;
		if (sCclasstime != null)
			Cclasstime = Long.valueOf(sCclasstime);
		Long Cendtime = null;
		if (sCendtime != null)
			Cendtime = Long.valueOf(sCendtime);
		Long Ctime = null;
		if (sCtime != null)
			Ctime = Long.valueOf(sCtime);
		Long Cofftime = null;
		if (sCofftime != null)
			Cofftime = Long.valueOf(sCofftime);
		Boolean Cmonday = null;
		if (sCmonday != null)
			Cmonday = Boolean.valueOf(sCmonday);
		Boolean Ctuesday = null;
		if (sCtuesday != null)
			Ctuesday = Boolean.valueOf(sCtuesday);
		Boolean Cwednesday = null;
		if (sCwednesday != null)
			Cwednesday = Boolean.valueOf(sCwednesday);
		Boolean Cthursday = null;
		if (sCthursday != null)
			Cthursday = Boolean.valueOf(sCthursday);
		Boolean Cfriday = null;
		if (sCfriday != null)
			Cfriday = Boolean.valueOf(sCfriday);
		Boolean Csaturday = null;
		if (sCsaturday != null)
			Csaturday = Boolean.valueOf(sCsaturday);
		Boolean Csunday = null;
		if (sCsunday != null)
			Csunday = Boolean.valueOf(sCsunday);

		int code = service.addCourse(Cclasstime, Cendtime, Ctime, Cofftime, Cbackground, Cdesc, Cname, Cimage, Cmonday,
				Ctuesday, Cwednesday, Cthursday, Cfriday, Csaturday, Csunday, teacherUid, teacherUphone, approverUid,
				approverUphone, Oid);
		result.setResultCode(code);
		if (code == ResultCode.ERROR_NO_USER) {
			result.setResultMessage("不存在该审批人或者任课老师");
		}
		if (code == ResultCode.ERROR_NO_ORG) {
			result.setResultMessage("不存在该机构");
		}
		if (code == ResultCode.SUCCESS) {
			result.setResultMessage("新建成功");
		}
		out.write(gson.toJson(result));
	}
}
