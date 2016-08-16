package com.bearkiddiary.dao;

import java.util.List;

import com.bearkiddiary.bean.TimeLine;
import com.bearkiddiary.common.dao.BaseDao;

public interface TimeLineDao extends BaseDao<TimeLine> {
	/**
	 * ���ʱ�����¼�
	 * 
	 * @param timeLine
	 *            ʱ�����¼�
	 * @param Uid
	 *            �����ߵı��
	 * @param Kid
	 *            ���ӵı��
	 */
	int addTimeLine(TimeLine timeLine, Long Uid, Long Kid);

	/**
	 * ���ʱ�����¼�
	 * 
	 * @param timeLine
	 *            ʱ�����¼�
	 * @param Uphone
	 *            �����ߵ��ֻ�����
	 * @param Kid
	 *            ���ӵı��
	 */
	int addTimeLine(TimeLine timeLine, String Uphone, Long Kid);

	/**
	 * ���ʱ�����¼�
	 * 
	 * @param Kid
	 *            ���ӵı��
	 * @param Order
	 *            ����
	 */
	List<TimeLine> getTimeLine(Long Kid, String Order);

	/**
	 * ��ҳ���ʱ�����¼�
	 * 
	 * @param Kid
	 *            ���ӵı��
	 * @param Order
	 *            ����
	 * @param pageSize
	 *            ��ҳ��С
	 * @param pageNum
	 *            ��ҳҳ��
	 */
	List<TimeLine> getTimeLine(Long Kid, String Order, int pageSize, int pageNum);

	/**
	 * ��÷�����Ϊָ���˵�ʱ�����¼�
	 * 
	 * @param Kid
	 *            ���ӵı��
	 * @param Uid
	 *            �����ߵı��
	 * @param Uphone
	 *            �����ߵ��ֻ�����
	 * @param Order
	 *            ����
	 */
	List<TimeLine> getTimeLine(Long Kid, Long Uid, String Uphone, String Order);

	/**
	 * ��ҳ��÷�����Ϊָ���˵�ʱ�����¼�
	 * 
	 * @param Kid
	 *            ���ӵı��
	 * @param Uid
	 *            �����ߵı��
	 * @param Uphone
	 *            �����ߵ��ֻ�����
	 * @param Order
	 *            ����
	 * @param pageSize
	 *            ��ҳ��С
	 * @param pageNum
	 *            ��ҳҳ��
	 */
	List<TimeLine> getTimeLine(Long Kid, Long Uid, String Uphone, String Order, int pageSize, int pageNum);
}
