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
 * 1����������
 * 2��ɾ������
 * 3���޸Ļ�������������������ַ���������棩
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
	 * ������������
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
			
			Long Uid = Long.valueOf(request.getParameter(Organization.UID));
			//�����ɹ����ص�Oid
			Long Oid = service.createOrg(Oname, Oaddress, Oannounce, Uid);
			if(Oid > 0){
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMessage("�����ɹ�");
				result.setData(org);
			}else {
				result.setResultCode(ResultCode.ERROR_COMMIT);
				result.setResultMessage("����ʧ��");
			}
			
			responseWriter(response, gson.toJson(result));
			System.out.println("�����ɹ���" + Oid);
		}else if(type == Organization.DELETE){ // ɾ������
			//��ȡ�ͻ��˴��ݵĻ���id
			long Oid = Long.valueOf(request.getParameter("Oid"));
			int count = service.deleteOrg(Oid);
			
			if(count > 0){
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMessage("ɾ���ɹ���");
			}else {
				result.setResultCode(ResultCode.ERROR);
				result.setResultMessage("ɾ��ʧ�ܣ�");
			}
			
			responseWriter(response, gson.toJson(result));
		}else if(type == Organization.UPDATE){ //�޸Ļ���
			
			//��ȡ�ͻ��˴��ݵĻ���id
			long Oid = Long.valueOf(request.getParameter("Oid"));
			int count = 0;
			if(Oname != null){
				
				count = service.updateOrg(Oid, Organization.ONAME, Oname);
				if(count > 0){
					result.setResultCode(ResultCode.SUCCESS);
					result.setResultMessage("�޸����ֳɹ�!");
				}else {
					result.setResultCode(ResultCode.ERROR);
					result.setResultMessage("�޸�����ʧ��!");
				}
				responseWriter(response, gson.toJson(result));
			}
			if(Oaddress != null){
				
				count = service.updateOrg(Oid, Organization.OADDRESS, Oaddress);
				if(count > 0){
					result.setResultCode(ResultCode.SUCCESS);
					result.setResultMessage("�޸ĵ�ַ�ɹ�!");
				}else {
					result.setResultCode(ResultCode.ERROR);
					result.setResultMessage("�޸ĵ�ַʧ��!");
				}
				responseWriter(response, gson.toJson(result));
			}
			if(Oannounce != null){
				
				count = service.updateOrg(Oid, Organization.OANNOUNCE, Oannounce);
				if(count > 0){
					result.setResultCode(ResultCode.SUCCESS);
					result.setResultMessage("�޸Ĺ���ɹ�!");
				}else {
					result.setResultCode(ResultCode.ERROR);
					result.setResultMessage("�޸Ĺ���ʧ��!");
				}
				responseWriter(response, gson.toJson(result));
			}
			System.out.println("�޸����ݣ�");
		}
	}
	
	/**
	 * ��ӡ����
	 * @param response
	 * @param Object
	 * @throws IOException
	 */
	public void responseWriter(HttpServletResponse response, String Object) throws IOException{
		PrintWriter out = response.getWriter();
		out.write(Object);
	}

}
