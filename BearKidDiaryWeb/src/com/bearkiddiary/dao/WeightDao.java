package com.bearkiddiary.dao;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Weight;
import com.bearkiddiary.common.dao.BaseDao;

public interface WeightDao extends BaseDao<Weight>{
	/**
	 * 为孩子添加体重数据
	 * 
	 * @param Kid
	 *            孩子编号
	 * @param Wweight
	 *            体重数据
	 * @param Wtime
	 *            更新时间
	 */
	int addWeightToKid(Long Kid, Float Wweight, Long Wtime);

	/**
	 * 获取孩子的体重信息
	 * 
	 * @param Kid
	 *            孩子编号
	 * @param order
	 *            desc或者asc
	 */
	List<Weight> getWeight(Long Kid, String order);
	
	/**
	 * 分页获取孩子的体重信息
	 * 
	 * @param Kid
	 *            孩子的编号
	 * @param order
	 *            desc或者asc
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页大小
	 */
	List<Weight> getWeight(Long Kid, String order, int pageNum, int pageSize);
}
