package com.bearkiddiary.servlet.Org;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.service.Service;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class OrgMembersAdd
 */
@WebServlet(name = "/OrgMembersAdd", urlPatterns = "/org/members/add")
public class OrgMembersAdd extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private int resultCode;   
    private JsonObject jsonResult;
    private JsonObject jsonData;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	Integer identity = Integer.valueOf(request.getParameter("identity"));
    	
    	long Oid = Long.valueOf(request.getParameter("Oid"));
    	long Uid = Long.valueOf(request.getParameter("Uid"));
    	
    	resultCode = service.addOrgMember(Oid, Uid, identity);
    	if(resultCode == ResultCode.SUCCESS){
    		jsonData = new JsonObject();
    		jsonData.addProperty("Oid", Oid);
    		
    		jsonResult = new JsonObject();
    		jsonResult.addProperty("resultCode", ResultCode.SUCCESS);
    		jsonResult.addProperty("resultMessage", "添加成功！");
    		jsonResult.add("data", jsonData);
    		
    		Gson gson = new Gson();
    		out.write(gson.toJson(jsonResult));
    		System.out.println(gson.toJson(jsonResult));
    	}else {
    		jsonData = new JsonObject();
    		jsonData.addProperty("Oid", "null");
    		
    		jsonResult = new JsonObject();
    		jsonResult.addProperty("resultCode", resultCode);
    		jsonResult.addProperty("resultMessage", "添加失败！");
    		jsonResult.add("data", jsonData);
    		
    		Gson gson = new Gson();
    		out.write(gson.toJson(jsonResult));
    		System.out.println(gson.toJson(jsonResult));
    	}
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
}
