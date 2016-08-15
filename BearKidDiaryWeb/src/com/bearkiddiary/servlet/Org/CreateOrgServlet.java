package com.bearkiddiary.servlet.Org;

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
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 1、创建机构
 * 2、删除机构
 * 3、修改机构（机构名，机构地址，机构公告）
 */
/**
 * Servlet implementation class CreateOrgServlet
 */
@WebServlet(name = "CreateOrgServlet", urlPatterns = "/org/create")
public class CreateOrgServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private JsonObject jsonResult; 
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
	
	/**
	 * 操作机构函数
	 * @param type
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void doOrgOperate(Integer type, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
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
			
			responseWriter(response, new Gson().toJson(result));
			System.out.println("创建成功：" + Oid);
		}else if(type == Organization.DELETE){ // 删除机构
			//获取客户端传递的机构id
			long Oid = Long.valueOf(request.getParameter("Oid"));
			service.deleteOrg(Oid);
			
			jsonData = new JsonObject();
			jsonData.addProperty("Oid", Oid);
			jsonData.addProperty("Oname", Oname);
			
			jsonResult = new JsonObject();
			jsonResult.addProperty("resultCode", ResultCode.SUCCESS);
			jsonResult.addProperty("resultMessage", "删除成功！");
			jsonResult.add("Data", jsonData);
			responseWriter(response, new Gson().toJson(jsonResult));
			System.out.println("删除成功！");
		}else if(type == Organization.UPDATE){ //修改机构
			
			//获取客户端传递的机构id
			long Oid = Long.valueOf(request.getParameter("Oid"));
			if(Oname != null){
				service.updateOrg(Oid, Organization.ONAME, Oname);
			}else if(Oaddress != null){
				service.updateOrg(Oid, Organization.OADDRESS, Oaddress);
			}else if(Oannounce != null){
				service.updateOrg(Oid, Organization.OANNOUNCE, Oannounce);
			}
			
			//返回的json数据
			jsonData = new JsonObject();
			jsonData.addProperty("Oid", Oid);
			jsonData.addProperty("Oname", Oname);
			
			jsonResult = new JsonObject();
			jsonResult.addProperty("resultCode", ResultCode.SUCCESS);
			jsonResult.addProperty("resultMessage", "修改成功");
			jsonResult.add("Data", jsonData);
			
			responseWriter(response, new Gson().toJson(jsonResult));
			System.out.println("修改数据！");
		}
	}
	
	/**
	 * 打印函数
	 * @param response
	 * @param Object
	 * @throws IOException
	 */
	public void responseWriter(HttpServletResponse response, String Object) throws IOException{
		PrintWriter out = response.getWriter();
		out.write(Object);
	}

}
