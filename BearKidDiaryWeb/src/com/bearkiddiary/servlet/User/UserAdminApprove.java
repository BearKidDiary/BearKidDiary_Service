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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 机构管理员用户对请假申请进行审批，获取请假申请列表
 * Servlet implementation class UserAdminApprove
 */
@WebServlet(name = "UserAdminApprove", urlPatterns = "/user/approve")
public class UserAdminApprove extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out = null;
	private Leave_Application application = new Leave_Application();
	
	private Result<List<Leave_Application>> result;
	private List<Leave_Application> list;
	
//	private JsonObject jsonData;
//	private JsonObject jsonResult;
       
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	out = response.getWriter();
    	//类型，0：获取请假列表；1：对请假进行审批
    	Integer applyType = Integer.valueOf(request.getParameter("applyType"));
    	Long Oid = Long.valueOf(request.getParameter(Organization.OID));
    	String Uphone = request.getParameter(User.PHONE);
    	
    	result = new Result<>();
    	list = new ArrayList<>();
    	
    	//验证权限
    	int resultCode = service.validAdmin(Oid, Uphone);
    	if(resultCode == ResultCode.SUCCESS){
    		
    		if(applyType == 0){ // 获取请假申请列表
        		
        		if(resultCode == ResultCode.SUCCESS){
        			list = service.getOrgApplicationList(Oid);
            		
            		if(list.size() > 0){
            			result.setResultCode(ResultCode.SUCCESS);
                		result.setResultMessage("机构请假申请列表");
                		result.setData(list);
            		}else {
            			result.setResultCode(ResultCode.NO_RESULT);
            			result.setResultMessage("没有请假申请记录");
            			list.add(application);
            			result.setData(list);
            		}
        		}
        		
        	}else if(applyType == 1){ // 对请假申请进行审批
        		Long LAid = Long.valueOf(request.getParameter(Leave_Application.LAID));
            	Integer LAisapproved = Integer.valueOf(request.getParameter(Leave_Application.LAISAPPROVED));
            	String LAcomment = request.getParameter(Leave_Application.LACOMMENT);
            	
            	int count = service.updateApplication(Leave_Application.APPROVED, LAisapproved, Uphone, LAcomment, LAid);
            	if(count > 0){
            		
            		result.setResultCode(ResultCode.SUCCESS);
            		result.setResultMessage("审批成功");
            	}else {
            		
            		result.setResultCode(ResultCode.ERROR_COMMIT);
            		result.setResultMessage("提交审批出错");
            	}
        	}else {
        		//出错
        		result.setResultCode(ResultCode.ERROR);
        		result.setResultMessage("出错了");
        	}
    		
    		
    	}else if(resultCode == ResultCode.ERROR_NO_ORG){
			result.setResultCode(ResultCode.ERROR_NO_ORG);
			result.setResultMessage("没有此机构");
			list.add(application);
			result.setData(list);
		}else if(resultCode == ResultCode.ERROR_NO_PERMISSION){
			result.setResultCode(ResultCode.ERROR_NO_PERMISSION);
			result.setResultMessage("该用户不具备管理员权限");
			list.add(application);
			result.setData(list);
		}
    	
    	out.write(gson.toJson(result));
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
    
    @Override
    public void destroy() {
    	// TODO Auto-generated method stub
    	super.destroy();
    	if(out != null){
        	out.close();
    	}
    }
}
