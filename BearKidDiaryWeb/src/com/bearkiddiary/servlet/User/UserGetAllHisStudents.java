package com.bearkiddiary.servlet.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

/**
 * 教师获取其当前所有的学生
 */
@WebServlet("/user/students/get")
public class UserGetAllHisStudents extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private Result<List<Kid>> result; 
    private List<Kid> list;
    private PrintWriter out;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		result = new Result<>();
		list = new ArrayList<>();
		out = response.getWriter();
		
		String Uphone = request.getParameter(User.PHONE);
		if(Uphone != null){
			list = service.getAllHisStudents(Uphone);
			if(list.isEmpty()){
				result.setResultCode(ResultCode.ERROR_NO_KID);
				result.setResultMessage("该用户还没有学生！");
			}else{
				result.setData(list);
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMessage("获取所教的所有学生成功！");
			}
		}else{
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("缺少请求参数！");
		}
		
		out.write(gson.toJson(result));
	}

}
