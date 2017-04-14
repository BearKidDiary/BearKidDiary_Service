package com.bearkiddiary.servlet.Org;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.AttendanceGroup;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.service.AttendanceGroupService;
import com.bearkiddiary.servlet.AGBaseServlet;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;
import com.bearkiddiary.utils.ServiceBean;

/**
 * Servlet implementation class OrgGetAttendanceGroup
 */
@WebServlet("/org/attendancegroup")
public class OrgGetAttendanceGroup extends AGBaseServlet {
	private static final long serialVersionUID = 1L;
    
	private PrintWriter out = null;
	private Result<List<AttendanceGroup>> result;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		result = new Result<>();
		
		String Uphone = request.getParameter(User.PHONE);
		if(Uphone == null){
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("�������������");
			result.setData(null);
		}else{
			List<AttendanceGroup> list = AGservice.getOrgAttendanceGroupList(Uphone);
			if(list == null){
				result.setResultCode(ResultCode.ERROR_NO_ATTENDANCEGROUP);
				result.setResultMessage("�����ڿ������б�");
				result.setData(list);
			}else{
				result.setResultCode(ResultCode.SUCCESS);
				result.setResultMessage("��ȡ�����Ŀ�����ɹ�");
				result.setData(list);
			}
		}
		out.write(gson.toJson(result));
	}
	
	@Override
	public void destroy() {
		out.close();
		super.destroy();
	}
}
