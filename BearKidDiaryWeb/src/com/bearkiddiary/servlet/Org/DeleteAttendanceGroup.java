package com.bearkiddiary.servlet.Org;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.AttendanceGroup;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.dao.AttendanceGroupDao;
import com.bearkiddiary.service.AttendanceGroupService;
import com.bearkiddiary.servlet.AGBaseServlet;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.Gson;

/**
 * Servlet implementation class DeleteAttendanceGroup
 */
@WebServlet("/org/attendancegroup/delete")
public class DeleteAttendanceGroup extends AGBaseServlet {
	private static final long serialVersionUID = 1L;
	private Result<Long> result = null;
	private PrintWriter out = null;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		result = new Result<>();
		out = response.getWriter();
		
		String AGid = request.getParameter(AttendanceGroup.AGID);
		if(AGid == null){
			result.setData(null);
			result.setResultCode(ResultCode.ERROR);
			result.setResultMessage("请求参数不完整");
		}else{
			int resultCode = AGservice.delete(Long.valueOf(AGid));
			result.setData(null);
			result.setResultCode(resultCode);
			result.setResultMessage("删除考勤组成功");
		}
		
		out.write(gson.toJson(result));
	}
	
	@Override
	public void destroy() {
		out.close();
		super.destroy();
	}
}
