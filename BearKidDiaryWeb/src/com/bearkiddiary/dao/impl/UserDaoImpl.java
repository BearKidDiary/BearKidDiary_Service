package com.bearkiddiary.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.UserDao;

public class UserDaoImpl extends BaseDaoHibernate<User> implements UserDao{

	/**
	 * ���ظ����û���¼��������ƥ������ݸ���
	 */
	@Override
	public int Login(String Uphone, String Upsw) {
		String hql = "select distinct user from User user where user.Uphone = ?0 and user.Upsw = ?1";
		List<User> list = new ArrayList();
		list = find(hql,Uphone,Upsw);
		return list.size();
	}

	/**
	 * ע��ɹ������û�id
	 */
	@Override
	public int Register(User user) {
		int Uid = (int) save(user);
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

}
