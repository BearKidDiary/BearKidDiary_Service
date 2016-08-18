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
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * ��������Ա�û���������������������ȡ��������б�
 * Servlet implementation class UserAdminApprove
 */
@WebServlet(name = "UserAdminApprove", urlPatterns = "/user/approve")
public class UserAdminApprove extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	
	private Result<List<Leave_Application>> result;
	private List<Leave_Application> list;
	
	private JsonObject jsonData;
	private JsonObject jsonResult;
       
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	out = response.getWriter();
    	//���ͣ�0����ȡ����б�1������ٽ�������
    	Integer applyType = Integer.valueOf(request.getParameter("applyType"));
    	Long Oid = Long.valueOf(request.getParameter(Organization.OID));
    	
    	result = new Result<>();
    	list = new ArrayList<>();
    	if(applyType == 0){ // ��ȡ��������б�
    		list = service.getOrgApplicationList(Oid);
    		
    		if(list.size() > 0){
    			result.setResultCode(ResultCode.SUCCESS);
        		result.setResultMessage("������������б�");
        		result.setData(list);
    		}else {
    			result.setResultCode(ResultCode.NO_RESULT);
    			result.setResultMessage("û����������¼");
    			list.add(new Leave_Application());
    			result.setData(list);
    		}
    		out.println(gson.toJson(result));
    	}else if(applyType == 1){ // ����������������
    		Long LAid = Long.valueOf(request.getParameter(Leave_Application.LAID));
        	Integer LAisapproved = Integer.valueOf(request.getParameter(Leave_Application.LAISAPPROVED));
        	String LAcomment = request.getParameter(Leave_Application.LACOMMENT);
        	int count = service.updateApplication(Leave_Application.APPROVED, LAisapproved, LAcomment, LAid);
        	if(count > 0){
        		jsonData = new JsonObject();
        		jsonData.addProperty("LAid", LAid);
        		
        		jsonResult = new JsonObject();
        		jsonResult.addProperty(Result.RESULTCODE, ResultCode.SUCCESS);
        		jsonResult.addProperty(Result.RESULTMESSAGE, "�����ɹ�");
        		jsonResult.add(Result.DATA, jsonData);
        	}else {
        		jsonData = new JsonObject();
        		jsonData.addProperty(Leave_Application.LAID, -1);
        		
        		jsonResult = new JsonObject();
        		jsonResult.addProperty(Result.RESULTCODE, ResultCode.ERROR_COMMIT);
        		jsonResult.addProperty(Result.RESULTMESSAGE, "�ύ��������");
        		jsonResult.add(Result.DATA, jsonData);
        	}
        	out.write(gson.toJson(jsonResult));
    	}else {
    		//����
    		result.setResultCode(ResultCode.ERROR);
    		result.setResultMessage("������");
    		list.add(new Leave_Application());
    		result.setData(list);
    	}
    	out.close();
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
