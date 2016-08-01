package com.bearkiddiary.bean;

/**
 * 返回给客户端的数据格式
 * @author admin
 * 
 * @param <T>
 */
public class Result<T> {
	
	private int resultCode;
	private String resultMessage;
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
