package com.bearkiddiary.utils;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bearkiddiary.service.Service;

public class ServiceBean {
	private static Service service;
	public ServiceBean(){}
	
	public static Service getService(ServletContext servletContext){
		service = (Service) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("service");
		return service;
	}
	
}
