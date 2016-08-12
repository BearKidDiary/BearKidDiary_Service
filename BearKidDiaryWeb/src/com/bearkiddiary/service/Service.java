package com.bearkiddiary.service;

import java.util.Set;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.Kid;
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

	/**
	 * 获取孩子的信息
	 * 
	 * @param Kid
	 *            孩子编号
	 * @param Uphone
	 *            家庭创建者手机号码
	 * @param Fid
	 *            家庭编号
	 */
	Set<Kid> getKids(Long Kid, String Uphone, Long Fid);

	/**
	 * 删除孩子
	 * 
	 * @param Kid
	 *            孩子编号
	 */
	int removeKid(Long Kid);

	/**
	 * 新添加一个孩子到家庭中
	 * 
	 * @param Kname
	 *            孩子的名字
	 * @param Kbirthday
	 *            孩子的生日
	 * @param Kavatar
	 *            孩子头像的URL
	 * @param Ksex
	 *            孩子的性别
	 * @param Kask
	 *            家长的叮嘱
	 * @param Kflowers
	 *            小红花数
	 * @param Fid
	 *            家庭的编号
	 * @param Uphone
	 *            家庭创建者的手机号码
	 */
	int addKid(String Kname, Long Kbirthday, String Kavatar, String Ksex, String Kask, Integer Kflowers, Long Fid,
			String Uphone);
}
