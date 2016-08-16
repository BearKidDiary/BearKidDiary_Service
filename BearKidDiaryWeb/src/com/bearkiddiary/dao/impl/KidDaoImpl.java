package com.bearkiddiary.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.Height;
import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.Vision;
import com.bearkiddiary.bean.Weight;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.FamilyDao;
import com.bearkiddiary.dao.KidDao;
import com.bearkiddiary.utils.ResultCode;

public class KidDaoImpl extends BaseDaoHibernate<Kid> implements KidDao {

	private FamilyDao familyDao;

	public void setFamilyDao(FamilyDao familyDao) {
		this.familyDao = familyDao;
	}

	@Override
	public Kid getKid(Long Kid) {
		final String hql = "select k from Kid k where k.Kid = ?0";
		List<Kid> list = find(hql, Kid);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public Set<Kid> getKidsInFamily(Long Fid) {
		Family family = familyDao.getFamily(Fid);
		if (family == null)
			return null;
		family.getKid().size();
		return family.getKid();
	}

	@Override
	public Set<Kid> getKidsInFamily(String Uphone) {
		Family family = familyDao.getCreatedFamily(Uphone);
		if (family == null)
			return null;
		family.getKid().size();
		return family.getKid();
	}

	@Override
	public int updateKid(Long Kid, Kid kid) {
		Kid k = getKid(Kid);
		if (k == null)
			return ResultCode.ERROR_NO_KID;

		if (kid.getKname() != null) {
			for(Kid brother:k.getFamily().getKid()){//有兄弟的名字相同
				if(brother.getKname().equals(kid.getKname())){
					return ResultCode.ERROR_EXIST_KID;
				}
			}
			k.setKname(kid.getKname());
		}

		if (kid.getKbirthday() != null) {
			k.setKbirthday(kid.getKbirthday());
		}

		if (kid.getKask() != null) {
			k.setKask(kid.getKask());
		}

		if (kid.getKsex() != null) {
			k.setKsex(kid.getKsex());
		}

		if (kid.getKavatar() != null) {
			k.setKavatar(kid.getKavatar());
		}

		if (kid.getKflowers() != null) {
			k.setKflowers(kid.getKflowers());
		}

		update(k);
		return ResultCode.SUCCESS;
	}

	@Override
	public int addKid(Long Fid, Kid kid) {
		Family family = familyDao.getFamily(Fid);
		if (family == null) {
			return ResultCode.ERROR_NO_FAMILY;
		}
		for (Kid k : family.getKid()) {
			if (k.getKname().equals(kid.getKname())) {
				return ResultCode.ERROR_EXIST_KID;
			}
		}
		kid.setFamily(family);
		save(kid);
		return ResultCode.SUCCESS;
	}

	@Override
	public int addKid(String Uphone, Kid kid) {
		Family family = familyDao.getCreatedFamily(Uphone);
		if (family == null) {
			return ResultCode.ERROR_NO_FAMILY;
		}
		for (Kid k : family.getKid()) {
			if (k.getKname().equals(kid.getKname())) {
				return ResultCode.ERROR_EXIST_KID;
			}
		}
		kid.setFamily(family);
		save(kid);
		return ResultCode.SUCCESS;
	}

	@Override
	public int removeKid(Long Kid) {
		Kid k = getKid(Kid);
		if (k == null)
			return ResultCode.ERROR_NO_KID;
		delete(k);
		return ResultCode.SUCCESS;
	}
}
