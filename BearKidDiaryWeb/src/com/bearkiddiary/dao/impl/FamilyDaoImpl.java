package com.bearkiddiary.dao.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.FamilyDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.utils.ResultCode;

public class FamilyDaoImpl extends BaseDaoHibernate<Family> implements FamilyDao {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Family getFamily(Long Fid) {
		final String hql = "select family from Family family where family.Fid = ?0";
		List<Family> list = find(hql, Fid);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public int createFamily(String Uphone, String Fname) {
		User creator = userDao.getUser(Uphone);
		if (creator == null) {// 不存在手机号码Uphone的用户
			return ResultCode.ERROR_NO_USER;
		}

		if (getCreatedFramily(Uphone) != null) {// 已经有创建的家庭了
			return ResultCode.ERROR_ALREADY_HAVE_FAMILY;
		}

		Family family = new Family();
		family.setFname(Fname);
		family.setCreator(creator);
		save(family);
		return ResultCode.SUCCESS;
	}

	@Override
	public Family getCreatedFramily(String Uphone) {
		final String hql = "select family from Family family join family.creator creator where creator.Uphone = ?0";
		List<Family> list = find(hql, Uphone);
		if (list == null || list.size() == 0)
			return null;
		else
			return list.get(0);
	}

	@Override
	public Set<Family> getAttendedFramily(String Uphone) {
		final String hql = "select family from Family as family join family.members as member where member.Uphone = ?0";
		List<Family> list = find(hql, Uphone);
		return new HashSet<>(list);
	}

	@Override
	public Set<User> getMembersInFamily(long Fid) {
		final String hql = "select user from User as user join user.accessFamily as family where family.Fid = ?0";
		List<User> list = userDao.find(hql, Fid);
		return new HashSet<>(list);
	}

	@Override
	public Set<User> getMembersInFamily(String Uphone) {
		Family family = getCreatedFramily(Uphone);
		return getMembersInFamily(family.getFid());
	}

	@Override
	public User getCreatorInFamily(long Fid) {
		final String hql = "select user from User user join user.ownFamily family where family.Fid = ?0";
		List<User> list = userDao.find(hql, Fid);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public Set<User> getMembersAndCreatorInFamily(long Fid) {
		User creator = getCreatorInFamily(Fid);
		Set<User> members = getMembersInFamily(Fid);
		if (creator != null)
			members.add(creator);
		return members;
	}

	@Override
	public Set<User> getMembersAndCreatorInFamily(String Uphone) {
		Set<User> members = getMembersInFamily(Uphone);
		User creator = userDao.getUser(Uphone);
		if (creator != null)
			members.add(creator);
		return members;
	}

	@Override
	public int addMemberToFamily(Long Fid, Long Uid) {
		User user = userDao.getUser(Uid);
		if (user == null) {
			return ResultCode.ERROR_NO_USER;
		}

		Family family = getFamily(Fid);
		if (family == null) {
			return ResultCode.ERROR_NO_FAMILY;
		}

		family.getMembers().add(user);
		update(family);
		return ResultCode.SUCCESS;
	}

	@Override
	public int addMemberToFamily(Long Fid, String Uphone) {
		User user = userDao.getUser(Uphone);
		if (user == null)
			return ResultCode.ERROR_NO_USER;
		Family family = getFamily(Fid);
		if (family == null)
			return ResultCode.ERROR_NO_FAMILY;

		family.getMembers().add(user);
		update(family);
		return ResultCode.SUCCESS;
	}

	@Override
	public int addMemberToFamily(String Uphone, Long Uid) {
		User user = userDao.getUser(Uid);
		if (user == null)
			return ResultCode.ERROR_NO_USER;
		Family family = getCreatedFramily(Uphone);
		if (family == null)
			return ResultCode.ERROR_NO_FAMILY;

		family.getMembers().add(user);
		update(family);
		return ResultCode.SUCCESS;
	}

	@Override
	public int addMemberToFamily(String creatorPhone, String memberPhone) {
		User user = userDao.getUser(memberPhone);
		if (user == null)
			return ResultCode.ERROR_NO_USER;
		Family family = getCreatedFramily(creatorPhone);
		if (family == null)
			return ResultCode.ERROR_NO_FAMILY;

		family.getMembers().add(user);
		update(family);
		return ResultCode.SUCCESS;
	};
}
