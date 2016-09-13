package com.bearkiddiary.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Group;
import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.GroupDao;
import com.bearkiddiary.dao.OrgDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.utils.ResultCode;

public class GroupDaoImpl extends BaseDaoHibernate<Group> implements GroupDao {

	private OrgDao orgDao;

	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int createGroup(Long Oid, String Gname) {
		Organization org = orgDao.getOrg(Oid);
		Set<Group> groups = org.getGroups();
		for (Group other : groups) {
			if (other.getGname().equals(Gname)) {
				return ResultCode.ERROR_EXIST_GROUP;
			}
		}
		Group group = new Group();
		group.setOrg(org);
		group.setGname(Gname);
		save(group);
		return ResultCode.SUCCESS;
	}

	@Override
	public int createGroupWithId(Long Oid, String Gname, List<Long> Uids) {
		Organization org = orgDao.getOrg(Oid);
		Set<Group> groups = org.getGroups();
		for (Group other : groups) {
			if (other.getGname().equals(Gname)) {
				return ResultCode.ERROR_EXIST_GROUP;
			}
		}
		List<User> members = new ArrayList<>();
		for (Long Uid : Uids) {
			User member = userDao.getUser(Uid);
			if (member != null)
				members.add(member);
		}
		Group group = new Group();
		group.setOrg(org);
		group.setGname(Gname);
		group.getMembers().addAll(members);
		save(group);
		return ResultCode.SUCCESS;
	}

	@Override
	public int createGroupWithPhone(Long Oid, String Gname, List<String> Uphones) {
		Organization org = orgDao.getOrg(Oid);
		Set<Group> groups = org.getGroups();
		for (Group other : groups) {
			if (other.getGname().equals(Gname)) {
				return ResultCode.ERROR_EXIST_GROUP;
			}
		}
		List<User> members = new ArrayList<>();
		for (String phone : Uphones) {
			User member = userDao.getUser(phone);
			if (member != null)
				members.add(member);
		}
		Group group = new Group();
		group.setOrg(org);
		group.setGname(Gname);
		group.getMembers().addAll(members);
		save(group);
		return ResultCode.SUCCESS;
	}

	@Override
	public int deleteGroup(Long Gid) {
		Group group = getGroup(Gid);
		if (group == null)
			return ResultCode.ERROR_NO_GROUP;
		delete(group);
		return ResultCode.SUCCESS;
	}

	@Override
	public int addMembersById(Long Gid, List<Long> Uids) {
		Group group = getGroup(Gid);
		if (group == null)
			return ResultCode.ERROR_NO_GROUP;
		List<User> members = new ArrayList<>();
		for (Long Uid : Uids) {
			User member = userDao.getUser(Uid);
			if (member != null) {
				members.add(member);
			}
		}
		group.getMembers().addAll(members);
		return ResultCode.SUCCESS;
	}

	@Override
	public int addMembersByPhone(Long Gid, List<String> Uphones) {
		Group group = getGroup(Gid);
		if (group == null)
			return ResultCode.ERROR_NO_GROUP;
		List<User> members = new ArrayList<>();
		for (String Uphone : Uphones) {
			User member = userDao.getUser(Uphone);
			if (member != null) {
				members.add(member);
			}
		}
		group.getMembers().addAll(members);
		return ResultCode.SUCCESS;
	}

	@Override
	public int deleteMembersById(Long Gid, List<Long> Uids) {
		Group group = getGroup(Gid);
		if (group == null)
			return ResultCode.ERROR_NO_GROUP;
		Set<Long> ids = new HashSet<>(Uids);
		for (User member : group.getMembers()) {
			if (ids.contains(member.getUid())) {
				group.getMembers().remove(member);
			}
		}
		return ResultCode.SUCCESS;
	}

	@Override
	public int deleteMembersByPhone(Long Gid, List<String> Uphones) {
		Group group = getGroup(Gid);
		if (group == null)
			return ResultCode.ERROR_NO_GROUP;
		Set<String> phones = new HashSet<>(Uphones);
		for (User member : group.getMembers()) {
			if (phones.contains(member.getUphone())) {
				group.getMembers().remove(member);
			}
		}
		return ResultCode.SUCCESS;
	}

	@Override
	public Group getGroup(Long Gid) {
		final String hql = "select g from Group g where g.Gid = ?0";
		List<Group> list = find(hql, Gid);
		if (list.size() > 0) {
			//list.get(0).getOrg();
			list.get(0).getMembers().size();
			return list.get(0);
		}
		return null;
	}

	@Override
	public Set<Group> getGroups(Long Oid) {
		final String hql = "select g from Group g join g.org org where org.Oid = ?0";
		List<Group> list = find(hql, Oid);
		for (Group g : list) {
			//g.getOrg();
			g.getMembers().size();
		}
		return new HashSet<>(list);
	}
}
