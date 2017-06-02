package com.bearkiddiary.servlet.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ImageUtil;
import com.bearkiddiary.utils.ParameterDecode;
import com.bearkiddiary.utils.ResultCode;

/**
 * Servlet implementation class UpdateUserInfo
 */
@WebServlet(name = "UpdateUserInfo", urlPatterns = "/user/updateinfo")
public class UserInfoUpdate extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Result<User> result = null;
	private User user = null;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		user = new User();
		result = new Result<>();

		String Uphone = request.getParameter(User.PHONE);
		String Uname = request.getParameter(User.NAME);
		String Uarea = request.getParameter(User.AREA);
		String Uemail = request.getParameter(User.EMAIL);
		String Usex = request.getParameter(User.SEX);
		String Uavatar = request.getParameter(User.AVATAR);
		
		if(Uphone == null){
			result.setData(null);
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("请求参数不完整");
		}else{
			if (Uname != null) {
				// 解码
//				Uname = ParameterDecode.decode(Uname);
				user.setUname(Uname);
			} 
			if (Uarea != null) {
//				Uarea = ParameterDecode.decode(Uarea);
				user.setUarea(Uarea);
			} 
			if (Uemail != null) {
//				Uemail = ParameterDecode.decode(Uemail);
				user.setUemail(Uemail);
			}
			if(Usex != null){
//				Usex = ParameterDecode.decode(Usex);
				user.setUsex(Usex);
			}
			if(Uavatar != null){
//				Uavatar = ParameterDecode.decode(Uavatar);
				user.setUavatar(Uavatar + ".jpg");
				
				String Uimage = request.getParameter("Uimage"); 
				if(ImageUtil.saveImage(Uimage, Uavatar, imagePath + "/avatar")){
					System.out.print("保存图片成功！");
				}
			}else {
				System.out.println("没有任何数据！");
			}
			
			int resultCode = service.updateUserInfo(Uphone, user);
			
			if(resultCode == ResultCode.SUCCESS){
				result.setData(null);
				result.setResultCode(resultCode);
				result.setResultMessage("更新成功");
			}else{
				result.setData(null);
				result.setResultCode(resultCode);
				result.setResultMessage("更新失败");
			}
		}
		out.write(gson.toJson(result));
		System.out.println(gson.toJson(result));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
