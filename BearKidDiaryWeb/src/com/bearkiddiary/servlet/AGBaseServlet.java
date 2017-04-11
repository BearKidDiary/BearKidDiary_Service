package com.bearkiddiary.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.service.AttendanceGroupService;
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class AGBaseServlet
 */
@WebServlet("/AGBaseServlet")
public class AGBaseServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	protected AttendanceGroupService AGservice = null;
	protected Gson gson = null;
	
	@Override
	public void init() throws ServletException {
		AGservice = ServiceBean.getAGService(getServletContext());
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		super.init();
	}

}
