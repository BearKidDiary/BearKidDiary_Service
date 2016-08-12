package com.bearkiddiary.dao.impl;

import java.util.Set;

import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.FamilyDao;
import com.bearkiddiary.dao.KidDao;

public class KidDaoImpl extends BaseDaoHibernate<Kid> implements KidDao {

	private FamilyDao familyDao;

	public void setFamilyDao(FamilyDao familyDao) {
		this.familyDao = familyDao;
	}

	@Override
	public Set<Kid> getKidsInFamily(Long Fid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Kid> getKidsInFamily(String Uphone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addKid(Long Fid) {

		return 0;
	}

	@Override
	public int addKid(String Uphone) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateKid(Long Kid, Kid kid2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
