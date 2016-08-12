
package com.bearkiddiary.utils;

public class ResultCode {
	private ResultCode() {
	}

	public static final int SUCCESS = 0;

	/**
	 * ȱ���������
	 */
	public static final int ERROR_MISSING_PARAMETER = -1;
	/**
	 * �����ڸ��û�
	 */
	public static final int ERROR_NO_USER = -2;
	/**
	 * �Ѿ��м�ͥ�������ظ�����
	 */
	public static final int ERROR_ALREADY_HAVE_FAMILY = -3;
	/**
	 * �����ڸü�ͥ
	 */
	public static final int ERROR_NO_FAMILY = -4;
	/**
	 * �����ڶ�Ӧ�Ĺ�����ϵ
	 */
	public static final int ERROR_NO_RELATION = -5;
}
