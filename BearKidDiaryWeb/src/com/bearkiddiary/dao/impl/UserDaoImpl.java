package com.bearkiddiary.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.utils.ResultCode;

public class UserDaoImpl extends BaseDaoHibernate<User> implements UserDao {

	
	@Override
	public int Login(String Uphone, String Upsw) {
		String hql = "select distinct user from User user where user.Uphone = ?0 and user.Upsw = ?1";
		List<User> list = new ArrayList();
		list = find(hql, Uphone, Upsw);
		return list.size();
	}

	@Override
	public Long Register(User user) {
		Long Uid = (Long) save(user);
		return Uid;
	}

	@Override
	public int Valid(String Uphone) {
		String hql = "select distinct user from User user where user.Uphone = ?0";
		List<User> list = new ArrayList<>();
		list = find(hql, Uphone);
		return list.size();
	}

	@Override
	public int updateName(String Uphone, String Uname) {
		String hql = "update User user set user.Uname = ?0 where user.Uphone = ?1";
		return update(hql, Uname, Uphone);
	}

	@Override
	public int updateArea(String Uphone, String Uarea) {
		String hql = "update User user set user.Uarea = ?0 where user.Uphone = ?1";
		return update(hql, Uarea, Uphone);
	}

	@Override
	public int updateEmail(String Uphone, String Uemail) {
		String hql = "update User user set user.Uemail = ?0 where user.Uphone = ?1";
		return update(hql, Uemail, Uphone);
	}

	@Override
	public User getUser(String Uphone) {
		final String hql = "select user from User user where user.Uphone = ?0";
		List<User> list = find(hql, Uphone);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public User getUser(Long Uid) {
		final String hql = "select user from User user where user.Uid = ?0";
		List<User> list = find(hql, Uid);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateUser(String Uphone, User user) {
		User u = getUser(Uphone);
		if(u == null)
			return ResultCode.ERROR_NO_USER;
		if(user.getUname() != null)
			u.setUname(user.getUname());
		if(user.getUarea() != null)
			u.setUarea(user.getUarea());
		if(user.getUemail() != null)
			u.setUemail(user.getUemail());
		if(user.getUsex() != null)
			u.setUsex(user.getUsex());
		update(u);
		return ResultCode.SUCCESS;
	}
}
