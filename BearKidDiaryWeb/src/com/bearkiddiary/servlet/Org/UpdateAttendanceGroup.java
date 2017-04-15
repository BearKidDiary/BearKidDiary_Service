package com.bearkiddiary.servlet.Org;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.AttendanceGroup;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.service.AttendanceGroupService;
import com.bearkiddiary.service.Service;
import com.bearkiddiary.servlet.AGBaseServlet;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * 更新考勤组打卡的信息
 * 更新打卡教师列表
 * Servlet implementation class UpdateAttendanceGroup
 */
@WebServlet("/org/attendancegroup/update")
public class UpdateAttendanceGroup extends AGBaseServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out = null;
	private Result<AttendanceGroup> result;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		result = new Result<>();
		
		String sAGid = request.getParameter(AttendanceGroup.AGID);		
		String sAGstarttime = request.getParameter(AttendanceGroup.AGSTARTTIME);
		String sAGendtime = request.getParameter(AttendanceGroup.AGENDTIME);
		String sAGmonday = request.getParameter(AttendanceGroup.AGMONDAY);
		String sAGtuesday = request.getParameter(AttendanceGroup.AGTUESDAY);
		String sAGwednesday = request.getParameter(AttendanceGroup.AGWEDNESDAY);
		String sAGthursday = request.getParameter(AttendanceGroup.AGTHURSDAY);
		String sAGfriday = request.getParameter(AttendanceGroup.AGFRIDAY);
		String sAGsaturday = request.getParameter(AttendanceGroup.AGSATURDAY);
		String sAGsunday = request.getParameter(AttendanceGroup.AGSUNDAY);
		String teachers = request.getParameter(AttendanceGroup.AGTEACHERS);
		String AGname = request.getParameter(AttendanceGroup.AGNAME);
		
		if(sAGid == null){
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("缺少请求参数！");
		}else{
			
			Long AGid = Long.valueOf(sAGid);
			
			Long AGstarttime = null;
			if(sAGstarttime != null)
				AGstarttime = Long.valueOf(sAGstarttime);
			
			Long AGendtime = null;
			if(sAGendtime != null)
				AGendtime = Long.valueOf(sAGendtime);
			
			Boolean AGmonday = null;
			if(sAGmonday != null)
				AGmonday = Boolean.valueOf(sAGmonday);
			
			Boolean AGtuesday = null;
			if(sAGtuesday != null)
				AGtuesday = Boolean.valueOf(sAGtuesday);
			
			Boolean AGwednesday = null;
			if(sAGwednesday != null)
				AGwednesday = Boolean.valueOf(sAGwednesday);
			
			Boolean AGthursday = null;
			if(sAGthursday != null)
				AGthursday = Boolean.valueOf(sAGthursday);
			
			Boolean AGfriday = null;
			if(sAGfriday != null)
				AGfriday = Boolean.valueOf(sAGfriday);
			
			Boolean AGsaturday = null;
			if(sAGsaturday != null)
				AGsaturday = Boolean.valueOf(sAGsaturday);
			
			Boolean AGsunday = null;
			if(sAGsunday != null)
				AGsunday = Boolean.valueOf(sAGsunday);
			
			//更新考勤组的基本考勤信息
			int resultCode_update_AG = AGservice.update(AGid, AGname, AGstarttime, AGendtime, AGmonday, AGtuesday, AGwednesday, AGthursday, AGfriday, AGsaturday, AGsunday);
			//更新考勤组的打卡教师
			int resultCode_update_teacher = 0;
			
			List<String> teachers_phone = null;
			if(teachers != null){
				teachers_phone = new ArrayList<>();
				JsonArray jsonArray = new JsonParser().parse(teachers).getAsJsonArray();
				Iterator<JsonElement> iter = jsonArray.iterator();
				while(iter.hasNext()){
					teachers_phone.add(iter.next().getAsString());
				}
				resultCode_update_teacher = AGservice.updateTeachers(AGid, AGservice.getTeacherWithPhone(teachers_phone));
			}
				
			
			if(resultCode_update_AG >= 0 && resultCode_update_teacher >= 0){
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMessage("更新考勤组成功！");
			}else if(resultCode_update_AG >= 0 && resultCode_update_teacher < 0){
				result.setResultCode(ResultCode.ERROR);
				result.setResultMessage("更新考勤组信息成功，更新教师列表失败！");
			}else if(resultCode_update_AG < 0 && resultCode_update_teacher >= 0){
				result.setResultCode(ResultCode.ERROR);
				result.setResultMessage("更新考勤组信息失败，更新教师列表成功！");
			}else{
				result.setResultCode(ResultCode.ERROR);
				result.setResultMessage("更新考勤组失败！");
			}
			String path = getServletContext().getRealPath("META-INF");
			System.out.println(path);
			
			System.out.print(gson.toJson(result));
			out.write(gson.toJson(result));
		}
		
		
	}
	
	
	@Override
	public void destroy() {
		out.close();
		super.destroy();
	}
}
