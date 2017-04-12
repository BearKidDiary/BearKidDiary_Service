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

/**
 * 教师考勤记录（打卡）
 * @author Hung_Xum
 *
 */
@Entity
@Table(name = "TAttendRecord")
public class TAttendRecord implements Serializable{

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long TAid;
	
	/**
	 * 打卡时间
	 */
	@Expose
	private Long TAtime;
	
	/**
	 * 经度
	 */
	@Expose
	private Long TAlongitude;
	
	/**
	 * 纬度
	 */
	@Expose
	private Long TAlatitude;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "teacher")
	private User teacher;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "organization")
	private Organization organization;

	public Long getTAid() {
		return TAid;
	}

	public void setTAid(Long tAid) {
		TAid = tAid;
	}

	public Long getTAtime() {
		return TAtime;
	}

	public void setTAtime(Long tAtime) {
		TAtime = tAtime;
	}

	public Long getTAlongitude() {
		return TAlongitude;
	}

	public void setTAlongitude(Long tAlongitude) {
		TAlongitude = tAlongitude;
	}

	public Long getTAlatitude() {
		return TAlatitude;
	}

	public void setTAlatitude(Long tAlatitude) {
		TAlatitude = tAlatitude;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
}
