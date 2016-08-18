package com.bearkiddiary.dao.impl;

import java.util.List;

import com.bearkiddiary.bean.Leave_Application;
import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.BaseDao;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.LADao;
import com.bearkiddiary.dao.OrgDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.utils.ResultCode;

public class LADaoImpl extends BaseDaoHibernate<Leave_Application> implements LADao{

	private OrgDao orgDao;
	
	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}
	
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * Ìá½»Çë¼ÙÉêÇë
	 */
	@Override
	public Long commitApplicaton(Leave_Application application) {
		Long result =  (Long) save(application);
		if (result > 0) {
			return result;
		}
		return (long) ResultCode.ERROR;
	}

	@Override
	public int updateApplication(Integer LAstatus, Integer LAisapproved, User LArover, String LAcomment, Long LAid) {
		String hql = "update Leave_Application application set application.LAstatus = ?0,application.LAisapproved = ?1,application.LArover = ?2";
		if(LAcomment != null){
			hql = hql + ",application.LAcomment = ?3 where application.LAid = ?4";
			return update(hql, LAstatus, LAisapproved, LArover, LAcomment, LAid);
		}
		hql = hql + " where application.LAid = ?3";
		return update(hql, LAstatus, LAisapproved, LArover, LAid);
	}

	@Override
	public List<Leave_Application> getOrgApplicationList(Long Oid) {
		Organization org = new Organization();
		org = orgDao.getOrg(Oid);
		if(org == null){
			return null;
		}
		String hql = "select application from Leave_Application application where application.LAorg = ?0";
		List<Leave_Application> list = find(hql, org);
		return list;
	}

	@Override
	public List<Leave_Application> getUserApplicationList(Long Uid) {
		User user = new User();
		user = userDao.getUser(Uid);
		if(user == null){
			return null;
		}
		String hql = "select application from Leave_Application application where application.LAapplicant = ?0";
		return find(hql, user);
	}

	@Override
	public List<Leave_Application> getUserApplicationList(String Uphone) {
		User user = new User();
		user = userDao.getUser(Uphone);
		if(user == null){
			return null;
		}
		String hql = "select application from Leave_Application application where application.LAapplicant = ?0";
		return find(hql, user);
	}

}
