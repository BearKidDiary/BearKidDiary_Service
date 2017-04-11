package com.bearkiddiary.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.AttendanceGroup;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.dao.AttendanceGroupDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.service.AttendanceGroupService;
import com.bearkiddiary.utils.ResultCode;

public class AttendanceGroupServiceImpl implements AttendanceGroupService{
	
	private AttendanceGroupDao attendanceGroupDao;

	public void setAttendanceGroupDao(AttendanceGroupDao attendanceGroupDao) {
		this.attendanceGroupDao = attendanceGroupDao;
	}

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Long addAttendanceGroup(AttendanceGroup Atdgroup, String Uphone) {
		return attendanceGroupDao.addAttendanceGroup(Atdgroup, Uphone);
	}

	@Override
	public int delete(Long AGid) {
		return attendanceGroupDao.deleteAttendanceGroup(AGid);
	}

	@Override
	public List<AttendanceGroup> getOrgAttendanceGroupList(String Uphone) {
		return attendanceGroupDao.getOrgAttendanceGroupList(Uphone);
	}

	@Override
	public List<AttendanceGroup> getTeacherAttendanceGroupList(String Uphone) {
		return attendanceGroupDao.getTeacherAttendanceGroupList(Uphone);
	}

	@Override
	public int update(Long AGid, Long AGstarttime, Long AGendtime, Boolean AGmonday, Boolean AGtuesday,
			Boolean AGwednesday, Boolean AGthursday, Boolean AGfriday, Boolean AGsaturday, Boolean AGsunday) {
		int resultCode = attendanceGroupDao.updateAttendanceGroup(AGid, AGstarttime, AGendtime, AGmonday, AGtuesday, AGwednesday, AGthursday, AGfriday, AGsaturday, AGsunday);
		return resultCode;
	}

	
	
	@Override
	public int updateTeachers(Long AGid, List<User> teachers) {
		Set<User> teacher = new HashSet<>(teachers);
		return attendanceGroupDao.addTeachers(AGid, teacher);
	}

	@Override
	public List<User> getTeacherWithPhone(String[] teachers_phone) {
		List<User> list = new ArrayList<>();
		for(String Uphone : teachers_phone){
			User user = userDao.getUser(Uphone);
			list.add(user);
		}
		return list;
	}

}