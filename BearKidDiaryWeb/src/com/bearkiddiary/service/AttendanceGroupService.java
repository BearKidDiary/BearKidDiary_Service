package com.bearkiddiary.service;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.AttendanceGroup;
import com.bearkiddiary.bean.User;

public interface AttendanceGroupService {

	/**
	 * 添加考勤组
	 * @return 考勤组id
	 */
	Long addAttendanceGroup(AttendanceGroup Atdgroup, String Uphone);
	
	/**
	 * 删除
	 * @return
	 */
	int delete(Long AGid);

	/**
	 * 获取机构考勤组
	 * @param Uphone
	 * @return
	 */
	List<AttendanceGroup> getOrgAttendanceGroupList(String Uphone);
	
	/**
	 * 教师获取考勤组
	 * @param Uphone
	 * @return
	 */
	List<AttendanceGroup> getTeacherAttendanceGroupList(String Uphone);
	
	/**
	 * 更新考勤组
	 * @param AGid 
	 * @param AGstarttime
	 * @param AGendtime
	 * @param AGmonday
	 * @param AGtuesday
	 * @param AGwednesday
	 * @param AGthursday
	 * @param AGfriday
	 * @param AGsaturday
	 * @param AGsunday
	 * @return
	 */
	int update(Long AGid, String AGname, Long AGstarttime, Long AGendtime, Boolean AGmonday, Boolean AGtuesday, Boolean AGwednesday, 
			Boolean AGthursday, Boolean AGfriday, Boolean AGsaturday, Boolean AGsunday);

	/**
	 * 根据手机号码获取用户
	 * @param teachers_phone
	 * @return
	 */
	List<User> getTeacherWithPhone(List<String> teachers_phone);
	
	/**
	 * 更新打卡教师
	 * @param AGid
	 * @param teachers
	 * @return
	 */
	int updateTeachers(Long AGid, List<User> teachers);
}
