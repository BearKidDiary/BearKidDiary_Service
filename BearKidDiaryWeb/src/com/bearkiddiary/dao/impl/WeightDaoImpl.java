package com.bearkiddiary.dao.impl;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Weight;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.KidDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.dao.WeightDao;

public class WeightDaoImpl extends BaseDaoHibernate<Weight> implements WeightDao {
	private KidDao kidDao;

	public void setKidDao(KidDao kidDao) {
		this.kidDao = kidDao;
	}

	@Override
	public int addWeightToKid(Long Kid, Float Wweight, Long Wtime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Weight> getWeight(Long Kid, String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Weight> getWeight(Long Kid, String order, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
}
