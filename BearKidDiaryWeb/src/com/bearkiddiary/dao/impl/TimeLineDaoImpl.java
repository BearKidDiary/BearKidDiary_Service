package com.bearkiddiary.dao.impl;

import java.util.List;

import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.TimeLine;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.KidDao;
import com.bearkiddiary.dao.TimeLineDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.utils.ResultCode;
import com.sun.org.apache.xpath.internal.operations.Or;

public class TimeLineDaoImpl extends BaseDaoHibernate<TimeLine> implements TimeLineDao {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private KidDao kidDao;

	public void setKidDao(KidDao kidDao) {
		this.kidDao = kidDao;
	}

	@Override
	public int addTimeLine(TimeLine timeLine, Long Uid, Long Kid) {
		User author = userDao.getUser(Uid);
		if (author == null) {
			return ResultCode.ERROR_NO_USER;
		}
		Kid kid = kidDao.getKid(Kid);
		if (kid == null) {
			return ResultCode.ERROR_NO_KID;
		}
		timeLine.setAuthor(author);
		timeLine.setKid(kid);
		save(timeLine);
		return ResultCode.SUCCESS;
	}

	@Override
	public int addTimeLine(TimeLine timeLine, String Uphone, Long Kid) {
		User author = userDao.getUser(Uphone);
		if (author == null) {
			return ResultCode.ERROR_NO_USER;
		}
		Kid kid = kidDao.getKid(Kid);
		if (kid == null) {
			return ResultCode.ERROR_NO_KID;
		}
		timeLine.setKid(kid);
		timeLine.setAuthor(author);
		save(timeLine);
		return ResultCode.SUCCESS;
	}

	@Override
	public List<TimeLine> getTimeLine(Long Kid, String Order) {
		if (Order == null || !Order.equals("asc")) {
			Order = "desc";
		}
		final String hql = "select tl from TimeLine tl join tl.kid kid where kid.Kid = ?0 "
				+ "order by tl.Treleasetime " + Order;
		return find(hql, Kid);
	}

	@Override
	public List<TimeLine> getTimeLine(Long Kid, String Order, int pageSize, int pageNum) {
		if (Order == null || !Order.equals("asc")) {
			Order = "desc";
		}
		final String hql = "select tl from TimeLine tl join tl.kid kid where kid.Kid = ?0 "
				+ "order by tl.Treleasetime " + Order;
		return findByPage(hql, pageNum, pageSize, Kid);
	}

	@Override
	public List<TimeLine> getTimeLine(Long Kid, Long Uid, String Uphone, String Order) {
		if (Order == null || !Order.equals("asc")) {
			Order = "desc";
		}
		if (Uid != null) {
			final String hql = "select tl from TimeLine tl join tl.kid kid join tl.author author "
					+ "where kid.Kid = ?0 and author.Uid = ?1 order by tl.Treleasetime " + Order;
			return find(hql, Kid, Uid);
		} else {
			final String hql = "select tl from TimeLine tl join tl.kid kid join tl.author author "
					+ "where kid.Kid = ?0 and author.Uphone = ?1 order by tl.Treleasetime " + Order;
			return find(hql, Kid, Uphone);
		}
	}

	@Override
	public List<TimeLine> getTimeLine(Long Kid, Long Uid, String Uphone, String Order, int pageSize, int pageNum) {
		if (Order == null || !Order.equals("asc")) {
			Order = "desc";
		}
		if (Uid != null) {
			final String hql = "select tl from TimeLine tl join tl.kid kid join tl.author author "
					+ "where kid.Kid = ?0 and author.Uid = ?1 order by tl.Treleasetime " + Order;
			return findByPage(hql, pageNum, pageSize, Kid, Uid);
		} else {
			final String hql = "select tl from TimeLine tl join tl.kid kid join tl.author author "
					+ "where kid.Kid = ?0 and author.Uphone = ?1 order by tl.Treleasetime " + Order;
			return findByPage(hql, pageNum, pageSize, Kid, Uphone);
		}
	}
}
