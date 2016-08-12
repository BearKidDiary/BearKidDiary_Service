package com.bearkiddiary.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.Result;
import com.bearkiddiary.dao.FamilyDao;
import com.bearkiddiary.dao.KidDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.dao.impl.FamilyDaoImpl;
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/test")
public class TestServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result<Family> result = new Result<>();
		PrintWriter out = resp.getWriter();

		String Uphone = req.getParameter("Uphone");
		if (Uphone == null) {
			out.write("Uphone==null");
			return;
		}

//		Family family = service.getCreatedFamily(Uphone);
//		out.write(gson.toJson(family));
	}
}
