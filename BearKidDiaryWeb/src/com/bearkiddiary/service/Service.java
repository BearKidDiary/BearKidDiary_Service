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
	int updateUser(String Uphone, String Parameter, String value);

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
	 * 获得家庭的所有成员 包含创建者<br/>
	 * Uphone或者Fid二者有其一就可以了
	 * 
	 * @param Uphone
	 *            创建者的手机号码
	 * @param Fid
	 *            家庭的编号
	 * @return
	 */
	Set<User> getFamilyMembersAndCreator(String Uphone, Long Fid);

	/**
	 * 获取家庭的创建者
	 * 
	 * @param Uphone
	 *            创建者的手机号码
	 * @param Fid
	 *            家庭编号
	 */
	User getFamilyCreator(String Uphone, Long Fid);

	/**
	 * 添加家庭成员到家庭中
	 * 
	 * @param Fid
	 * @param creatorPhone
	 * @param Uid
	 * @param memberPhone
	 */
	int addFamilyMembers(Long Fid, String creatorPhone, Long Uid, String memberPhone);

	/**
	 * 删除家庭成员
	 * 
	 * @param Fid
	 *            家庭编号
	 * @param creatorPhone
	 *            创建者的手机号码
	 * @param memberPhone
	 *            家庭成员的手机号码
	 */
	int removeFamilyMember(Long Fid, String creatorPhone, String memberPhone);

	/**
	 * 获取创建的家庭信息
	 * 
	 * @param Uphone
	 *            创建者的手机号码
	 * @param Fid
	 *            家庭编号
	 */
	Family getCreatedFamily(String Uphone, Long Fid);

	/**
	 * 获取参与的家庭信息
	 * 
	 * @param Uphone
	 *            创建者的手机号码
	 */
	Set<Family> getAttendFamily(String Uphone);

	/**
	 * 更新家庭信息
	 * 
	 * @param Uphone
	 *            创建者的手机号码
	 * @param Fid
	 *            家庭的编号
	 * @param Fname
	 *            家庭的名字
	 * @return
	 */
	int updateFamily(String Uphone, Long Fid, String Fname);
	
//机构
	/**
	 * 创建机构
	 * @param Oname 
	 * @param Oaddress
	 * @param Oannounce
	 * @param Uid
	 * @return
	 */
	public long createOrg(String Oname, String Oaddress, String Oannounce, Long Uid);
	
	/**
	 * 解散机构
	 * @param Oid
	 */
	public void deleteOrg(long Oid);
	
	/**
	 * 更新机构信息
	 * @param Oid
	 * @param Parameter
	 * @param value
	 * @return
	 */
	long updateOrg(long Oid, String Parameter, String value);

	/**
	 * 添加机构成员（家长或者教师）
	 * @param Oid
	 * @param Uid
	 * @return
	 */
	public int addOrgMember(long Oid, long Uid, int identity);
}
