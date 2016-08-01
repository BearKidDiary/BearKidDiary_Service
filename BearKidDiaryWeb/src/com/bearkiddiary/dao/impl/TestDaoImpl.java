package com.bearkiddiary.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.TestDao;

public class TestDaoImpl extends BaseDaoHibernate<User> implements TestDao{

	/**
	 * ≤‚ ‘
	 */
	@Override
	public List<User> Login(User user) {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<>();
		String hql = "select distinct user from User user where user.name = ?0 and user.password = ?1";
		list = find(hql, user.getUphone(), user.getUpsw());
		return list;
	}

	@Override
	public int Register(User user) {
		// TODO Auto-generated method stub
		return (int) save(user);
	}

}
