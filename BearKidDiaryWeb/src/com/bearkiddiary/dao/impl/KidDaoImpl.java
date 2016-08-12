package com.bearkiddiary.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.Kid;
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
		final String hql = "select k from Kid k where k.Kid = ?";
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

		final String hql = "select k from Kid k join k.family family where family.Fid = ?";
		List<Kid> list = find(hql, Fid);
		return new HashSet<>(list);
	}

	@Override
	public Set<Kid> getKidsInFamily(String Uphone) {
		Family family = familyDao.getCreatedFamily(Uphone);
		if (family == null)
			return null;
		System.out.println(family.getKid().size());
		return family.getKid();
	}

	@Override
	public int updateKid(Long Kid, Kid kid) {
		Kid k = getKid(Kid);
		if (k == null)
			return ResultCode.ERROR_NO_KID;

		if (kid.getKname() != null) {
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

		save(kid);
		family.getKid().add(kid);
		familyDao.update(family);
		return ResultCode.SUCCESS;
	}

	@Override
	public int addKid(String Uphone, Kid kid) {
		Family family = familyDao.getCreatedFamily(Uphone);
		if (family == null) {
			return ResultCode.ERROR_NO_FAMILY;
		}

		save(kid);
		family.getKid().add(kid);
		familyDao.update(family);
		return ResultCode.SUCCESS;
	}

	@Override
	public int removeKid(Long Fid, Long Kid) {
		Kid k = getKid(Kid);
		if (k == null) {
			return ResultCode.ERROR_NO_KID;
		}
		Family family = familyDao.getFamily(Fid);
		if (family == null) {
			return ResultCode.ERROR_NO_FAMILY;
		}

		boolean success = family.getKid().remove(k);
		if (success) {
			familyDao.update(family);
			return ResultCode.SUCCESS;
		}
		return ResultCode.ERROR_NO_RELATION;
	}

	@Override
	public int removeKid(String Uphone, Long Kid) {
		Kid k = getKid(Kid);
		if (k == null) {
			return ResultCode.ERROR_NO_KID;
		}
		Family family = familyDao.getCreatedFamily(Uphone);
		if (family == null) {
			return ResultCode.ERROR_NO_FAMILY;
		}

		boolean success = family.getKid().remove(k);
		if (success) {
			familyDao.update(family);
			return ResultCode.SUCCESS;
		}
		return ResultCode.ERROR_NO_RELATION;
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
