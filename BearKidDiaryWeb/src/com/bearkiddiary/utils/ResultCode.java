
package com.bearkiddiary.utils;

public class ResultCode {
	private ResultCode() {
	}

	public static final int SUCCESS = 0;

	/**
	 * 缺少请求参数
	 */
	public static final int ERROR_MISSING_PARAMETER = -1;
	/**
	 * 不存在该用户
	 */
	public static final int ERROR_NO_USER = -2;
	/**
	 * 已经有家庭，不能重复创建
	 */
	public static final int ERROR_ALREADY_HAVE_FAMILY = -3;
	/**
	 * 不存在该家庭
	 */
	public static final int ERROR_NO_FAMILY = -4;
	/**
	 * 不存在对应的关联关系
	 */
	public static final int ERROR_NO_RELATION = -5;
	/**
	 * 不存在该孩子
	 */
	public static final int ERROR_NO_KID = -6;
	/**
	 * 已存在该孩子
	 */
	public static final int ERROR_EXIST_KID = -7;
	/**
	 * 不能正常获得结果，过程发生了错误
	 */
	public static final int ERROR_NO_RESULT = -100;
}
