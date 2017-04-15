package com.bearkiddiary.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.AttendanceGroup;
import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.AttendanceGroupDao;
import com.bearkiddiary.dao.OrgDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.utils.ResultCode;

public class AttendanceGroupDaoImpl extends BaseDaoHibernate<AttendanceGroup> implements AttendanceGroupDao{

	private OrgDao orgDao;

	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Long addAttendanceGroup(AttendanceGroup Atdgroup, String Uphone) {
		// TODO Auto-generated method stub
		Organization org = orgDao.getOrg(Uphone);
		if(org == null)
			return (long) ResultCode.ERROR_NO_ORG;
		Atdgroup.setOrganization(org);
		Long AGid = (Long) save(Atdgroup);
		return AGid;
	}

	@Override
	public int deleteAttendanceGroup(Long AGid) {
		AttendanceGroup attendanceGroup = getAttendanceGruop(AGid);
		if(attendanceGroup == null)
			return ResultCode.ERROR_NO_ATTENDANCEGROUP;
		delete(AttendanceGroup.class, AGid);
		return ResultCode.SUCCESS;
	}

	@Override
	public int updateAttendanceGroup(Long AGid, String AGname, Long AGstarttime, Long AGendtime, Boolean AGmonday, Boolean AGtuesday,
			Boolean AGwednesday, Boolean AGthursday, Boolean AGfriday, Boolean AGsaturday, Boolean AGsunday) {
		AttendanceGroup attendancegruop = getAttendanceGruop(AGid);
		if(attendancegruop == null){
			return ResultCode.ERROR_NO_ATTENDANCEGROUP;
		}
		if(AGname != null){
			attendancegruop.setAGname(AGname);
		}
		if(AGstarttime != null)
			attendancegruop.setAGstarttime(AGstarttime);
		if(AGendtime != null)
			attendancegruop.setAGendtime(AGendtime);
		if(AGmonday != null)
			attendancegruop.setAGmonday(AGmonday);
		if(AGtuesday != null)
			attendancegruop.setAGtuesday(AGtuesday);
		if(AGwednesday != null)
			attendancegruop.setAGwednesday(AGwednesday);
		if(AGthursday != null)
			attendancegruop.setAGthursday(AGthursday);
		if(AGfriday != null)
			attendancegruop.setAGfriday(AGfriday);
		if(AGsaturday != null)
			attendancegruop.setAGsaturday(AGsaturday);
		if(AGsunday != null)
			attendancegruop.setAGsunday(AGsunday);
		update(attendancegruop);
		return ResultCode.SUCCESS;
	}

	@Override
	public int addTeachers(Long AGid, Set<User> teachers) {
		AttendanceGroup attendanceGroup = getAttendanceGruop(AGid);
		if(attendanceGroup == null){
			return ResultCode.ERROR_NO_ATTENDANCEGROUP;
		}
		attendanceGroup.setTeachers(teachers);
		update(attendanceGroup);
		return ResultCode.SUCCESS;
	}

	@Override
	public List<AttendanceGroup> getOrgAttendanceGroupList(String Uphone) {
		Organization organization = orgDao.getOrg(Uphone);
		if(organization == null)
			return null;
		String hql = "select attendancegroup from AttendanceGroup attendancegroup where attendancegroup.organization = ?0";
		List<AttendanceGroup> list = find(hql, organization);
		return list;
	}

	@Override
	public List<AttendanceGroup> getTeacherAttendanceGroupList(String Uphone) {
		User user = userDao.getUser(Uphone);
		if(user == null)
			return null;
		List<AttendanceGroup> list = new ArrayList<>(user.getAttendancegroup());
		return list;
	}

	@Override
	public AttendanceGroup getAttendanceGruop(Long AGid) {
		AttendanceGroup attendancegroup = get(AttendanceGroup.class, AGid);
		return attendancegroup;
	}
	
}
