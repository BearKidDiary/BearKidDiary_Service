package com.bearkiddiary.dao;

import java.util.Set;

import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.common.dao.BaseDao;

public interface KidDao extends BaseDao<Kid> {
	/**
	 * 获得家庭中的所有孩子
	 * 
	 * @param Fid
	 *            家庭编号
	 */
	Set<Kid> getKidsInFamily(Long Fid);

	/**
	 * 获得家庭中的所有孩子
	 * 
	 * @param Uphone
	 *            创建者的手机号
	 */
	Set<Kid> getKidsInFamily(String Uphone);

	/**
	 * 添加一个孩子到家庭中
	 * 
	 * @param Fid
	 *            家庭编号
	 */
	int addKid(Long Fid);

	/**
	 * 添加一个孩子到用户创建的家庭中
	 * 
	 * @param Uphone
	 *            创建者的手机号
	 */
	int addKid(String Uphone);

	/**
	 * 更新孩子的信息
	 * 
	 * @param Kid
	 *            孩子编号
	 * @param kid
	 *            孩子信息
	 */
	int updateKid(Long Kid, Kid kid);
}
