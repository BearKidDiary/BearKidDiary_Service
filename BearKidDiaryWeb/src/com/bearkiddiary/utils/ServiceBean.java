package com.bearkiddiary.utils;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bearkiddiary.service.AttendanceGroupService;
import com.bearkiddiary.service.Service;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class ServiceBean {
	private static Service service;
	private static AttendanceGroupService AGroupService;
	public ServiceBean(){}
	
	public static Service getService(ServletContext servletContext){
		service = (Service) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("service");
		return service;
	}
	
	public static AttendanceGroupService getAGService(ServletContext servletContext){
		AGroupService = (AttendanceGroupService) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("attendanceGroupService");
		return AGroupService;
	}
	
}
