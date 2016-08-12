package com.bearkiddiary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.utils.ResultCode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class CreateOrgServlet
 */
@WebServlet(name = "CreateOrgServlet", urlPatterns = "/org/create")
public class CreateOrgServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private JsonObject jsonObject;
	private JsonObject jsonData;
	private Result<Organization> result;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer type = Integer.valueOf(request.getParameter("org"));
		System.out.print(type);
		doOrgOperate(type, request, response);
	}
	
	public void doOrgOperate(Integer type, HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = null;
		out = response.getWriter();
		
		String Oname = request.getParameter(Organization.ONAME);
		String Oaddress = request.getParameter(Organization.OADDRESS);
		String Oannounce = request.getParameter(Organization.OANNOUNCE);
		
		if(type == Organization.CREATE){
			Organization org = new Organization();
			org.setOname(Oname);
			org.setOaddress(Oaddress);
			org.setOannounce(Oannounce);
			
			Long Uid = Long.valueOf(request.getParameter(Organization.UID));
			//创建成功返回的Oid
			Long Oid = service.createOrg(Oname, Oaddress, Oannounce, Uid);
			
			result = new Result<>();
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMessage("创建成功");
			result.setData(org);
			response.getWriter().write(new Gson().toJson(result));
			System.out.println("创建成功：" + Oid);
		}else if(type == Organization.DELETE){ // 删除机构
			
			long Oid = Long.valueOf(request.getParameter("Oid"));
			service.deleteOrg(Oid);
			System.out.println("删除成功！");
		}else if(type == Organization.UPDATE){
			
			long Oid = Long.valueOf(request.getParameter("Oid"));
			if(Oname != null){
				service.updateOrg(Oid, Organization.ONAME, Oname);
			}else if(Oaddress != null){
				service.updateOrg(Oid, Organization.OADDRESS, Oaddress);
			}else if(Oannounce != null){
				service.updateOrg(Oid, Organization.OANNOUNCE, Oannounce);
			}
			
			System.out.println("修改数据！");
		}
	}

}
