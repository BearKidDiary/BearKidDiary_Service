package com.bearkiddiary.servlet.Org;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

import com.bearkiddiary.bean.AttendanceGroup;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.service.AttendanceGroupService;
import com.bearkiddiary.servlet.AGBaseServlet;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 机构获取考勤组
 * Servlet implementation class OrgGetAttendanceGroup
 */
@WebServlet("/org/attendancegroup")
public class OrgGetAttendanceGroup extends AGBaseServlet {
	private static final long serialVersionUID = 1L;
    
	private PrintWriter out = null;
	private Result<List<Map<String, Object>>> result;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		result = new Result<>();
		
		String Uphone = request.getParameter(User.PHONE);
		if(Uphone == null){
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("缺少请求参数");
			result.setData(null);
		}else{
			List<AttendanceGroup> list = AGservice.getOrgAttendanceGroupList(Uphone);
			if(list == null){
				result.setResultCode(ResultCode.ERROR_NO_ATTENDANCEGROUP);
				result.setResultMessage("该机构不存在考勤组");
				result.setData(null);
			}else{
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMessage("获取考勤组成功");
				List<Map<String, Object>> list_result = new ArrayList<>();
				Map<String, Object> map = new HashMap();
				for(AttendanceGroup ag : list){
					map = new HashMap();
					map.put("AG", ag);
					map.put("teachers", ag.getTeachers());
					list_result.add(map);
				}
				result.setData(list_result);
			}
		}
		out.write(gson.toJson(result));
	}
	
	@Override
	public void destroy() {
		out.close();
		super.destroy();
	}
}
