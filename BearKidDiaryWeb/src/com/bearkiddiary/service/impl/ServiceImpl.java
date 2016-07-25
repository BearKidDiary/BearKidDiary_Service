package com.bearkiddiary.service.impl;

import java.util.List;

import com.bearkiddiary.bean.User;
import com.bearkiddiary.dao.TestDao;
import com.bearkiddiary.service.Service;

public class ServiceImpl implements Service{

	/**
	 * ≤‚ ‘
	 */
	private TestDao testDao;
	
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}
	@Override
	public boolean Login(User user) {
		// TODO Auto-generated method stub
		List<User> userList = testDao.Login(user);
		if(!userList.isEmpty()){
			return true;
		}
		return false;
	}

}
