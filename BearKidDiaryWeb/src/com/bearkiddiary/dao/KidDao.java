package com.bearkiddiary.dao;

import java.util.Set;

import com.bearkiddiary.bean.Kid;
import com.bearkiddiary.common.dao.BaseDao;

public interface KidDao extends BaseDao<Kid> {
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
	 */
	int addKid(Long Fid);

	/**
	 * ���һ�����ӵ��û������ļ�ͥ��
	 * 
	 * @param Uphone
	 *            �����ߵ��ֻ���
	 */
	int addKid(String Uphone);

	/**
	 * ���º��ӵ���Ϣ
	 * 
	 * @param Kid
	 *            ���ӱ��
	 * @param kid
	 *            ������Ϣ
	 */
	int updateKid(Long Kid, Kid kid);
}
