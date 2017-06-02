package com.bearkiddiary.servlet.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

/**
 * Servlet implementation class UserGetPictures
 */
@WebServlet("/user/pictures/get")
public class UserGetPictures extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private Result<List<String>> result;
    private PrintWriter out;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		result = new Result<>();
		out = response.getWriter();
		
		String Uphone = request.getParameter(User.PHONE);
		String sPageNum = request.getParameter("PageNum");
		String sPageSize = request.getParameter("PageSize");
		String Order = request.getParameter("Order");
		
		if(Uphone == null){
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("缺少请求参数！");
		}else{
			int PageNum = 0;
			int PageSize = 0;
			if(sPageNum != null){
				PageNum = Integer.valueOf(sPageNum); 
			}
			if(sPageSize != null){
				PageSize = Integer.valueOf(sPageSize);
			}
			List<String> list = service.getPictures(Uphone, Order, PageNum, PageSize);
			result.setResultCode(ResultCode.SUCCESS);
			result.setResultMessage("获取相册成功！");
			result.setData(list);
		}
		
		out.write(gson.toJson(result));
	}

}
