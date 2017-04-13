package com.bearkiddiary.servlet.course;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Course;
import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

/**
 * Servlet implementation class CourseStudentsGet
 */
@WebServlet("/course/students/get")
public class CourseStudentsGet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private List<Kid> list;
    private Result<List<Kid>> result;
    private PrintWriter out;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		result = new Result<>();
		list = new ArrayList<>();
		
		String sCid = request.getParameter("Cid");
		if(sCid != null){
			Long Cid = Long.valueOf(sCid);
			list = service.getKidsInCourse(Cid);
			if(list.isEmpty()){
				result.setResultCode(ResultCode.ERROR_NO_KID);
				result.setResultMessage("该课程还没有任何学生！");
			}else{
				result.setData(list);
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMessage("获取课程学生列表成功！");
			}	
		}else{
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("缺少请求参数！");
		}
		out.write(gson.toJson(result));
	}

}
