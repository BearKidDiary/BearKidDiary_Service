package com.bearkiddiary.service.impl;

import java.util.List;

import com.bearkiddiary.bean.User;
import com.bearkiddiary.dao.TestDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.service.Service;

public class ServiceImpl implements Service {

	/**
	 * ≤‚ ‘
	 */
	private TestDao testDao;

	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// @Override
	// public boolean Login(User user) {
	// // TODO Auto-generated method stub
	// List<User> userList = testDao.Login(user);
	// if(!userList.isEmpty()){
	// return true;
	// }
	// return false;
	// }
	@Override
	public boolean Register(User user) {
		// TODO Auto-generated method stub
		if (userDao.Valid(user.getUphone()) == 0) {
			System.out.println(userDao.save(user));
			return true;
		}
		return false;
	}

	@Override
	public boolean Login(String Uphone, String Upsw) {
		int userCount = userDao.Login(Uphone, Upsw);
		if (userCount > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int update(String Uphone,String Parameter, String value) {
		int result = 0;
		switch (Parameter) {
		    case User.NAME:
		    	result = userDao.updateName(Uphone, value);
		    	break;
		    case User.AREA:
		    	result = userDao.updateArea(Uphone, value);
		    	break;
		    case User.EMAIL:
		    	result = userDao.updateEmail(Uphone, value);
		    	break;
		}
		return result;
	}

}
