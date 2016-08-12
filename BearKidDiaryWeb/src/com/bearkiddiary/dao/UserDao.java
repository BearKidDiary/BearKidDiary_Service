package com.bearkiddiary.dao;

import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.BaseDao;

public interface UserDao extends BaseDao<User> {
	/**
	 * 用户登录
	 * 
	 * @param user
	 *            用户数据
	 * @return
	 */
	int Login(String Uphone, String Upsw);

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	int Register(User user);

	/**
	 * 检测该号码是否被注册过
	 * 
	 * @param Uphone
	 * @return
	 */
	int Valid(String Uphone);

	int updateName(String Uphone, String Uname);

	int updateArea(String Uphone, String Uarea);

	int updateEmail(String Uphone, String Uemail);

	/**
	 * 获取手机号码为Uphone的用户
	 * 
	 * @param Uphone
	 */
	User getUser(String Uphone);

	/**
	 * 获取编号为Uid的用户
	 * 
	 * @param Uid
	 */
	User getUser(Long Uid);
}
