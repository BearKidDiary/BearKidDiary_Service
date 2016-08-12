package com.bearkiddiary.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.sql.Update;

import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.OrgDao;
import com.bearkiddiary.dao.UserDao;

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
	public void deleteOrg(long Oid) {
		String hql = "delete Organization org where org.Oid = ?0";
		update(hql, Oid);
	}

	@Override
	public long updateOname(long Oid, String Oname) {
		String hql = "update Organization org set org.Oname = ?0 where org.Oid = ?1";
		return update(hql, Oname, Oid);
	}

	@Override
	public long updateOaddress(long Oid, String Oaddress) {
		String hql = "update Organization org set org.Oaddress = ?0 where org.Oid = ?1";
		return update(hql, Oaddress, Oid);
	}

	@Override
	public long updateOannounce(long Oid, String Oannounce) {
		String hql = "update Organization org set org.Oannounce = ?0 where org.Oid = ?1";
		return update(hql, Oannounce, Oid);
	}

	
}
