package com.bearkiddiary.dao;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.AttendanceGroup;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.BaseDao;


public interface AttendanceGroupDao extends BaseDao<AttendanceGroup>{
	
	/**
	 * 添加考勤组
	 */
	Long addAttendanceGroup(AttendanceGroup Atdgroup, String Uphone);
	
	/**
	 * 根据AGid获取考勤组
	 * @param AGid ������id
	 * @return
	 */
	AttendanceGroup getAttendanceGruop(Long AGid);
	
	/**
	 * 删除考勤组
	 * @param AGid ������id
	 * @return ɾ���ɹ����
	 */
	int deleteAttendanceGroup(Long AGid);
	
	/**
	 * 更新考勤组
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
	 * 添加考勤组教师
	 * @param teachers
	 * @return
	 */
	int addTeachers(Long AGid, Set<User> teachers);
	
	/**
	 * 获取机构考勤组
	 * @param Oid ����id
	 * @return
	 */
	List<AttendanceGroup> getOrgAttendanceGroupList(String Uphone);
	
	/**
	 * 教师获取考勤组
	 * @param Uid
	 * @return
	 */
	List<AttendanceGroup> getTeacherAttendanceGroupList(String Uphone);
}
