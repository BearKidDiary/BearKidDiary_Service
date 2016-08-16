package com.bearkiddiary.servlet;

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
import com.bearkiddiary.utils.ResultCode;
import com.google.gson.JsonArray;

/**
 * 机构管理员用户对请假申请进行审批
 * Servlet implementation class UserAdminApprove
 */
@WebServlet(name = "UserAdminApprove", urlPatterns = "/user/approve")
public class UserAdminApprove extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Result<Leave_Application> result;
	private JsonArray jsonArray;
       
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	Integer app = Integer.valueOf(request.getParameter("app"));
    	if(app == 0){
    		result = new Result<>();
    		Long Oid = Long.valueOf(request.getParameter(Organization.OID));
    		List<Leave_Application> list = new ArrayList<>();
    		list = service.getOrgApplicationList(Oid);
    		
    		out.println(list.toString());
    	}else if(app == 1){
        	Integer approve = Integer.valueOf(request.getParameter("approve"));
        	String LAcomment = request.getParameter(Leave_Application.LACOMMENT);
    	}
    	
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
}
