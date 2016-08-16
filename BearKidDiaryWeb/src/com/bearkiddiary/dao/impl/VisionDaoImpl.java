package com.bearkiddiary.dao.impl;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Vision;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.KidDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.dao.VisionDao;

public class VisionDaoImpl extends BaseDaoHibernate<Vision> implements VisionDao {
	private KidDao kidDao;

	public void setKidDao(KidDao kidDao) {
		this.kidDao = kidDao;
	}

	@Override
	public int addVision(Long Kid, Float Vleft, Float Vright, Long Vtime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Vision> getVision(Long Kid, String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vision> getVision(Long Kid, String order, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
