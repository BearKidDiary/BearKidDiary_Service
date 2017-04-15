package com.bearkiddiary.servlet.User;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class AddContact
 */
@WebServlet("/user/contact/add")
public class AddContact extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private Result<User> result;
    private PrintWriter out;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		result = new Result<>();
		out = response.getWriter();
		
		String Uphone = request.getParameter(User.PHONE);
		String contactPhone = request.getParameter("ContactPhone");
		
		if(Uphone == null && contactPhone == null){
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("缺少请求参数！");
			
		}else{
			if(service.addContact(Uphone, contactPhone) && service.addContact(contactPhone, Uphone)){
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMessage("添加联系人成功！");
			}else{
				result.setResultCode(ResultCode.ERROR);
				result.setResultMessage("添加联系人失败！");
			}
		}
		out.write(gson.toJson(result));
	}

}
