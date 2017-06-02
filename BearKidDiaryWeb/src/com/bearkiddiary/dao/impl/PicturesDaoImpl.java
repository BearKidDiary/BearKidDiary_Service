package com.bearkiddiary.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.bearkiddiary.bean.Course;
import com.bearkiddiary.bean.Pictures;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.PicturesDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.utils.ResultCode;

public class PicturesDaoImpl extends BaseDaoHibernate<Pictures> implements PicturesDao{

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int savePicture(Pictures picture) {
		Long Pid = (Long) save(picture);
		if(Pid > 0){
			return ResultCode.SUCCESS;
		}
		return ResultCode.ERROR;
	}

	@Override
	public List<String> getPicture(String Uphone, String Order, int pageNo, int pageSize) {
		if(Order == null || !Order.equals("asc")){
			Order = "desc";
		}
		
		User user = userDao.getUser(Uphone);
		String hql = "select pic from Pictures pic where pic.user = ?0 order by pic.Ptime " + Order;
		List<Pictures> list = findByPage(hql, pageNo, pageSize, user);
		List<String> list_pic = new ArrayList<>();
		for(Pictures pic : list){
			list_pic.add(pic.getPimage());
		}
		return list_pic;
	}

	@Override
	public List<String> getPicture(String Uphone, String Order) {
		User user = userDao.getUser(Uphone);
		if(Order == null || !Order.equals("asc")){
			Order = "desc";
		}
		String hql = "select pic from Pictures pic where pic.user = ?0 order by pic.Ptime " + Order;
		List<Pictures> list = find(hql, user);
		List<String> list_pic = new ArrayList<>();
		for(Pictures pic : list){
			list_pic.add(pic.getPimage());
		}
		return list_pic;
	}

}
