package com.bearkiddiary.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Leave_Application;
import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.utils.ResultCode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 教师用户请假申请
 * Servlet implementation class UserApplication
 */
@WebServlet(name = "UserApplication", urlPatterns = "/user/application")
public class UserApplication extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private JsonObject jsonResult;
	private JsonObject jsonData;
       
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	
    	String Uphone = request.getParameter(User.PHONE);
    	Long Oid = Long.valueOf(request.getParameter(Organization.OID));
    	Integer LAtype = Integer.valueOf(request.getParameter(Leave_Application.LATYPE));
    	Long LAstarttime = Long.valueOf(request.getParameter(Leave_Application.LASTARTTIME));
    	Long LAendtime = Long.valueOf(request.getParameter(Leave_Application.LAENDTIME));
    	String LAreason = request.getParameter(Leave_Application.LAREASON);
    	
    	Leave_Application application = new Leave_Application();
    	application.setLAtype(LAtype);
    	application.setLAstatus(Leave_Application.UNAPPROVED);
    	application.setLAstarttime(LAstarttime);
    	application.setLAendtime(LAendtime);
    	application.setLAreason(LAreason);
    	
    	Long result = service.commitApplication(application, Oid, Uphone);
    	//判断结果，如果保存成功，则返回提交的id,否则返回错误代码（为负值）
    	if(result > 0){
    		jsonData = new JsonObject();
    		jsonData.addProperty(Leave_Application.LAID, result);
    		
    		jsonResult = new JsonObject();
    		jsonResult.addProperty(Result.RESULTCODE, ResultCode.SUCCESS);
    		jsonResult.addProperty(Result.RESULTMESSAGE, "申请提交成功！");
    		jsonResult.add(Result.DATA, jsonData);
    	}else {
    		jsonData = new JsonObject();
    		jsonData.addProperty(Leave_Application.LAID, "null");
    		
    		jsonResult.addProperty(Result.RESULTCODE, ResultCode.ERROR);
    		jsonResult.addProperty(Result.RESULTMESSAGE, "申请提交失败！");
    		jsonResult.add(Result.DATA, jsonData);
    	}
    	
    	out.write(new Gson().toJson(jsonResult));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
}
