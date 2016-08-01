package com.bearkiddiary.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.UserDao;

public class UserDaoImpl extends BaseDaoHibernate<User> implements UserDao{

	/**
	 * 返回根据用户登录名，密码匹配的数据个数
	 */
	@Override
	public int Login(String Uphone, String Upsw) {
		String hql = "select distinct user from User user where user.Uphone = ?0 and user.Upsw = ?1";
		List<User> list = new ArrayList();
		list = find(hql,Uphone,Upsw);
		return list.size();
	}

	/**
	 * 注册成功返回用户id
	 */
	@Override
	public int Register(User user) {
		int Uid = (int) save(user);
		return Uid;
	}

	/**
	 * 验证是否该号码已经注册过，即数据库是否已有这个号码
	 */
	@Override
	public int Valid(String Uphone) {
		String hql = "select distinct user from User user where user.Uphone = ?0";
		List<User> list = new ArrayList<>();
		list = find(hql, Uphone);
		return list.size();
	}

}
