package com.bearkiddiary.dao;

import java.util.Set;

import com.bearkiddiary.bean.Course;
import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.common.dao.BaseDao;

public interface CourseDao extends BaseDao<Course> {

	/**
	 * ���ݿγ̱�Ż�ÿγ�
	 * 
	 * @param Cid
	 *            �γ̱��
	 */
	Course getCourse(Long Cid);

	/**
	 * ���ݺ��ӻ�ö�Ӧ�Ŀγ�
	 * 
	 * @param Kid
	 *            ���ӵı��
	 */
	Set<Course> getCourseByKid(Long Kid);

	/**
	 * �����ο���ʦ��ö�Ӧ�Ŀγ�
	 * 
	 * @param Uid
	 *            �ο���ʦ�ı��
	 * @param Uphone
	 *            �ο���ʦ���ֻ�����
	 */
	Set<Course> getCourseByTeacher(Long Uid, String Uphone);

	/**
	 * ���ݿγ����ڻ��������Ӧ�Ŀγ�
	 * 
	 * @param Oid
	 *            ������id
	 */
	Set<Course> getCourseByOrg(Long Oid);

	/**
	 * �½��γ�
	 * 
	 * @param data
	 *            �γ̵���Ϣ
	 * @param teacherUid
	 *            �ο���ʦ�ı��
	 * @param teacherUphone
	 *            �ο���ʦ���ֻ�����
	 * @param approverUid
	 *            �����˵ı��
	 * @param approverUphone
	 *            �����˵ĵ绰����
	 * @param Oid
	 *            ���ڻ����ı��
	 */
	int addCourse(Course data, Long teacherUid, String teacherUphone, Long approverUid, String approverUphone,
			Long Oid);

	/**
	 * ���¿γ���Ϣ
	 * 
	 * @param Cid
	 *            ָ���Ŀγ̱��
	 * @param Cclasstime
	 *            �Ͽ�ʱ��
	 * @param Cendtime
	 *            �¿�ʱ��
	 * @param Ctime
	 *            ��ѧʱ��
	 * @param Cofftime
	 *            ��ҵʱ��
	 * @param Cbackground
	 *            ���α���
	 * @param Cdesc
	 *            �γ�����
	 * @param Cname
	 *            �γ�����
	 * @param Cimage
	 *            �γ�����ͼƬURL
	 * @param Cmonday
	 *            ��һ�Ƿ��Ͽ�
	 * @param Ctuesday
	 *            �ܶ��Ƿ��Ͽ�
	 * @param Cwednesday
	 *            �����Ƿ��Ͽ�
	 * @param Cthursday
	 *            �����Ƿ��Ͽ�
	 * @param Cfriday
	 *            �����Ƿ��Ͽ�
	 * @param Csaturday
	 *            �����Ƿ��Ͽ�
	 * @param Csunday
	 *            �����Ƿ��Ͽ�
	 */
	int updateCourse(Long Cid, Long Cclasstime, Long Cendtime, Long Ctime, Long Cofftime, String Cbackground,
			String Cdesc, String Cname, String Cimage, Boolean Cmonday, Boolean Ctuesday, Boolean Cwednesday,
			Boolean Cthursday, Boolean Cfriday, Boolean Csaturday, Boolean Csunday);

	/**
	 * �����γ̵��ο���ʦ
	 * 
	 * @param Cid
	 *            �γ̱��
	 * @param Uphone
	 *            ��ʦ���ֻ�����
	 * @param Uid
	 *            ��ʦ�ı��
	 */
	int updateCourseTeacher(Long Cid, String Uphone, Long Uid);

	/**
	 * ��ָ��������ӵ���Ӧ�Ŀγ�
	 * 
	 * @param Cid
	 *            �γ̱��
	 * @param Kid
	 *            ���ӱ��
	 * @return
	 */
	int addKidToCourse(Long Cid, Long Kid);

	/**
	 * ��ָ�����Ӵӿγ����Ƴ�
	 * 
	 * @param Cid
	 *            �γ̱��
	 * @param Kid
	 *            ���ӱ��
	 */
	int removeKidFromCourse(Long Cid, Long Kid);

	/**
	 * ���ָ���γ��е�����ѧ��
	 * 
	 * @param Cid
	 *            �γ̱��
	 */
	Set<Kid> getKidsInCourse(Long Cid);
}
