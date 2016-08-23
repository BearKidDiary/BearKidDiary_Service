package com.bearkiddiary.dao;

import java.util.List;
import java.util.Set;

import com.bearkiddiary.bean.Height;
import com.bearkiddiary.common.dao.BaseDao;

public interface HeightDao extends BaseDao<Height> {
	/**
	 * 为孩子添加身高数据
	 * 
	 * @param Kid
	 *            孩子编号
	 * @param Hheight
	 *            身高数据
	 * @param Htime
	 *            更新时间
	 */
	int addHeightToKid(Long Kid, Float Hheight, Long Htime);

	/**
	 * 获取孩子的身高数据
	 * 
	 * @param Kid
	 *            孩子的编号
	 * @param order
	 *            asc,即升序排列,desc为降序
	 */
	List<Height> getHeight(Long Kid, String order);

	/**
	 * 分页获取孩子的身高信息
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
	List<Height> getHeight(Long Kid, String order, int pageSize, int pageNum);
}
