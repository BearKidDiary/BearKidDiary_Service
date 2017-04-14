package com.bearkiddiary.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.sql.Update;

import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.OrgDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.utils.ResultCode;

public class OrgDaoImpl extends BaseDaoHibernate<Organization> implements OrgDao{

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public long createOrg(String Oname, String Oaddress, String Oannounce, String Uphone, String Oavatar, Long Otime) {
		Organization org = new Organization();
		org.setOname(Oname);
		org.setOaddress(Oaddress);
		org.setOannounce(Oannounce);
		org.setOavatar(Oavatar);
		org.setOtime(Otime);
		
		User creator = userDao.getUser(Uphone);
		String hql = "select org from Organization org where org.creator = ?0";
		List<Organization> list = find(hql, creator);
		if(list.isEmpty()){
			org.setCreator(creator);
			long Oid = (long) save(org);
			return Oid;
		}
		
		return ResultCode.ERROR_EXIST_ORG;
	}

	@Override
	public int deleteOrg(String Uphone) {
		User user = userDao.getUser(Uphone);
		String hql = "delete Organization org where org.creator = ?0";
		return update(hql, user);
	}

	@Override
	public int updateOname(String Uphone, String Oname) {
		User user = userDao.getUser(Uphone);
		String hql = "update Organization org set org.Oname = ?0 where org.creator = ?1";
		return update(hql, Oname, user);
	}

	@Override
	public int updateOaddress(String Uphone, String Oaddress) {
		User user = userDao.getUser(Uphone);
		String hql = "update Organization org set org.Oaddress = ?0 where org.creator = ?1";
		return update(hql, Oaddress, user);
	}

	@Override
	public int updateOannounce(String Uphone, String Oannounce) {
		User user = userDao.getUser(Uphone);
		String hql = "update Organization org set org.Oannounce = ?0 where org.creator = ?1";
		return update(hql, Oannounce, user);
	}
	
	public int updateOavatar(String Uphone, String Oavatar){
		User user = userDao.getUser(Uphone);
		String hql = "update Organization org set org.Oavatar = ?0 where org.creator = ?1";
		return update(hql, Oavatar, user);
	}
	
	@Override
	public Organization getOrg(long Oid) {
		String hql = "select org from Organization org where org.Oid = ?0";
		List<Organization> list = find(hql,Oid);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Organization getOrg(String Uphone) {
		User user = userDao.getUser(Uphone);
		Organization organization = user.getCreateOrganization();
		return organization;
	}
	
	/**
	 * 添加机构教师
	 */
	@Override
	public int addOrgTeacher(long Oid, String Uphone) {
		User teacher = userDao.getUser(Uphone);
		if(teacher == null) {
			return ResultCode.ERROR_NO_USER;
		}
		
		Organization org = getOrg(Oid);
		if(org == null){
			return ResultCode.ERROR_NO_ORG;
		}
		org.getTeachers().add(teacher);
		update(org);
		return ResultCode.SUCCESS;
	}

	/**
	 * 添加机构家长
	 */
	@Override
	public int addOrgParent(long Oid, String Uphone) {
		User parent = userDao.getUser(Uphone);
		if(parent == null) {
			return ResultCode.ERROR_NO_USER;
		}
		
		Organization org = getOrg(Oid);
		if(org == null){
			return ResultCode.ERROR_NO_ORG;
		}
		org.getParents().add(parent);
		update(org);
		return ResultCode.SUCCESS;
	}

	@Override
	public int validAdmin(String Uphone, Long Oid) {
		Organization org = getOrg(Oid);
		if(org != null){
			User creator = org.getCreator();
			if(creator.getUphone().equals(Uphone)){
				return ResultCode.SUCCESS;
			}
			return ResultCode.ERROR_NO_PERMISSION;
		}
		return ResultCode.ERROR_NO_ORG;
	}

	@Override
	public List<Organization> getUserCreateOrg(User user) {
		String hql = "select org from Organization org where org.creator = ?0";
		List<Organization> list = find(hql, user);
		return list;
	}

	@Override
	public List<Organization> getUserInOrgs(User user) {
		String hql = "select user.workOrganization from User user where Uphone = ?0";
		List<Organization> list = find(hql, user.getUphone());
		return list;
	}

	@Override
	public List<Organization> getAllOrgs() {
		List<Organization> list = findAll(Organization.class);
		return list;
	}

}
