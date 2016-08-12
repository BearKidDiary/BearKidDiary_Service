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
	public void deleteOrg(long Oid);
	
	/**
	 * ���»�������
	 * @param Oid
	 * @param Oname
	 * @return
	 */
	public long updateOname(long Oid, String Oname);
	/**
	 * ���»�����ַ
	 * @param Oid
	 * @param Oaddress
	 * @return
	 */
	public long updateOaddress(long Oid, String Oaddress);
	/**
	 * ���»�������
	 * @param Oid
	 * @param Oannounce
	 * @return
	 */
	public long updateOannounce(long Oid, String Oannounce);
}
