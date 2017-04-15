package com.bearkiddiary.servlet.Org;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

/**
 * Servlet implementation class GetOrgTeachers
 */
@WebServlet("/org/teachers/get")
public class GetOrgTeachers extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private Result<List<User>> result;
    private PrintWriter out;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		result = new Result<>();
		
		String sOid = request.getParameter(Organization.OID);
		String Uphone = request.getParameter(User.PHONE);
		if(sOid == null && Uphone == null){
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("缺少请求参数！");
		}else{
			Long Oid = Long.valueOf(sOid);
			List<User> list = service.getOrgTeachers(Oid, Uphone);
			if (list == null) {
				result.setResultCode(ResultCode.ERROR_NO_ORG);
				result.setResultMessage("该机构不存在教师！");
			}else{
				result.setData(list);
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMessage("获取机构教师列表成功！");
			}
		}
		
		out.write(gson.toJson(result));
	}

}
