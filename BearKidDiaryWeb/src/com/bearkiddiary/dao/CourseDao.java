package com.bearkiddiary.dao;

import java.util.Set;

import com.bearkiddiary.bean.Course;
import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.common.dao.BaseDao;

public interface CourseDao extends BaseDao<Course> {

	/**
	 * 根据课程编号获得课程
	 * 
	 * @param Cid
	 *            课程编号
	 */
	Course getCourse(Long Cid);

	/**
	 * 根据孩子获得对应的课程
	 * 
	 * @param Kid
	 *            孩子的编号
	 */
	Set<Course> getCourseByKid(Long Kid);

	/**
	 * 根据任课老师获得对应的课程
	 * 
	 * @param Uid
	 *            任课老师的编号
	 * @param Uphone
	 *            任课老师的手机号码
	 */
	Set<Course> getCourseByTeacher(Long Uid, String Uphone);

	/**
	 * 根据课程所在机构获得相应的课程
	 * 
	 * @param Oid
	 *            机构的id
	 */
	Set<Course> getCourseByOrg(Long Oid);

	/**
	 * 新建课程
	 * 
	 * @param data
	 *            课程的信息
	 * @param teacherUid
	 *            任课老师的编号
	 * @param teacherUphone
	 *            任课老师的手机号码
	 * @param approverUid
	 *            审批人的编号
	 * @param approverUphone
	 *            审批人的电话号码
	 * @param Oid
	 *            所在机构的编号
	 */
	int addCourse(Course data, Long teacherUid, String teacherUphone, Long approverUid, String approverUphone,
			Long Oid);

	/**
	 * 更新课程信息
	 * 
	 * @param Cid
	 *            指定的课程编号
	 * @param Cclasstime
	 *            上课时间
	 * @param Cendtime
	 *            下课时间
	 * @param Ctime
	 *            开学时间
	 * @param Cofftime
	 *            毕业时间
	 * @param Cbackground
	 *            开课背景
	 * @param Cdesc
	 *            课程描述
	 * @param Cname
	 *            课程名字
	 * @param Cimage
	 *            课程描述图片URL
	 * @param Cmonday
	 *            周一是否上课
	 * @param Ctuesday
	 *            周二是否上课
	 * @param Cwednesday
	 *            周三是否上课
	 * @param Cthursday
	 *            周四是否上课
	 * @param Cfriday
	 *            周五是否上课
	 * @param Csaturday
	 *            周六是否上课
	 * @param Csunday
	 *            周日是否上课
	 */
	int updateCourse(Long Cid, Long Cclasstime, Long Cendtime, Long Ctime, Long Cofftime, String Cbackground,
			String Cdesc, String Cname, String Cimage, Boolean Cmonday, Boolean Ctuesday, Boolean Cwednesday,
			Boolean Cthursday, Boolean Cfriday, Boolean Csaturday, Boolean Csunday);

	/**
	 * 更换课程的任课老师
	 * 
	 * @param Cid
	 *            课程编号
	 * @param Uphone
	 *            老师的手机号码
	 * @param Uid
	 *            老师的编号
	 */
	int updateCourseTeacher(Long Cid, String Uphone, Long Uid);

	/**
	 * 把指定孩子添加到对应的课程
	 * 
	 * @param Cid
	 *            课程编号
	 * @param Kid
	 *            孩子编号
	 * @return
	 */
	int addKidToCourse(Long Cid, Long Kid);

	/**
	 * 把指定孩子从课程中移除
	 * 
	 * @param Cid
	 *            课程编号
	 * @param Kid
	 *            孩子编号
	 */
	int removeKidFromCourse(Long Cid, Long Kid);

	/**
	 * 获得指定课程中的所有学生
	 * 
	 * @param Cid
	 *            课程编号
	 */
	Set<Kid> getKidsInCourse(Long Cid);
}
