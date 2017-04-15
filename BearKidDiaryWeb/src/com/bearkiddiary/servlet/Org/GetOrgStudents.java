package com.bearkiddiary.servlet.Org;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

import sun.print.resources.serviceui_pt_BR;

/**
 * Servlet implementation class GetOrgStudents
 */
@WebServlet("/org/students/get")
public class GetOrgStudents extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Result<List<Kid>> result;
	private PrintWriter out;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		result = new Result<>();
		out = response.getWriter();
		
		String sOid = request.getParameter(Organization.OID);
		String Uphone = request.getParameter(User.PHONE);
		
		if(sOid == null && Uphone == null){
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("缺少请求参数！");
		}else{
			Long Oid = Long.valueOf(sOid);
			List<Kid> list = service.getOrgStudents(Oid, Uphone);
			if(list == null){
				result.setResultCode(ResultCode.ERROR_NO_KID);
				result.setResultMessage("该机构不存在学生！");
			}else{
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMessage("获取该机构学生列表成功！");
				result.setData(list);
			}
		}
		out.print(gson.toJson(result));
	}

}
