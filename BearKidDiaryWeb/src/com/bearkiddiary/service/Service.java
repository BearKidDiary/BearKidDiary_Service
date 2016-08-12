package com.bearkiddiary.service;

import java.util.Set;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.User;

public interface Service {

	/**
	 * 测试
	 * 
	 * @param user
	 *            获取的用户
	 */
	// 登录功能
	boolean Login(String Uphone, String Upsw);

	boolean Register(User user);

	/**
	 * 更新操作
	 * 
	 * @param Parameter
	 * @param value
	 */
	int update(String Uphone, String Parameter, String value);

	/**
	 * 创建一个家庭
	 * 
	 * @param Uphone
	 *            用户手机号码
	 * @param Fname
	 *            家庭的名字
	 * @return 是否创建成功
	 */
	int createFamily(String Uphone, String Fname);

	/**
	 * 获得家庭的所有成员（除了创建者以外）<br/>
	 * Uphone或者Fid二者有其一就可以了
	 * 
	 * @param Uphone
	 *            创建者的手机号码
	 * @param Fid
	 *            家庭的编号
	 */
	Set<User> getFamilyMembers(String Uphone, Long Fid);

	/**
	 * 添加家庭成员到家庭中
	 * 
	 * @param Fid
	 * @param creatorPhone
	 * @param Uid
	 * @param memberPhone
	 */
	int addFamilyMembers(Long Fid, String creatorPhone, Long Uid, String memberPhone);

	Family getCreatedFamily(String Uphone);
}
