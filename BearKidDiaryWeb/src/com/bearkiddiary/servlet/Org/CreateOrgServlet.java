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
import com.bearkiddiary.bean.User;
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
	private Result<Organization> result;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		
		result = new Result<>();
		
		if(type == Organization.CREATE){
			Organization org = new Organization();
			org.setOname(Oname);
			org.setOaddress(Oaddress);
			org.setOannounce(Oannounce);
			
//			Long Uid = Long.valueOf(request.getParameter(Organization.UID));
			//机构管理员手机号码
			String Uphone = request.getParameter(User.PHONE);
			//创建成功返回的Oid
			Long resultCode = service.createOrg(Oname, Oaddress, Oannounce, Uphone);
			if(resultCode > 0){
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMessage("创建成功");
				org.setOid(resultCode);
				result.setData(org);
			}else {
				result.setResultCode(ResultCode.ERROR_EXIST_ORG);
				result.setResultMessage("创建失败，该用户已经创建过机构了");
			}
			
			responseWriter(response, gson.toJson(result));
			System.out.println("创建成功：" + resultCode);
		}else if(type == Organization.DELETE){ // 删除机构
			//获取客户端传递的机构id
//			long Oid = Long.valueOf(request.getParameter("Oid"));
			String Uphone = request.getParameter(User.PHONE);
			int count = service.deleteOrg(Uphone);
			
			if(count > 0){
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMessage("删除成功！");
			}else {
				result.setResultCode(ResultCode.ERROR);
				result.setResultMessage("删除失败！");
			}
			
			responseWriter(response, gson.toJson(result));
		}else if(type == Organization.UPDATE){ //修改机构
			
			//获取客户端传递的机构id
//			long Oid = Long.valueOf(request.getParameter("Oid"));
			String Uphone = request.getParameter(User.PHONE);
			int count = 0;
			if(Oname != null){
				
				count = service.updateOrg(Uphone, Organization.ONAME, Oname);
				if(count > 0){
					result.setResultCode(ResultCode.SUCCESS);
					result.setResultMessage("修改名字成功!");
				}else {
					result.setResultCode(ResultCode.ERROR);
					result.setResultMessage("修改名字失败!");
				}
				responseWriter(response, gson.toJson(result));
			}
			if(Oaddress != null){
				
				count = service.updateOrg(Uphone, Organization.OADDRESS, Oaddress);
				if(count > 0){
					result.setResultCode(ResultCode.SUCCESS);
					result.setResultMessage("修改地址成功!");
				}else {
					result.setResultCode(ResultCode.ERROR);
					result.setResultMessage("修改地址失败!");
				}
				responseWriter(response, gson.toJson(result));
			}
			if(Oannounce != null){
				
				count = service.updateOrg(Uphone, Organization.OANNOUNCE, Oannounce);
				if(count > 0){
					result.setResultCode(ResultCode.SUCCESS);
					result.setResultMessage("修改公告成功!");
				}else {
					result.setResultCode(ResultCode.ERROR);
					result.setResultMessage("修改公告失败!");
				}
				responseWriter(response, gson.toJson(result));
			}
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
