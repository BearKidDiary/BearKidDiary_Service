package com.bearkiddiary.dao;

import java.util.List;

import com.bearkiddiary.bean.Leave_Application;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.BaseDao;

/**
 * 请假申请Dao
 * @author admin
 *
 */
public interface LADao extends BaseDao<Leave_Application>{
	
	/**
	 * 提交申请
	 * @param application
	 * @return
	 */
	Long commitApplicaton(Leave_Application application);
	
	/**
	 * 更新请假申请的状态
	 * @param LAstatus
	 * @param LAcomment
	 * @return
	 */
	int updateApplication(Integer LAstatus, Integer LAisapproved, User LArover, String LAcomment, Long LAid);
	
	/**
	 * 获取机构的请假申请列表
	 * @param Oid
	 * @return
	 */
	List<Leave_Application> getOrgApplicationList(Long Oid, int LAstatus);
	
	/**
	 * 教师获取个人请假的申请列表
	 * @param Uid
	 * @return
	 */
	List<Leave_Application> getUserApplicationList(Long Uid);
	
	/**
	 * 教师获取个人请假的申请列表
	 * @param Uphone
	 * @return
	 */
	List<Leave_Application> getUserApplicationList(String Uphone);
}
