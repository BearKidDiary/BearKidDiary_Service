package com.bearkiddiary.servlet.Org;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.service.Service;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class OrgMembersAdd
 */
@WebServlet(name = "/OrgMembersAdd", urlPatterns = "/org/member/add")
public class OrgMembersAdd extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private int resultCode;   
    private Result<Organization> result;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	result = new Result<>();
    	
    	Integer identity = Integer.valueOf(request.getParameter("identity"));
    	
    	long Oid = Long.valueOf(request.getParameter(Organization.OID));
    	long Uid = Long.valueOf(request.getParameter(User.ID));
    	
    	resultCode = service.addOrgMember(Oid, Uid, identity);
    	
    	if(resultCode == ResultCode.SUCCESS){
    		result.setResultCode(ResultCode.SUCCESS);
    		result.setResultMessage("添加成功！");
    		
    	}else {
    		result.setResultCode(resultCode);
    		result.setResultMessage("添加失败！");
    	}
    	out.write(gson.toJson(result));
		System.out.println(gson.toJson(result));
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
}
