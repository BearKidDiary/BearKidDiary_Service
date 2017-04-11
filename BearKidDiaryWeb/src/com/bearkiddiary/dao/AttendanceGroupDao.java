package com.bearkiddiary.dao;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.AttendanceGroup;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.BaseDao;


public interface AttendanceGroupDao extends BaseDao<AttendanceGroup>{
	
	/**
	 * 添加考勤组
	 * @param Atdgroup 考勤组信息
	 * @param Oid 机构id
	 * @return AGid
	 */
	Long addAttendanceGroup(AttendanceGroup Atdgroup, String Uphone);
	
	/**
	 * 根据id获取考勤组
	 * @param AGid 考勤组id
	 * @return
	 */
	AttendanceGroup getAttendanceGruop(Long AGid);
	
	/**
	 * 删除考勤组
	 * @param AGid 考勤组id
	 * @return 删除成功与否
	 */
	int deleteAttendanceGroup(Long AGid);
	
	/**
	 * 更新考勤组
	 * @param AGid 考勤组id
	 * @param AGstarttime 考勤开始时间
	 * @param AGendtime 考勤结束时间
	 * @param AGmonday  星期一
	 * @param AGtuesday 星期二
	 * @param AGwednesday 星期三
	 * @param AGthursday 星期四
	 * @param AGfriday 星期五
	 * @param AGsaturday 星期六
	 * @param AGsunday 星期日
	 * @return
	 */
	int updateAttendanceGroup(Long AGid, Long AGstarttime, Long AGendtime, Boolean AGmonday, Boolean AGtuesday, Boolean AGwednesday, 
			Boolean AGthursday, Boolean AGfriday, Boolean AGsaturday, Boolean AGsunday);
	
	/**
	 * 增加或修改考勤组教师
	 * @param teachers
	 * @return
	 */
	int addTeachers(Long AGid, Set<User> teachers);
	
	/**
	 * 获取机构的考勤组列表
	 * @param Oid 机构id
	 * @return
	 */
	List<AttendanceGroup> getOrgAttendanceGroupList(String Uphone);
	
	/**
	 * 获取某个老师的考勤组
	 * @param Uid
	 * @return
	 */
	List<AttendanceGroup> getTeacherAttendanceGroupList(String Uphone);
}
