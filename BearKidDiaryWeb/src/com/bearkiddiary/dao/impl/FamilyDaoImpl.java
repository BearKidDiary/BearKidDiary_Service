package com.bearkiddiary.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.FamilyDao;
import com.bearkiddiary.dao.UserDao;

public class FamilyDaoImpl extends BaseDaoHibernate<Family> implements FamilyDao {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int createFamily(String Uphone, String Fname) {

		User creator = userDao.getUser(Uphone);
		if (creator == null) {// 不存在手机号码Uphone的用户
			return ERROR_NO_USER;
		}

		if (getCreatedFramily(Uphone) != null) {// 已经有创建的家庭了
			return ERROR_ALREADY_HAVE_FAMILY;
		}

		Family family = new Family();
		family.setFname(Fname);
		family.setCreator(creator);
		save(family);
		return SUCESS;
	}

	@Override
	public Family getCreatedFramily(String Uphone) {
		final String hql = "select * from Family where Family.Uid = User.Uid and User.Uphone = " + Uphone;
		List<Family> list = find(hql);
		if (list.size() == 0)
			return null;
		else
			return list.get(0);
	}

	@Override
	public Set<Family> getAttendedFramily(String Uphone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<User> getMembersInFamily(long Fid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<User> getMembersInFamily(String Uphone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<User> getCreatorInFamily(long Fid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<User> getMembersAndCreatorInFamily(long Fid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<User> getMembersAndCreatorInFamily(String Uphone) {
		// TODO Auto-generated method stub
		return null;
	}

}
