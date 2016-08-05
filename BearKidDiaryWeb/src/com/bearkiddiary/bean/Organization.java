package com.bearkiddiary.bean;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Organization implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Oid;
	/**
	 * ����������
	 */
	private String Oname;
	/**
	 * �����ĵ�ַ
	 */
	private String Oaddress;
	/**
	 * �����ĳ���ʱ��
	 */
	private Long Otime;
	/**
	 * �����ı�־ͼ
	 */
	private String Oavatar;
	/**
	 * �����Ĺ���
	 */
	private String Oannounce;

	public Long getOid() {
		return Oid;
	}

	public void setOid(Long oid) {
		Oid = oid;
	}

	public String getOname() {
		return Oname;
	}

	public void setOname(String oname) {
		Oname = oname;
	}

	public String getOaddress() {
		return Oaddress;
	}

	public void setOaddress(String oaddress) {
		Oaddress = oaddress;
	}

	public Long getOtime() {
		return Otime;
	}

	public void setOtime(Long otime) {
		Otime = otime;
	}

	public String getOavatar() {
		return Oavatar;
	}

	public void setOavatar(String oavatar) {
		Oavatar = oavatar;
	}

	public String getOannounce() {
		return Oannounce;
	}

	public void setOannounce(String oannounce) {
		Oannounce = oannounce;
	}
}
