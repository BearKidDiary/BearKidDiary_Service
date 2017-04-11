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
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

/**
 * Servlet implementation class GetAllOrgs
 */
@WebServlet("/org/all")
public class GetAllOrgs extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private PrintWriter out = null;
    private List<Organization> list;
    private Result<List<Organization>> result;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		result = new Result<>();
		list = service.getAllOrgs();
		if(!list.isEmpty()){
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMessage("获取所有机构成功！");
			result.setData(list);
		}else{
			result.setResultCode(ResultCode.ERROR_NO_ORG);
			result.setResultMessage("还没有任何机构！");
		}
		out.write(gson.toJson(result));
	}

}
