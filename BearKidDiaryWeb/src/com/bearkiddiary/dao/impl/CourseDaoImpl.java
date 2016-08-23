package com.bearkiddiary.dao.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Course;
import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.bean.Organization;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.impl.BaseDaoHibernate;
import com.bearkiddiary.dao.CourseDao;
import com.bearkiddiary.dao.KidDao;
import com.bearkiddiary.dao.OrgDao;
import com.bearkiddiary.dao.UserDao;
import com.bearkiddiary.utils.ResultCode;

public class CourseDaoImpl extends BaseDaoHibernate<Course> implements CourseDao {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private OrgDao orgDao;

	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

	private KidDao kidDao;

	public void setKidDao(KidDao kidDao) {
		this.kidDao = kidDao;
	}

	@Override
	public Course getCourse(Long Cid) {
		final String hql = "select c from Course c where c.Cid = ?0";
		List<Course> list = find(hql, Cid);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Set<Course> getCourseByKid(Long Kid) {
		final String hql = "select c from Course c join c.students kid where kid.Kid = ?0";
		List<Course> list = find(hql, Kid);
		return new HashSet<>(list);
	}

	@Override
	public Set<Course> getCourseByTeacher(Long Uid, String Uphone) {
		if (Uid != null) {
			final String hql = "select c from Course c join c.teacher user where user.Uid = ?0";
			List<Course> list = find(hql, Uid);
			return new HashSet<>(list);
		}
		if (Uphone != null) {
			final String hql = "select c from Course c join c.teacher user where user.Uphone = ?0";
			List<Course> list = find(hql, Uphone);
			return new HashSet<>(list);
		}
		return null;
	}

	@Override
	public Set<Course> getCourseByOrg(Long Oid) {
		final String hql = "select c from Course c join c.organization org where org.Oid = ?0";
		List<Course> list = find(hql, Oid);
		if (list != null && list.size() > 0)
			return new HashSet<>(list);
		return null;
	}

	@Override
	public int addCourse(Course data, Long teacherUid, String teacherUphone, Long approverUid, String approverUphone,
			Long Oid) {
		User teacher = null;
		if (teacherUid != null) {
			teacher = userDao.getUser(teacherUid);
		}
		if (teacher == null && teacherUphone != null) {
			teacher = userDao.getUser(teacherUphone);
		}
		if (teacher == null) {
			return ResultCode.ERROR_NO_USER;
		}

		User approver = null;
		if (approverUid != null) {
			approver = userDao.getUser(approverUid);
		}
		if (approver == null && approverUphone != null) {
			approver = userDao.getUser(approverUphone);
		}
		if (approver == null) {
			return ResultCode.ERROR_NO_USER;
		}

		Organization org = orgDao.getOrg(Oid);
		if (org == null) {
			return ResultCode.ERROR_NO_ORG;
		}

		data.setTeacher(teacher);
		data.setApprover(approver);
		data.setOrganization(org);
		save(data);
		return ResultCode.SUCCESS;
	}

	@Override
	public int updateCourse(Long Cid, Long Cclasstime, Long Cendtime, Long Ctime, Long Cofftime, String Cbackground,
			String Cdesc, String Cname, String Cimage, Boolean Cmonday, Boolean Ctuesday, Boolean Cwednesday,
			Boolean Cthursday, Boolean Cfriday, Boolean Csaturday, Boolean Csunday) {
		Course course = getCourse(Cid);
		if (course == null) {
			return ResultCode.ERROR_NO_COURSE;
		}

		if (Cclasstime != null)
			course.setCclasstime(Cclasstime);
		if (Cendtime != null)
			course.setCendtime(Cendtime);
		if (Ctime != null)
			course.setCtime(Ctime);
		if (Cofftime != null)
			course.setCofftime(Cofftime);
		if (Cbackground != null)
			course.setCbackground(Cbackground);
		if (Cdesc != null)
			course.setCdesc(Cdesc);
		if (Cname != null)
			course.setCname(Cname);
		if (Cimage != null)
			course.setCimage(Cimage);
		if (Cmonday != null)
			course.setCmonday(Cmonday);
		if (Ctuesday != null)
			course.setCtuesday(Ctuesday);
		if (Cwednesday != null)
			course.setCwednesday(Cwednesday);
		if (Cthursday != null)
			course.setCthursday(Cthursday);
		if (Cfriday != null)
			course.setCfriday(Cfriday);
		if (Csaturday != null)
			course.setCsaturday(Csaturday);
		if (Csunday != null)
			course.setCsunday(Csunday);
		update(course);
		return ResultCode.SUCCESS;
	}

	@Override
	public int updateCourseTeacher(Long Cid, String Uphone, Long Uid) {
		Course course = getCourse(Cid);
		if (course == null)
			return ResultCode.ERROR_NO_COURSE;

		User teacher = null;
		if (Uid != null)
			teacher = userDao.getUser(Uid);
		if (teacher == null && Uphone != null)
			teacher = userDao.getUser(Uphone);
		if (teacher == null)
			return ResultCode.ERROR_NO_USER;

		course.setTeacher(teacher);
		update(course);
		return ResultCode.SUCCESS;
	}

	@Override
	public int addKidToCourse(Long Cid, Long Kid) {
		Course course = getCourse(Cid);
		if (course == null)
			return ResultCode.ERROR_NO_COURSE;

		Kid kid = kidDao.getKid(Kid);
		if (kid == null)
			return ResultCode.ERROR_NO_KID;

		boolean success = course.getStudents().add(kid);
		if (success) {
			update(course);
			return ResultCode.SUCCESS;
		} else {
			return ResultCode.ERROR_EXIST_KID;
		}
	}

	@Override
	public int removeKidFromCourse(Long Cid, Long Kid) {
		Course course = getCourse(Cid);
		if (course == null)
			return ResultCode.ERROR_NO_COURSE;

		Kid kid = kidDao.getKid(Kid);
		if (kid == null)
			return ResultCode.ERROR_NO_KID;

		boolean success = course.getStudents().remove(kid);
		if (success) {
			update(course);
			return ResultCode.SUCCESS;
		} else {
			return ResultCode.ERROR_NO_KID;
		}
	}

	@Override
	public Set<Kid> getKidsInCourse(Long Cid) {
		Course course = getCourse(Cid);
		if (course != null) {
			course.getStudents().size();
			return course.getStudents();
		}
		return null;
	}
}
