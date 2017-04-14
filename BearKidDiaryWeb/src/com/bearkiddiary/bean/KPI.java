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
@Table(name = "KPI")
public class KPI implements Serializable{
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long KPIid;
	
	@Expose
	private Long KPItime;
	
	@Expose
	private Long KPIscore;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "organization")
	private Organization organization;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "teacher")
	private User teacher;

	public Long getKPIid() {
		return KPIid;
	}

	public void setKPIid(Long kPIid) {
		KPIid = kPIid;
	}

	public Long getKPItime() {
		return KPItime;
	}

	public void setKPItime(Long kPItime) {
		KPItime = kPItime;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public Long getKPIscore() {
		return KPIscore;
	}

	public void setKPIscore(Long kPIscore) {
		KPIscore = kPIscore;
	}
	
	
}
