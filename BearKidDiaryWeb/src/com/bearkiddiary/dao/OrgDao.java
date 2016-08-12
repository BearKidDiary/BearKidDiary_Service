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
	public void deleteOrg(long Oid);
	
	/**
	 * 更新机构名字
	 * @param Oid
	 * @param Oname
	 * @return
	 */
	public long updateOname(long Oid, String Oname);
	/**
	 * 更新机构地址
	 * @param Oid
	 * @param Oaddress
	 * @return
	 */
	public long updateOaddress(long Oid, String Oaddress);
	/**
	 * 更新机构公告
	 * @param Oid
	 * @param Oannounce
	 * @return
	 */
	public long updateOannounce(long Oid, String Oannounce);
}
