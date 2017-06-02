package com.bearkiddiary.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "picture")
public class Pictures implements Serializable{
	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Pid;
	
	/**
	 * 图片名字
	 */
	@Expose
	private String Pimage;
	
	/**
	 * 上传图片的时间
	 */
	@Expose
	private Long Ptime;
	
	/**
	 * 用户
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user")
	private User user;

	public Long getPid() {
		return Pid;
	}

	public void setPid(Long pid) {
		Pid = pid;
	}

	public String getPimage() {
		return Pimage;
	}

	public void setPimage(String pimage) {
		Pimage = pimage;
	}

	public Long getPtime() {
		return Ptime;
	}

	public void setPtime(Long ptime) {
		Ptime = ptime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
