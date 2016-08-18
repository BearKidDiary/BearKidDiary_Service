package com.bearkiddiary.dao;

import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.BaseDao;

public interface OrgDao extends BaseDao<Organization>{
	
	/**
	 * ��������
	 * @param Oname ��������
	 * @param Oaddress ������ַ
	 * @param Oannounce �������棨��飩
	 * @param Uid �������û�id
	 * @return
	 */
	public long createOrg(String Oname, String Oaddress, String Oannounce, Long Uid);
	
	/**
	 * ��ɢ����
	 * @param Oid
	 */
	public int deleteOrg(long Oid);
	
	/**
	 * ���»�������
	 * @param Oid
	 * @param Oname
	 * @return
	 */
	public int updateOname(long Oid, String Oname);
	/**
	 * ���»�����ַ
	 * @param Oid
	 * @param Oaddress
	 * @return
	 */
	public int updateOaddress(long Oid, String Oaddress);
	/**
	 * ���»�������
	 * @param Oid
	 * @param Oannounce
	 * @return
	 */
	public int updateOannounce(long Oid, String Oannounce);
	
	/**
	 * ��ȡ����
	 */
	public Organization getOrg(long Oid);
//�������û�֮��Ĺ�ϵ
	
	/**
	 * ��ӻ�����ʦ
	 * @param Oid
	 * @param Uid
	 * @return
	 */
	public int addOrgTeacher(long Oid, long Uid);
	
	/**
	 * ��ӻ����ҳ�
	 * @param Oid
	 * @param Uid
	 * @return
	 */
	public int addOrgParent(long Oid, long Uid);
	
	/**
	 * ��֤�Ƿ��Ǹû����Ĺ���Ա
	 * @param Uphone
	 * @param Oid
	 * @return
	 */
	int validAdmin(String Uphone, Long Oid);
}
