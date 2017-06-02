package com.bearkiddiary.dao;

import java.util.List;

import com.bearkiddiary.bean.SAttendRecord;

/**
 * 学生考勤记录Dao
 * @author Hung_Xum
 *
 */
public interface SARDao {
	/**
	 * 点名
	 * @param list
	 * @return
	 */
	List<Long> named(List<SAttendRecord> list);
}
