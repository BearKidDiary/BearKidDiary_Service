package com.bearkiddiary.dao;

import java.util.List;

import com.bearkiddiary.bean.Pictures;

public interface PicturesDao {
	/**
	 * 保存图片
	 * @param picture
	 * @return
	 */
	int savePicture(Pictures picture);
	
	/**
	 * 获取用户的图片列表
	 * @param Uphone
	 * @return
	 */
	List<String> getPicture(String Uphone, String Order);
	
	/**
	 * 分页查询
	 * @param Uphone
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<String> getPicture(String Uphone, String Order, int pageNo, int pageSize);
}
