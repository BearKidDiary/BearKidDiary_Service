package com.bearkiddiary.dao;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Height;
import com.bearkiddiary.common.dao.BaseDao;

public interface HeightDao extends BaseDao<Height> {
	/**
	 * Ϊ��������������
	 * 
	 * @param Kid
	 *            ���ӱ��
	 * @param Hheight
	 *            �������
	 * @param Htime
	 *            ����ʱ��
	 */
	int addHeightToKid(Long Kid, Float Hheight, Long Htime);

	/**
	 * ��ȡ���ӵ��������
	 * 
	 * @param Kid
	 *            ���ӵı��
	 * @param order
	 *            asc,����������,descΪ����
	 */
	List<Height> getHeight(Long Kid, String order);

	/**
	 * ��ҳ��ȡ���ӵ������Ϣ
	 * 
	 * @param Kid
	 *            ���ӵı��
	 * @param order
	 *            desc����asc
	 * @param pageNum
	 *            ҳ��
	 * @param pageSize
	 *            ҳ��С
	 */
	List<Height> getHeight(Long Kid, String order, int pageSize, int pageNum);
}
