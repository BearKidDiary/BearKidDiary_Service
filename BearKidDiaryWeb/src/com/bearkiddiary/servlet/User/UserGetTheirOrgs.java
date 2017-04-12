package com.bearkiddiary.servlet.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class UserGetTheirOrgs
 */
@WebServlet("/user/orgs")
public class UserGetTheirOrgs extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private Result<Map<String, List<Organization>>> result;
    private PrintWriter out;
    private Map<String, List<Organization>> map;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		result = new Result<>();
		map = new HashMap<>();
		String Uphone = request.getParameter(User.PHONE);
		if(Uphone == null){
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("缺少请求参数！");
		}else{
			map = service.getUserOrganizations(Uphone);
			if(!map.isEmpty()){
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMessage("获取该用户的所有机构成功！");
				result.setData(map);
			}else{
				result.setResultCode(ResultCode.ERROR_NO_ORG);
				result.setResultMessage("该用户还有参与任何机构！");
				result.setData(map);
			}
		}
		
		out.write(gson.toJson(result));
	}

}
