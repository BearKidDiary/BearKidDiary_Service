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
	public long createOrg(String Oname, String Oaddress, String Oannounce, Long Uid) {
		Organization org = new Organization();
		org.setOname(Oname);
		org.setOaddress(Oaddress);
		org.setOannounce(Oannounce);
		
		User creator = userDao.getUser(Uid);
		org.setCreator(creator);
		
		long Oid = (long) save(org);
		return Oid;
	}

	@Override
	public int deleteOrg(long Oid) {
		String hql = "delete Organization org where org.Oid = ?0";
		return update(hql, Oid);
	}

	@Override
	public int updateOname(long Oid, String Oname) {
		String hql = "update Organization org set org.Oname = ?0 where org.Oid = ?1";
		return update(hql, Oname, Oid);
	}

	@Override
	public int updateOaddress(long Oid, String Oaddress) {
		String hql = "update Organization org set org.Oaddress = ?0 where org.Oid = ?1";
		return update(hql, Oaddress, Oid);
	}

	@Override
	public int updateOannounce(long Oid, String Oannounce) {
		String hql = "update Organization org set org.Oannounce = ?0 where org.Oid = ?1";
		return update(hql, Oannounce, Oid);
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
	
	/**
	 * 添加机构教师
	 */
	@Override
	public int addOrgTeacher(long Oid, long Uid) {
		User teacher = userDao.getUser(Uid);
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
	public int addOrgParent(long Oid, long Uid) {
		User parent = userDao.getUser(Uid);
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

	
}
