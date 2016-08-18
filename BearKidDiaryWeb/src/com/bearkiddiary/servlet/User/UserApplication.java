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

import com.bearkiddiary.bean.Leave_Application;
import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 教师用户获取请假申请，请假申请
 * Servlet implementation class UserApplication
 */
@WebServlet(name = "UserApplication", urlPatterns = "/user/apply")
public class UserApplication extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	
	private JsonObject jsonResult;
	private JsonObject jsonData;
	
	private Result<List<Leave_Application>> result;
	private List<Leave_Application> list;
       
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	out = response.getWriter();
    	result = new Result<>();
    	list = new ArrayList<>();
    	// 获取参数 applyType = 0|1, 0:获取请假申请列表，1：请假申请
    	Integer applyType = Integer.valueOf(request.getParameter("applyType"));
    	String Uphone = request.getParameter(User.PHONE);
    	
    	if(applyType == 0){ // 获取请假申请列表
    		
    		list = service.getUserApplicationList(Uphone);
    		
    		//存在申请列表
    		if(list.size() > 0){
    			result.setResultCode(ResultCode.SUCCESS);
    			result.setResultMessage("获取申请成功");
    			result.setData(list);
    			
    			out.write(gson.toJson(result));
    		}else {
    			result.setResultCode(ResultCode.NO_RESULT);
    			result.setResultMessage("没有请假申请记录");
    			list.add(new Leave_Application());
    			result.setData(list);
    			out.write(gson.toJson(result));
    		}
    		
    	}else if(applyType == 1){ // 请假申请
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
        	
        	Long resultId = service.commitApplication(application, Oid, Uphone);
        	//判断结果，如果保存成功，则返回提交的id,否则返回错误代码（为负值）
        	if(resultId > 0){
        		jsonData = new JsonObject();
        		jsonData.addProperty(Leave_Application.LAID, resultId);
        		
        		jsonResult = new JsonObject();
        		jsonResult.addProperty(Result.RESULTCODE, ResultCode.SUCCESS);
        		jsonResult.addProperty(Result.RESULTMESSAGE, "申请提交成功！");
        		jsonResult.add(Result.DATA, jsonData);
        	}else {
        		jsonData = new JsonObject();
        		jsonData.addProperty(Leave_Application.LAID, -1);
        		
        		jsonResult.addProperty(Result.RESULTCODE, ResultCode.ERROR_COMMIT);
        		jsonResult.addProperty(Result.RESULTMESSAGE, "申请提交失败！");
        		jsonResult.add(Result.DATA, jsonData);
        	}
        	
        	out.write(gson.toJson(jsonResult));
    	}else {
    		//出错
//    		jsonData = new JsonObject();
//    		jsonData.addProperty(Leave_Application.LAID, -1);
//    		
//    		jsonResult.addProperty(Result.RESULTCODE, ResultCode.ERROR);
//    		jsonResult.addProperty(Result.RESULTMESSAGE, "出错了");
//    		jsonResult.add(Result.DATA, jsonData);
//    		
//    		out.write(gson.toJson(jsonResult));
    		list.add(new Leave_Application());
    		result.setResultCode(ResultCode.ERROR);
    		result.setResultMessage("出错了");
    		result.setData(list);
    		out.write(gson.toJson(list));
    	}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
    
    @Override
    public void destroy() {
    	// TODO Auto-generated method stub
    	super.destroy();
    	out.close();
    }
}
