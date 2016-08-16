package com.bearkiddiary.dao.impl;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Height;
import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.HeightDao;
import com.bearkiddiary.dao.KidDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.utils.ResultCode;

public class HeightDaoImpl extends BaseDaoHibernate<Height> implements HeightDao {

	private KidDao kidDao;

	public void setKidDao(KidDao kidDao) {
		this.kidDao = kidDao;
	}

	@Override
	public int addHeightToKid(Long Kid, Float Hheight, Long Htime) {
		Kid k = kidDao.getKid(Kid);
		if (k == null) {
			return ResultCode.ERROR_NO_KID;
		}

		Height h = new Height();
		h.setHheight(Hheight);
		h.setHtime(Htime);
		h.setKid(k);
		save(h);
		return ResultCode.SUCCESS;
	}

	@Override
	public List<Height> getHeight(Long Kid, String order) {
		if (!order.equals("desc")) {
			order = "asc";
		}
		final String hql = "select height from Height height join height.kid kid where kid.Kid = ?0 "
				+ "order by height.Htime " + order;
		return find(hql, Kid);
	}

	@Override
	public List<Height> getHeight(Long Kid, String order, int pageSize, int pageNum) {
		// TODO Auto-generated method stub
		return null;
	}
}
