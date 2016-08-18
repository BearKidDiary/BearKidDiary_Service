package com.bearkiddiary.service;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.Height;
import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.Leave_Application;
import com.bearkiddiary.bean.TimeLine;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.bean.Vision;
import com.bearkiddiary.bean.Weight;

public interface Service {
//用户
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
	 * 提交请假申请
	 * @param application
	 * @param Oid
	 * @param Uphone
	 * @return
	 */
	Long commitApplication(Leave_Application application, Long Oid, String Uphone);
	
	/**
	 * 更新请假申请的状态
	 * @param LAstatus
	 * @param LAcomment
	 * @return 更新的条数
	 */
	int updateApplication(Integer LAstatus, Integer LAisapproved,String Uphone, String LAcomment, Long LAid);
	
	/**
	 * 获取机构的请假申请列表
	 * @param Oid
	 * @return
	 */
	List<Leave_Application> getOrgApplicationList(Long Oid);
	
	/**
	 * 教师获取个人请假的申请列表
	 * @param Uid
	 * @return
	 */
	List<Leave_Application> getUserApplicationList(Long Uid);
	
	/**
	 * 教师获取个人请假的申请列表
	 * @param Uid
	 * @return
	 */
	List<Leave_Application> getUserApplicationList(String Uphone);
	
	/**
	 * 验证是否是该机构的管理员权限
	 * @param Oid
	 * @param Uphone
	 * @return
	 */
	int validAdmin(Long Oid, String Uphone);
//////////////////////////////////////////////////////////// 家庭/////////////////////////////////////////////////////////
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

	/**
	 * 添加时间轴事件
	 * 
	 * @param content
	 *            内容
	 * @param time
	 *            时间
	 * @param image1
	 *            图片1URL
	 * @param image2
	 *            图片2URL
	 * @param image3
	 *            图片3URL
	 * @param type
	 *            时间轴事件类型
	 * @param logoType
	 *            时间轴事件图标类型
	 * @param Uid
	 *            发布者的编号
	 * @param Uphone
	 *            发布者的手机号码
	 * @param Kid
	 *            孩子的编号
	 */
	int addTimeLine(String content, Long time, String image1, String image2, String image3, String type, int logoType,
			Long Uid, String Uphone, Long Kid);

	/**
	 * 获取时间轴事件
	 * 
	 * @param Kid
	 *            孩子的编号
	 * @param Uid
	 *            发布者的编号
	 * @param Uphone
	 *            发布者的手机号码
	 * @param Order
	 *            排序
	 */
	List<TimeLine> getTimeLine(Long Kid, Long Uid, String Uphone, String Order);

	/**
	 * 分页获取时间轴事件
	 * 
	 * @param Kid
	 *            孩子的编号
	 * @param Uid
	 *            发布者的编号
	 * @param Uphone
	 *            发布者的手机号码
	 * @param Order
	 *            排序
	 * @param pageSize
	 *            分页大小
	 * @param pageNum
	 *            分页的页码
	 */
	List<TimeLine> getTimeLine(Long Kid, Long Uid, String Uphone, String Order, int pageSize, int pageNum);


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
	public int deleteOrg(long Oid);

	/**
	 * 更新机构信息
	 * 
	 * @param Oid
	 * @param Parameter
	 * @param value
	 * @return
	 */
	int updateOrg(long Oid, String Parameter, String value);

	/**
	 * 添加机构成员（家长或者教师）
	 * 
	 * @param Oid
	 * @param Uid
	 * @return
	 */
	public int addOrgMember(long Oid, long Uid, int identity);

}
