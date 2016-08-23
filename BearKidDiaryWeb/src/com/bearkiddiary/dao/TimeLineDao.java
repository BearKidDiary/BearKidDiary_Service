package com.bearkiddiary.dao;

import java.util.List;

import com.bearkiddiary.bean.TimeLine;
import com.bearkiddiary.common.dao.BaseDao;

public interface TimeLineDao extends BaseDao<TimeLine> {
	/**
	 * 添加时间轴事件
	 * 
	 * @param timeLine
	 *            时间轴事件
	 * @param Uid
	 *            发布者的编号
	 * @param Kid
	 *            孩子的编号
	 */
	int addTimeLine(TimeLine timeLine, Long Uid, Long Kid);

	/**
	 * 添加时间轴事件
	 * 
	 * @param timeLine
	 *            时间轴事件
	 * @param Uphone
	 *            发布者的手机号码
	 * @param Kid
	 *            孩子的编号
	 */
	int addTimeLine(TimeLine timeLine, String Uphone, Long Kid);

	/**
	 * 获得时间轴事件
	 * 
	 * @param Kid
	 *            孩子的编号
	 * @param Order
	 *            排序
	 */
	List<TimeLine> getTimeLine(Long Kid, String Order);

	/**
	 * 分页获得时间轴事件
	 * 
	 * @param Kid
	 *            孩子的编号
	 * @param Order
	 *            排序
	 * @param pageSize
	 *            分页大小
	 * @param pageNum
	 *            分页页码
	 */
	List<TimeLine> getTimeLine(Long Kid, String Order, int pageSize, int pageNum);

	/**
	 * 获得发布者为指定人的时间轴事件
	 * 
	 * @param Kid
	 *            孩子的编号
	 * @param Uid
	 *            发布者的编号
	 * @param Uphone
	 *            发布者的手机号码
	 * @param Order
	 *            排序
	 */
	List<TimeLine> getTimeLine(Long Kid, Long Uid, String Uphone, String Order);

	/**
	 * 分页获得发布者为指定人的时间轴事件
	 * 
	 * @param Kid
	 *            孩子的编号
	 * @param Uid
	 *            发布者的编号
	 * @param Uphone
	 *            发布者的手机号码
	 * @param Order
	 *            排序
	 * @param pageSize
	 *            分页大小
	 * @param pageNum
	 *            分页页码
	 */
	List<TimeLine> getTimeLine(Long Kid, Long Uid, String Uphone, String Order, int pageSize, int pageNum);
}
