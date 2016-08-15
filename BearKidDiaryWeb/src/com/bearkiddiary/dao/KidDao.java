package com.bearkiddiary.dao;

import java.util.Set;

import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.common.dao.BaseDao;

public interface KidDao extends BaseDao<Kid> {

	/**
	 * 获得孩子信息
	 * 
	 * @param Kid
	 *            孩子的编号
	 */
	Kid getKid(Long Kid);

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
	 * @param kid
	 *            孩子的信息
	 */
	int addKid(Long Fid, Kid kid);

	/**
	 * 添加一个孩子到用户创建的家庭中
	 * 
	 * @param Uphone
	 *            创建者的手机号
	 * @param kid
	 *            孩子信息
	 */
	int addKid(String Uphone, Kid kid);

	/**
	 * 更新孩子的信息
	 * 
	 * @param Kid
	 *            孩子编号
	 * @param kid
	 *            孩子信息
	 */
	int updateKid(Long Kid, Kid kid);


	/**
	 * 删除一个孩子
	 * 
	 * @param Kid
	 *            孩子的编号
	 */
	int removeKid(Long Kid);
}
