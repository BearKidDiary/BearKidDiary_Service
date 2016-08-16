package com.bearkiddiary.dao;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Weight;
import com.bearkiddiary.common.dao.BaseDao;

public interface WeightDao extends BaseDao<Weight>{
	/**
	 * Ϊ���������������
	 * 
	 * @param Kid
	 *            ���ӱ��
	 * @param Wweight
	 *            ��������
	 * @param Wtime
	 *            ����ʱ��
	 */
	int addWeightToKid(Long Kid, Float Wweight, Long Wtime);

	/**
	 * ��ȡ���ӵ�������Ϣ
	 * 
	 * @param Kid
	 *            ���ӱ��
	 * @param order
	 *            desc����asc
	 */
	List<Weight> getWeight(Long Kid, String order);
	
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
	List<Weight> getWeight(Long Kid, String order, int pageNum, int pageSize);
}
