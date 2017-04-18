package com.bearkiddiary.servlet.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

/**
 * 获取联系人列表
 */
@WebServlet("/user/contact/get")
public class GetContacts extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private Result<List<User>> result;
    private PrintWriter out;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		result = new Result<>();
		out = response.getWriter();
		
		String Uphone = request.getParameter(User.PHONE);
		if(Uphone == null){
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("缺少请求参数！");
		}else{
			List<User> list = service.getContacts(Uphone);
			if(list.isEmpty()){
				result.setResultCode(ResultCode.ERROR_NO_CONTACT);
				result.setResultMessage("该用户还没有联系人！");
			}else{
				result.setData(list);
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMessage("获取联系人列表成功！");
			}
		}
		out.write(gson.toJson(result));
	}

}
