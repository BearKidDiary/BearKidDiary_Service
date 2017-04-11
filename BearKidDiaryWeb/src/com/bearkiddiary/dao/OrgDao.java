package com.bearkiddiary.dao;

import java.util.List;

import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.BaseDao;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public interface OrgDao extends BaseDao<Organization>{
	
	/**
	 * 创建机构
	 * @param Oname 机构名字
	 * @param Oaddress 机构地址
	 * @param Oannounce 机构公告（简介）
	 * @param Uid 创建的用户id
	 * @return
	 */
	public long createOrg(String Oname, String Oaddress, String Oannounce, String Uphone);
	
	/**
	 * 解散机构
	 * @param Oid
	 */
	public int deleteOrg(String Uphone);
	
	/**
	 * 更新机构名字
	 * @param Oid
	 * @param Oname
	 * @return
	 */
	public int updateOname(String Uphone, String Oname);
	/**
	 * 更新机构地址
	 * @param Oid
	 * @param Oaddress
	 * @return
	 */
	public int updateOaddress(String Uphone, String Oaddress);
	/**
	 * 更新机构公告
	 * @param Oid
	 * @param Oannounce
	 * @return
	 */
	public int updateOannounce(String Uphone, String Oannounce);
	
	/**
	 * 获取机构
	 */
	public Organization getOrg(long Oid);
	
	/**
	 * 获取机构
	 * @param Uphone
	 * @return
	 */
	public Organization getOrg(String Uphone);
	
	/**
	 * 获取所有的机构
	 * @return
	 */
	public List<Organization> getAllOrgs();
//机构与用户之间的关系
	
	/**
	 * 添加机构教师
	 * @param Oid
	 * @param Uid
	 * @return
	 */
	public int addOrgTeacher(long Oid, String Uphone);
	
	/**
	 * 添加机构家长
	 * @param Oid
	 * @param Uid
	 * @return
	 */
	public int addOrgParent(long Oid, String Uphone);
	
	/**
	 * 验证是否是该机构的管理员
	 * @param Uphone
	 * @param Oid
	 * @return
	 */
	int validAdmin(String Uphone, Long Oid);
	
	/**
	 * 获取用户创建的机构
	 * @param user
	 * @return
	 */
	List<Organization> getUserCreateOrg(User user);
	/**
	 * 获取用户加入的机构（教师）
	 * @param user
	 * @return
	 */
	List<Organization> getUserInOrgs(User user);
}
