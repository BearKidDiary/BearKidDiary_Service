package com.bearkiddiary.dao;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Vision;
import com.bearkiddiary.common.dao.BaseDao;

public interface VisionDao extends BaseDao<Vision>{
	/**
	 * 为孩子添加视力数据
	 * 
	 * @param Kid
	 *            孩子编号
	 * @param Vleft
	 *            左眼视力
	 * @param Vright
	 *            右眼视力
	 * @param Vtime
	 *            更新时间
	 */
	int addVision(Long Kid, Float Vleft, Float Vright, Long Vtime);

	/**
	 * 获取孩子的视力信息
	 * 
	 * @param Kid
	 *            孩子的编号
	 * @param order
	 *            desc或者asc
	 */
	List<Vision> getVision(Long Kid, String order);

	/**
	 * 分页获取孩子的视力信息
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
	List<Vision> getVision(Long Kid, String order, int pageNum, int pageSize);
}
