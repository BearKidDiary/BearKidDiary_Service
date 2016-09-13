package com.bearkiddiary.dao;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Group;
import com.bearkiddiary.common.dao.BaseDao;

public interface GroupDao extends BaseDao<Group> {
	/**
	 * 创建分组
	 * 
	 * @param Oid
	 *            机构编号
	 * @param name
	 *            分组名字
	 */
	int createGroup(Long Oid, String Gname);

	/**
	 * 创建分组并添加成员
	 * 
	 * @param Oid
	 *            机构的编号
	 * @param Gname
	 *            机构的名字
	 * @param Uids
	 *            成员的编号
	 */
	int createGroupWithId(Long Oid, String Gname, List<Long> Uids);

	/**
	 * 创建分组并添加成员
	 * 
	 * @param Oid
	 *            机构的编号
	 * @param Gname
	 *            分组的名字
	 * @param Uphones
	 *            成员的手机号码
	 */
	int createGroupWithPhone(Long Oid, String Gname, List<String> Uphones);

	/**
	 * 删除分组
	 * 
	 * @param Gid
	 *            分组的编号
	 */
	int deleteGroup(Long Gid);

	/**
	 * 添加成员到分组中
	 * 
	 * @param Gid
	 *            分组编号
	 * @param Uids
	 *            用户的编号
	 */
	int addMembersById(Long Gid, List<Long> Uids);

	/**
	 * 添加成员到分组中
	 * 
	 * @param Gid
	 *            分组的编号
	 * @param Uphones
	 *            用户的手机号码
	 */
	int addMembersByPhone(Long Gid, List<String> Uphones);

	/**
	 * 删除分组中的用户
	 * 
	 * @param Gid
	 *            分组的编号
	 * @param Uids
	 *            用户的编号
	 */
	int deleteMembersById(Long Gid, List<Long> Uids);

	/**
	 * 删除分组中的用户
	 * 
	 * @param Gid
	 *            分组的编号
	 * @param Uphones
	 *            用户的手机号码
	 */
	int deleteMembersByPhone(Long Gid, List<String> Uphones);

	/**
	 * 查询分组信息 包括分组所在的机构以及分组的成员
	 * 
	 * @param Gid
	 *            分组的编号
	 */
	Group getGroup(Long Gid);

	/**
	 * 查询机构对应有哪些分组
	 * 
	 * @param Oid
	 *            机构的编号
	 */
	Set<Group> getGroups(Long Oid);
}
