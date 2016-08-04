package com.bearkiddiary.dao;

import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.BaseDao;

public interface UserDao extends BaseDao<User>{
	/**
	 * �û���¼
	 * @param user �û�����
	 * @return
	 */
	int Login(String Uphone, String Upsw);
	
	/**
	 * �û�ע��
	 * @param user 
	 * @return
	 */
	int Register(User user);
	
	/**
	 * ���ú����Ƿ�ע���
	 * @param Uphone
	 * @return
	 */
	int Valid(String Uphone);
	
	int updateName(String Uphone, String Uname);
	int updateArea(String Uphone, String Uarea);
	int updateEmail(String Uphone, String Uemail);
}
