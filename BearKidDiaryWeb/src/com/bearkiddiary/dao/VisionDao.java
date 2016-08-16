package com.bearkiddiary.dao;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Vision;
import com.bearkiddiary.common.dao.BaseDao;

public interface VisionDao extends BaseDao<Vision>{
	/**
	 * Ϊ���������������
	 * 
	 * @param Kid
	 *            ���ӱ��
	 * @param Vleft
	 *            ��������
	 * @param Vright
	 *            ��������
	 * @param Vtime
	 *            ����ʱ��
	 */
	int addVision(Long Kid, Float Vleft, Float Vright, Long Vtime);

	/**
	 * ��ȡ���ӵ�������Ϣ
	 * 
	 * @param Kid
	 *            ���ӵı��
	 * @param order
	 *            desc����asc
	 */
	List<Vision> getVision(Long Kid, String order);

	/**
	 * ��ҳ��ȡ���ӵ�������Ϣ
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
	List<Vision> getVision(Long Kid, String order, int pageNum, int pageSize);
}
