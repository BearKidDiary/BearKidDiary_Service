package com.bearkiddiary.dao;

import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.BaseDao;

public interface OrgDao extends BaseDao<Organization>{
	
	/**
	 * 创建机构
	 * @param Oname 机构名字
	 * @param Oaddress 机构地址
	 * @param Oannounce 机构公告（简介）
	 * @param Uid 创建的用户id
	 * @return
	 */
	public long createOrg(String Oname, String Oaddress, String Oannounce, Long Uid);
	
	/**
	 * 解散机构
	 * @param Oid
	 */
	public int deleteOrg(long Oid);
	
	/**
	 * 更新机构名字
	 * @param Oid
	 * @param Oname
	 * @return
	 */
	public int updateOname(long Oid, String Oname);
	/**
	 * 更新机构地址
	 * @param Oid
	 * @param Oaddress
	 * @return
	 */
	public int updateOaddress(long Oid, String Oaddress);
	/**
	 * 更新机构公告
	 * @param Oid
	 * @param Oannounce
	 * @return
	 */
	public int updateOannounce(long Oid, String Oannounce);
	
	/**
	 * 获取机构
	 */
	public Organization getOrg(long Oid);
//机构与用户之间的关系
	
	/**
	 * 添加机构教师
	 * @param Oid
	 * @param Uid
	 * @return
	 */
	public int addOrgTeacher(long Oid, long Uid);
	
	/**
	 * 添加机构家长
	 * @param Oid
	 * @param Uid
	 * @return
	 */
	public int addOrgParent(long Oid, long Uid);
	
	/**
	 * 验证是否是该机构的管理员
	 * @param Uphone
	 * @param Oid
	 * @return
	 */
	int validAdmin(String Uphone, Long Oid);
}
