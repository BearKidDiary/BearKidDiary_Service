package com.bearkiddiary.dao;

import java.util.Set;

import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.common.dao.BaseDao;

public interface KidDao extends BaseDao<Kid> {

	/**
	 * ��ú�����Ϣ
	 * 
	 * @param Kid
	 *            ���ӵı��
	 */
	Kid getKid(Long Kid);

	/**
	 * ��ü�ͥ�е����к���
	 * 
	 * @param Fid
	 *            ��ͥ���
	 */
	Set<Kid> getKidsInFamily(Long Fid);

	/**
	 * ��ü�ͥ�е����к���
	 * 
	 * @param Uphone
	 *            �����ߵ��ֻ���
	 */
	Set<Kid> getKidsInFamily(String Uphone);

	/**
	 * ���һ�����ӵ���ͥ��
	 * 
	 * @param Fid
	 *            ��ͥ���
	 * @param kid
	 *            ���ӵ���Ϣ
	 */
	int addKid(Long Fid, Kid kid);

	/**
	 * ���һ�����ӵ��û������ļ�ͥ��
	 * 
	 * @param Uphone
	 *            �����ߵ��ֻ���
	 * @param kid
	 *            ������Ϣ
	 */
	int addKid(String Uphone, Kid kid);

	/**
	 * ���º��ӵ���Ϣ
	 * 
	 * @param Kid
	 *            ���ӱ��
	 * @param kid
	 *            ������Ϣ
	 */
	int updateKid(Long Kid, Kid kid);


	/**
	 * ɾ��һ������
	 * 
	 * @param Kid
	 *            ���ӵı��
	 */
	int removeKid(Long Kid);
}
