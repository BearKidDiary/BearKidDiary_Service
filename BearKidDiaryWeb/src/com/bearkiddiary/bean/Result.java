package com.bearkiddiary.bean;

import com.google.gson.annotations.Expose;

/**
 * ���ظ��ͻ��˵����ݸ�ʽ
 * @author admin
 * 
 * @param <T>
 */
public class Result<T> {
	@Expose
	private int resultCode;
	@Expose
	private String resultMessage;
	@Expose
	private T data;
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
