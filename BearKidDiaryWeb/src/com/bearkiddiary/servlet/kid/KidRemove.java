package com.bearkiddiary.servlet.kid;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.servlet.BaseServlet;
import com.bearkiddiary.utils.ResultCode;

@WebServlet("/kid/remove")
public class KidRemove extends BaseServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Result<Kid> result = new Result<>();
		
		String sKid = req.getParameter("Kid");
		if(sKid==null){
			result.setResultCode(ResultCode.ERROR_MISSING_PARAMETER);
			result.setResultMessage("璇锋眰鍙傛暟涓嶅畬鏁�");
			out.write(gson.toJson(result));
			out.close();
			return;
		}
		
		Long Kid = Long.valueOf(sKid);
		int code = service.removeKid(Kid);
		
		result.setResultCode(code);
		if(code==ResultCode.ERROR_NO_KID){
			result.setResultMessage("涓嶅瓨鍦ㄨ瀛╁瓙");
		}
		if(code == ResultCode.SUCCESS){
			result.setResultMessage("鍒犻櫎鎴愬姛");
		}
		
		out.write(gson.toJson(result));
	}
}
