package com.bearkiddiary.servlet.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
import com.mysql.fabric.xmlrpc.base.Array;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Servlet implementation class UserGetAttendanceGroup
 */
@WebServlet("/user/attendancegroup")
public class UserGetAttendanceGroup extends AGBaseServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out = null;
	
	private Result<List<AttendanceGroup>> result = new Result<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		out = response.getWriter();
		String Uphone = request.getParameter(User.PHONE);
		List<AttendanceGroup> list = AGservice.getTeacherAttendanceGroupList(Uphone);
		if(list == null)
		{
			result.setResultCode(ResultCode.ERROR_NO_ATTENDANCEGROUP);
			result.setResultMessage("不存在考勤组");
			result.setData(null);
		}else{
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMessage("获取考勤组成功！");
			result.setData(list);
		}
		out.write(gson.toJson(result));
	}

}
