package com.bearkiddiary.dao;

import java.util.Set;

import com.bearkiddiary.bean.Family;
import com.bearkiddiary.bean.User;
import com.bearkiddiary.common.dao.BaseDao;

public interface FamilyDao extends BaseDao<Family> {

	Family getFamily(Long Fid);

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
	User getCreatorInFamily(long Fid);

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

	/**
	 * ��Ӽ�ͥ��Ա����ͥ��
	 * 
	 * @param Fid
	 *            ��ͥ���
	 * @param Uid
	 *            ��ͥ��Ա���
	 */
	int addMemberToFamily(Long Fid, Long Uid);

	/**
	 * ��Ӽ�ͥ��Ա����ͥ��
	 * 
	 * @param Fid
	 *            ��ͥ���
	 * @param Uphone
	 *            ��Ա���ֻ�����
	 */
	int addMemberToFamily(Long Fid, String Uphone);

	/**
	 * ��Ӽ�ͥ��Ա���û������ļ�ͥ��
	 * 
	 * @param Uphone
	 *            �����ߵ��ֻ�����
	 * @param Uid
	 *            ��ͥ��Ա
	 */
	int addMemberToFamily(String Uphone, Long Uid);

	/**
	 * ��Ӽ�ͥ��Ա���û������ļ�ͥ��
	 * 
	 * @param creatorPhone
	 *            �����ߵ��ֻ�����
	 * @param memberPhone
	 *            ��Ա���ֻ�����
	 * @return
	 */
	int addMemberToFamily(String creatorPhone, String memberPhone);
}
