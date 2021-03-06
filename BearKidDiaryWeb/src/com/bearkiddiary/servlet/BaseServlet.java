package com.bearkiddiary.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearkiddiary.service.Service;
import com.bearkiddiary.utils.ServiceBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected Service service;
	protected Gson gson;
	protected String imagePath;

	@Override
	public void init() throws ServletException {
		service = ServiceBean.getService(getServletContext());
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		imagePath = getServletContext().getRealPath("images");
	}
}
