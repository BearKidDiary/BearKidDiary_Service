package com.bearkiddiary.servlet.Org;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.ResolutionException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.AttendanceGroup;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.service.AttendanceGroupService;
import com.bearkiddiary.servlet.AGBaseServlet;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class AddAttendanceGroup
 */
@WebServlet("/org/attendancegroup/add")
public class AddAttendanceGroup extends AGBaseServlet {
	private static final long serialVersionUID = 1L;
    
	private PrintWriter out = null;
	private Result<AttendanceGroup> result;
	private AttendanceGroup attendanceGroup;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		result = new Result<>();
		out = response.getWriter();
		attendanceGroup = new AttendanceGroup();
		
		String Uphone = request.getParameter(User.PHONE);
		String starttime = request.getParameter(AttendanceGroup.AGSTARTTIME);
		String endtime = request.getParameter(AttendanceGroup.AGENDTIME);
		String monday = request.getParameter(AttendanceGroup.AGMONDAY);
		String tuesday = request.getParameter(AttendanceGroup.AGTUESDAY);
		String wednesday = request.getParameter(AttendanceGroup.AGWEDNESDAY);
		String thursday = request.getParameter(AttendanceGroup.AGTHURSDAY);
		String friday = request.getParameter(AttendanceGroup.AGFRIDAY);
		String saturday = request.getParameter(AttendanceGroup.AGSATURDAY);
		String sunday = request.getParameter(AttendanceGroup.AGSUNDAY);
		
		if(Uphone == null){
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不足！");
			result.setData(null);
		}else {
			if(starttime != null)
				attendanceGroup.setAGstarttime(Long.valueOf(starttime));
			if(endtime != null)
				attendanceGroup.setAGendtime(Long.valueOf(endtime));
			if(monday != null)
				attendanceGroup.setAGmonday(Boolean.valueOf(monday));
			if(tuesday != null)
				attendanceGroup.setAGtuesday(Boolean.valueOf(tuesday));
			if(wednesday != null)
				attendanceGroup.setAGwednesday(Boolean.valueOf(wednesday));
			if(thursday != null)
				attendanceGroup.setAGthursday(Boolean.valueOf(thursday));
			if(friday != null)
				attendanceGroup.setAGfriday(Boolean.valueOf(friday));
			if(saturday != null)
				attendanceGroup.setAGsaturday(Boolean.valueOf(saturday));
			if(sunday != null)
				attendanceGroup.setAGsunday(Boolean.valueOf(sunday));
			Long AGid = AGservice.addAttendanceGroup(attendanceGroup, Uphone);
			if(AGid > 0){
				AttendanceGroup ag = new AttendanceGroup();
				ag.setAGid(AGid);
				result.setData(ag);
				result.setResultMessage("添加考勤组成功");
				result.setResultCode(ResultCode.SUCCESS);
			}else{
				result.setData(null);
				result.setResultMessage("添加考勤组失败");
				result.setResultCode(ResultCode.ERROR);
			}
				
		}
		
		out.write(gson.toJson(result));
	}
}
