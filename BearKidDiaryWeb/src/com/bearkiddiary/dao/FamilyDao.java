package com.bearkiddiary.dao;

import java.util.Set;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.BaseDao;

public interface FamilyDao extends BaseDao<Family> {
	public static final int ERROR_NO_USER = -1;
	public static final int ERROR_ALREADY_HAVE_FAMILY = -2;
	public static final int SUCESS = 0;

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
	Set<User> getCreatorInFamily(long Fid);

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
}
