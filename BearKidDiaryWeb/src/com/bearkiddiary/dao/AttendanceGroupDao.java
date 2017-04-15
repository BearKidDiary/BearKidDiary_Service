package com.bearkiddiary.dao;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.AttendanceGroup;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.BaseDao;


public interface AttendanceGroupDao extends BaseDao<AttendanceGroup>{
	
	/**
	 * ��ӿ�����
	 * @param Atdgroup ��������Ϣ
	 * @param Oid ����id
	 * @return AGid
	 */
	Long addAttendanceGroup(AttendanceGroup Atdgroup, String Uphone);
	
	/**
	 * ����id��ȡ������
	 * @param AGid ������id
	 * @return
	 */
	AttendanceGroup getAttendanceGruop(Long AGid);
	
	/**
	 * ɾ��������
	 * @param AGid ������id
	 * @return ɾ���ɹ����
	 */
	int deleteAttendanceGroup(Long AGid);
	
	/**
	 * ���¿�����
	 * @param AGid ������id
	 * @param AGstarttime ���ڿ�ʼʱ��
	 * @param AGendtime ���ڽ���ʱ��
	 * @param AGmonday  ����һ
	 * @param AGtuesday ���ڶ�
	 * @param AGwednesday ������
	 * @param AGthursday ������
	 * @param AGfriday ������
	 * @param AGsaturday ������
	 * @param AGsunday ������
	 * @return
	 */
	int updateAttendanceGroup(Long AGid, String AGname, Long AGstarttime, Long AGendtime, Boolean AGmonday, Boolean AGtuesday, Boolean AGwednesday, 
			Boolean AGthursday, Boolean AGfriday, Boolean AGsaturday, Boolean AGsunday);
	
	/**
	 * ���ӻ��޸Ŀ������ʦ
	 * @param teachers
	 * @return
	 */
	int addTeachers(Long AGid, Set<User> teachers);
	
	/**
	 * ��ȡ�����Ŀ������б�
	 * @param Oid ����id
	 * @return
	 */
	List<AttendanceGroup> getOrgAttendanceGroupList(String Uphone);
	
	/**
	 * ��ȡĳ����ʦ�Ŀ�����
	 * @param Uid
	 * @return
	 */
	List<AttendanceGroup> getTeacherAttendanceGroupList(String Uphone);
}
