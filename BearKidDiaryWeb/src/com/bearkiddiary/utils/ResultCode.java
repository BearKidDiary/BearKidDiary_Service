
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
	/**
	 * �����ڸú���
	 */
	public static final int ERROR_NO_KID = -6;
	/**
	 * �Ѵ��ڸú���
	 */
	public static final int ERROR_EXIST_KID = -7;
	/**
	 * ����������ý�������̷����˴���
	 */
	public static final int ERROR_NO_RESULT = -100;
}
