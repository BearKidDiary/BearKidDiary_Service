package com.bearkiddiary.service;

import com.bearkiddiary.bean.User;

public interface Service {
	
	/**
	 * 测试
	 * @param user 获取的用户
	 * @return
	 */
	//登录功能
	boolean Login(String Uphone, String Upsw);
	boolean Register(User user);
	
	/**
	 * 更新操作
	 * @param Parameter
	 * @param value
	 * @return
	 */
	int update(String Uphone, String Parameter, String value);
}
