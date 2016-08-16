package com.bearkiddiary.service;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.Height;
import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.bean.Vision;
import com.bearkiddiary.bean.Weight;

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

	//////////////////////////////////////////////////////// 家庭/////////////////////////////////////////////////////////
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

	//////////////////////////////////////////////////////// 孩子/////////////////////////////////////////////////////////
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

	/**
	 * 更新孩子的信息
	 * 
	 * @param Kid
	 *            孩子的编号
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
	 */
	int updateKid(Long Kid, String Kname, Long Kbirthday, String Kavatar, String Ksex, String Kask, Integer Kflowers);

	/**
	 * 添加孩子身体信息
	 * 
	 * @param Kid
	 *            孩子的编号
	 * @param Hheight
	 *            孩子的身高
	 * @param Wweight
	 *            孩子的体重
	 * @param Vleft
	 *            孩子的左眼视力
	 * @param Vright
	 *            孩子的右眼视力
	 * @param time
	 *            更新时间
	 */
	int addKidBodyMsg(Long Kid, Float Hheight, Float Wweight, Float Vleft, Float Vright, Long time);

	/**
	 * 获取孩子的身高信息
	 * 
	 * @param Kid
	 *            孩子的编号
	 * @param order
	 *            desc或者asc
	 */
	List<Height> getHeight(Long Kid, String order);

	/**
	 * 分页获取孩子的身高信息
	 * 
	 * @param Kid
	 *            孩子的编号
	 * @param order
	 *            desc或者asc
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return
	 */
	List<Height> getHeight(Long Kid, String order, int pageNum, int pageSize);

	/**
	 * 获取孩子的体重信息
	 * 
	 * @param Kid
	 *            孩子编号
	 * @param order
	 *            desc或者asc
	 */
	List<Weight> getWeight(Long Kid, String order);

	/**
	 * 分页获取孩子的体重信息
	 * 
	 * @param Kid
	 *            孩子的编号
	 * @param order
	 *            desc或者asc
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页大小
	 */
	List<Weight> getWeight(Long Kid, String order, int pageNum, int pageSize);

	/**
	 * 获取孩子的视力信息
	 * 
	 * @param Kid
	 *            孩子的编号
	 * @param order
	 *            desc或者asc
	 */
	List<Vision> getVision(Long Kid, String order);

	/**
	 * 分页获取孩子的视力信息
	 * 
	 * @param Kid
	 *            孩子的编号
	 * @param order
	 *            desc或者asc
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页大小
	 */
	List<Vision> getVision(Long Kid, String order, int pageNum, int pageSize);

	// 机构
	/**
	 * 创建机构
	 * 
	 * @param Oname
	 * @param Oaddress
	 * @param Oannounce
	 * @param Uid
	 * @return
	 */
	public long createOrg(String Oname, String Oaddress, String Oannounce, Long Uid);

	/**
	 * 解散机构
	 * 
	 * @param Oid
	 */
	public void deleteOrg(long Oid);

	/**
	 * 更新机构信息
	 * 
	 * @param Oid
	 * @param Parameter
	 * @param value
	 * @return
	 */
	long updateOrg(long Oid, String Parameter, String value);
}
