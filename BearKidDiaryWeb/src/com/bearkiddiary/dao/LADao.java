package com.bearkiddiary.dao;

import java.util.List;

import com.bearkiddiary.bean.Leave_Application;
import com.bearkiddiary.common.dao.BaseDao;

/**
 * �������Dao
 * @author admin
 *
 */
public interface LADao extends BaseDao<Leave_Application>{
	
	/**
	 * �ύ����
	 * @param application
	 * @return
	 */
	Long commitApplicaton(Leave_Application application);
	
	/**
	 * ������������״̬
	 * @param LAstatus
	 * @param LAcomment
	 * @return
	 */
	Long updateApplication(Integer LAstatus, String LAcomment, Long LAid);
	
	/**
	 * ��ȡ��������������б�
	 * @param Oid
	 * @return
	 */
	List<Leave_Application> getOrgApplicationList(Long Oid);
	
	/**
	 * ��ʦ��ȡ������ٵ������б�
	 * @param Uid
	 * @return
	 */
	List<Leave_Application> getUserApplicationList(Long Uid);
}
