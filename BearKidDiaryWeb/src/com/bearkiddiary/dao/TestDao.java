package com.bearkiddiary.dao;

import java.util.List;

import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.BaseDao;

public interface TestDao extends BaseDao<User>{
	/**
	 * ≤‚ ‘
	 * @param user
	 * @return
	 */
	List<User> Login(User user);
}
