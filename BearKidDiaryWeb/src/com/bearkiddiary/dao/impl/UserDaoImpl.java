package com.bearkiddiary.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.UserDao;

public class UserDaoImpl extends BaseDaoHibernate<User> implements UserDao {

	/**
	 * ���ظ����û���¼��������ƥ������ݸ���
	 */
	@Override
	public int Login(String Uphone, String Upsw) {
		String hql = "select distinct user from User user where user.Uphone = ?0 and user.Upsw = ?1";
		List<User> list = new ArrayList();
		list = find(hql, Uphone, Upsw);
		return list.size();
	}

	/**
	 * ע��ɹ������û�id
	 */
	@Override
	public Long Register(User user) {
		Long Uid = (Long) save(user);
		return Uid;
	}

	/**
	 * ��֤�Ƿ�ú����Ѿ�ע����������ݿ��Ƿ������������
	 */
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
}
