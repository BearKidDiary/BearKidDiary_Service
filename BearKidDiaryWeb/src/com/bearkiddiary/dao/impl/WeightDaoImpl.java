package com.bearkiddiary.dao.impl;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.Weight;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.KidDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.dao.WeightDao;
import com.bearkiddiary.utils.ResultCode;

public class WeightDaoImpl extends BaseDaoHibernate<Weight> implements WeightDao {
	private KidDao kidDao;

	public void setKidDao(KidDao kidDao) {
		this.kidDao = kidDao;
	}

	@Override
	public int addWeightToKid(Long Kid, Float Wweight, Long Wtime) {
		Kid kid = kidDao.getKid(Kid);
		if (kid == null) {
			return ResultCode.ERROR_NO_KID;
		}
		Weight weight = new Weight();
		weight.setWweight(Wweight);
		weight.setWtime(Wtime);
		weight.setKid(kid);
		save(weight);
		return ResultCode.SUCCESS;
	}

	@Override
	public List<Weight> getWeight(Long Kid, String order) {
		if (order == null || !order.equals("asc")) {
			order = "desc";
		}
		final String hql = "select w from Weight w join w.kid kid where kid.Kid = ?0 order by w.Wtime " + order;
		return find(hql, Kid);
	}

	@Override
	public List<Weight> getWeight(Long Kid, String order, int pageNum, int pageSize) {
		if (order == null || !order.equals("asc")) {
			order = "desc";
		}
		final String hql = "select w from Weight w join w.kid kid where kid.Kid = ?0 order by w.Wtime " + order;
		return findByPage(hql, pageNum, pageSize, Kid);
	}
}
