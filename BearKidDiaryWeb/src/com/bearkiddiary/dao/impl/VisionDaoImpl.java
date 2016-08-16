package com.bearkiddiary.dao.impl;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.Vision;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.KidDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.dao.VisionDao;
import com.bearkiddiary.utils.ResultCode;

public class VisionDaoImpl extends BaseDaoHibernate<Vision> implements VisionDao {
	private KidDao kidDao;

	public void setKidDao(KidDao kidDao) {
		this.kidDao = kidDao;
	}

	@Override
	public int addVision(Long Kid, Float Vleft, Float Vright, Long Vtime) {
		Kid kid = kidDao.getKid(Kid);
		if (kid == null)
			return ResultCode.ERROR_NO_KID;

		Vision vision = new Vision();
		vision.setVleft(Vleft);
		vision.setVright(Vright);
		vision.setVtime(Vtime);
		vision.setKid(kid);
		save(vision);
		return ResultCode.SUCCESS;
	}

	@Override
	public List<Vision> getVision(Long Kid, String order) {
		if (!order.equals("asc")) {
			order = "desc";
		}
		final String hql = "select v from Vision v join v.kid kid where kid.Kid = ?0 order by v.Vtime " + order;
		return find(hql, Kid);
	}

	@Override
	public List<Vision> getVision(Long Kid, String order, int pageNum, int pageSize) {
		if (!order.equals("asc")) {
			order = "desc";
		}
		final String hql = "select v from Vision v join v.kid kid where kid.Kid = ?0 order by v.Vtime " + order;
		return findByPage(hql, pageNum, pageSize, Kid);
	}
}
