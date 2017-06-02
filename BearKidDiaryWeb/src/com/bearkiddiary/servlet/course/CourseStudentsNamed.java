package com.bearkiddiary.servlet.course;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Course;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.SAttendRecord;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * 课程学生点名
 */
@WebServlet("/course/students/named")
public class CourseStudentsNamed extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Result result;
	private PrintWriter out;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		result = new Result<>();
		out = response.getWriter();
		
		String sTime = request.getParameter(SAttendRecord.STTIME);
		String sCid = request.getParameter("Cid");
		
		if(sCid == null){
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("缺少请求参数！");
		}else{
			Long Cid = Long.valueOf(sCid);
			Long STtime = Long.valueOf(sTime); 
			String attend = request.getParameter("Attend");
			String unattend = request.getParameter("unAttend");
			
			JsonParser jp = new JsonParser();
			JsonArray attendArray = jp.parse(attend).getAsJsonArray();
			JsonArray unattendArray = jp.parse(unattend).getAsJsonArray();
			
			Iterator<JsonElement> iter = attendArray.iterator();
			
			List<Long> attendList = new ArrayList<>();
			while(iter.hasNext()){
				attendList.add(iter.next().getAsLong());
			}
			
			iter = unattendArray.iterator();
			List<Long> unattendList = new ArrayList<>();
			while(iter.hasNext()){
				unattendList.add(iter.next().getAsLong());
			}
			
			int resultCode = service.named(Cid, STtime, attendList, unattendList);
			result.setResultCode(resultCode);
			if(resultCode == ResultCode.ERROR){
				result.setResultMessage("点名保存失败！");
			}else{
				result.setResultMessage("点名保存成功！");
			}
			out.write(gson.toJson(result));
		}
	}

}
