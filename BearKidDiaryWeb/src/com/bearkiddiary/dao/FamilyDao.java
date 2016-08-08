package com.bearkiddiary.dao;

import java.util.Set;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.BaseDao;

public interface FamilyDao extends BaseDao<Family> {
	public static final int ERROR_NO_USER = -1;
	public static final int ERROR_ALREADY_HAVE_FAMILY = -2;
	public static final int SUCESS = 0;

	/**
	 * ����һ����ͥ
	 * 
	 * @param Uphone
	 * @param Fname
	 * @return �Ƿ񴴽��ɹ�
	 */
	int createFamily(String Uphone, String Fname);

	/**
	 * ��ȡ�����ļ�ͥ
	 * 
	 * @param Uphone
	 * @return �����ļ�ͥ
	 */
	Family getCreatedFramily(String Uphone);

	/**
	 * ��ȡ����ļ�ͥ�������������ļ�ͥ��
	 * 
	 * @param Uphone
	 * @return ����ļ�ͥ
	 */
	Set<Family> getAttendedFramily(String Uphone);

	/**
	 * ��ȡidΪFid�ļ�ͥ�е����м�ͥ��Ա�������������ߣ�
	 * 
	 * @param Fid
	 * @return
	 */
	Set<User> getMembersInFamily(long Fid);

	/**
	 * ��ȡ������ΪUphone�ļ�ͥ�����г�Ա�������������ߣ�
	 * 
	 * @param Uphone
	 * @return
	 */
	Set<User> getMembersInFamily(String Uphone);

	/**
	 * ��ȡidΪFid�ļ�ͥ�еĴ�����
	 * 
	 * @param Fid
	 * @return
	 */
	Set<User> getCreatorInFamily(long Fid);

	/**
	 * ��ȡidΪFid�ļ�ͥ�е����м�ͥ��Ա�����������ߣ�
	 * 
	 * @param Fid
	 * @return
	 */
	Set<User> getMembersAndCreatorInFamily(long Fid);

	/**
	 * ��ȡ������ΪUphone�ļ�ͥ�����г�Ա�����������ߣ�
	 * 
	 * @param Uphone
	 * @return
	 */
	Set<User> getMembersAndCreatorInFamily(String Uphone);
}
