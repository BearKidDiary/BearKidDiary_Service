package com.bearkiddiary.servlet.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Result;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ImageUtil;
import com.bearkiddiary.utils.ResultCode;

/**
 * Servlet implementation class UserUploadPicture
 */
@WebServlet("/user/pictures/upload")
public class UserUploadPicture extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Result result;
	private PrintWriter out;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		result = new Result();
		out = response.getWriter();
		
		String Uphone = request.getParameter(User.PHONE);
		String PimageName = request.getParameter("PimageName");
		String Pimage = request.getParameter("Pimage");
//		String PimageName2 = request.getParameter("PimageName2");
//		String Pimage2 = request.getParameter("Pimage2");
//		String PimageName3 = request.getParameter("PimageName3");
//		String Pimage3 = request.getParameter("Pimage3");
//		String PimageName4 = request.getParameter("PimageName4");
//		String Pimage4 = request.getParameter("Pimage4");
//		String PimageName5 = request.getParameter("PimageName5");
//		String Pimage5 = request.getParameter("Pimage5");
//		String PimageName6 = request.getParameter("PimageName6");
//		String Pimage6 = request.getParameter("Pimage6");
		
		if(Uphone == null){
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("缺少请求参数！");
		}else{
			Long Ptime = new Date().getTime();
			int code = 0;
			if(Pimage != null){
				if(ImageUtil.saveImage(Pimage, PimageName, imagePath + "/pictures")){
					code = service.savePicture(Uphone, PimageName + ".jpg", Ptime);
					if(code == ResultCode.ERROR){
						result.setResultCode(code);
						result.setResultMessage("保存图片出错！");
					}else if(code == ResultCode.ERROR_NO_USER){
						result.setResultCode(code);
						result.setResultMessage("不存在该用户！");
					}else if(code == ResultCode.SUCCESS){
						result.setResultCode(code);
						result.setResultMessage("保存图片成功！");
					}
				}else{
					result.setResultCode(ResultCode.ERROR);
					result.setResultMessage("保存图片失败！");
				}
			}
//			if(Pimage2 != null){
//				if(ImageUtil.saveImage(Pimage2, PimageName2, imagePath + "/pictures")){
//					code = service.savePicture(Uphone, PimageName2 + ".jpg", Ptime);
//				}else{
//					result.setResultCode(ResultCode.ERROR);
//					result.setResultMessage("保存图片失败！");
//				}
//			}
//			if(Pimage3 != null){
//				if(ImageUtil.saveImage(Pimage3, PimageName3, imagePath + "/pictures")){
//					code = service.savePicture(Uphone, PimageName3 + ".jpg", Ptime);
//				}else{
//					result.setResultCode(ResultCode.ERROR);
//					result.setResultMessage("保存图片失败！");
//				}
//			}
//			if(Pimage4 != null){
//				if(ImageUtil.saveImage(Pimage4, PimageName4, imagePath + "/pictures")){
//					code = service.savePicture(Uphone, PimageName4 + ".jpg", Ptime);
//				}else{
//					result.setResultCode(ResultCode.ERROR);
//					result.setResultMessage("保存图片失败！");
//				}
//			}
//			
//			if(Pimage2 != null){
//				if(ImageUtil.saveImage(Pimage5, PimageName5, imagePath + "/pictures")){
//					code = service.savePicture(Uphone, PimageName5 + ".jpg", Ptime);
//				}else{
//					result.setResultCode(ResultCode.ERROR);
//					result.setResultMessage("保存图片失败！");
//				}
//			}
			
		}
		out.write(gson.toJson(result));
	}

}
