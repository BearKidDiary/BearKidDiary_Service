package com.bearkiddiary.dao;

import java.util.Set;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.BaseDao;

public interface FamilyDao extends BaseDao<Family> {

	Family getFamily(Long Fid);

	/**
	 * 创建一个家庭
	 * 
	 * @param Uphone
	 * @param Fname
	 * @return 是否创建成功
	 */
	int createFamily(String Uphone, String Fname);

	/**
	 * 获取创建的家庭
	 * 
	 * @param Uphone
	 * @return 创建的家庭
	 */
	Family getCreatedFramily(String Uphone);

	/**
	 * 获取参与的家庭（不包括创建的家庭）
	 * 
	 * @param Uphone
	 * @return 参与的家庭
	 */
	Set<Family> getAttendedFramily(String Uphone);

	/**
	 * 获取id为Fid的家庭中的所有家庭成员（不包括创建者）
	 * 
	 * @param Fid
	 * @return
	 */
	Set<User> getMembersInFamily(long Fid);

	/**
	 * 获取创建者为Uphone的家庭的所有成员（不包括创建者）
	 * 
	 * @param Uphone
	 * @return
	 */
	Set<User> getMembersInFamily(String Uphone);

	/**
	 * 获取id为Fid的家庭中的创建者
	 * 
	 * @param Fid
	 * @return
	 */
	User getCreatorInFamily(long Fid);

	/**
	 * 获取id为Fid的家庭中的所有家庭成员（包括创建者）
	 * 
	 * @param Fid
	 * @return
	 */
	Set<User> getMembersAndCreatorInFamily(long Fid);

	/**
	 * 获取创建者为Uphone的家庭的所有成员（包括创建者）
	 * 
	 * @param Uphone
	 * @return
	 */
	Set<User> getMembersAndCreatorInFamily(String Uphone);

	/**
	 * 添加家庭成员到家庭中
	 * 
	 * @param Fid
	 *            家庭编号
	 * @param Uid
	 *            家庭成员编号
	 */
	int addMemberToFamily(Long Fid, Long Uid);

	/**
	 * 添加家庭成员到家庭中
	 * 
	 * @param Fid
	 *            家庭编号
	 * @param Uphone
	 *            成员的手机号码
	 */
	int addMemberToFamily(Long Fid, String Uphone);

	/**
	 * 添加家庭成员到用户创建的家庭中
	 * 
	 * @param Uphone
	 *            创建者的手机号码
	 * @param Uid
	 *            家庭成员
	 */
	int addMemberToFamily(String Uphone, Long Uid);

	/**
	 * 添加家庭成员到用户创建的家庭中
	 * 
	 * @param creatorPhone
	 *            创建者的手机号码
	 * @param memberPhone
	 *            成员的手机号码
	 * @return
	 */
	int addMemberToFamily(String creatorPhone, String memberPhone);
}
