package com.bearkiddiary.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

@Entity
@Table(name="AttendanceGroup")
public class AttendanceGroup implements Serializable{
	public static final String AGID = "AGid";
	public static final String AGSTARTTIME = "AGstarttime";
	public static final String AGENDTIME = "AGendtime";
	public static final String AGMONDAY = "AGmonday"; 
	public static final String AGTUESDAY = "AGtuesday";
	public static final String AGWEDNESDAY = "AGwednesday";
	public static final String AGTHURSDAY = "AGthursday";
	public static final String AGFRIDAY = "AGfriday";
	public static final String AGSATURDAY = "AGsaturday";
	public static final String AGSUNDAY = "AGsunday";
	public static final String AGTEACHERS = "AGteachers";
	public static final String AGNAME = "AGname";
	
	/**
	 * 考勤组id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Long AGid;
	
	/**
	 * 考勤组名字
	 */
	@Expose
	private String AGname;
	
	/**
	 * 考勤上班时间
	 */
	@Expose
	private Long AGstarttime;
		
	/**
	 * 考勤下班时间
	 */
	@Expose
	private Long AGendtime;
	
	/**
	 * 周几打卡
	 */
	@Expose
	private Boolean AGmonday, AGtuesday, AGwednesday, AGthursday, AGfriday, AGsaturday, AGsunday;
	
	/**
	 * 打卡的教师
	 */
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "attendancegroup_teacher", 
	joinColumns = @JoinColumn(name = "AGid"),
	inverseJoinColumns = @JoinColumn(name = "Uid"))
	private Set<User> teachers = new HashSet<>();
	
	/**
	 * 属于的机构
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Oid", referencedColumnName = "Oid")
	private Organization organization;

	public Long getAGid() {
		return AGid;
	}


	public void setAGid(Long aGid) {
		AGid = aGid;
	}


	public Long getAGstarttime() {
		return AGstarttime;
	}


	public void setAGstarttime(Long aGstarttime) {
		AGstarttime = aGstarttime;
	}


	public Long getAGendtime() {
		return AGendtime;
	}


	public void setAGendtime(Long aGendtime) {
		AGendtime = aGendtime;
	}


	public Boolean getAGmonday() {
		return AGmonday;
	}


	public void setAGmonday(Boolean aGmonday) {
		AGmonday = aGmonday;
	}


	public Boolean getAGtuesday() {
		return AGtuesday;
	}


	public void setAGtuesday(Boolean aGtuesday) {
		AGtuesday = aGtuesday;
	}


	public Boolean getAGwednesday() {
		return AGwednesday;
	}


	public void setAGwednesday(Boolean aGwednesday) {
		AGwednesday = aGwednesday;
	}


	public Boolean getAGthursday() {
		return AGthursday;
	}


	public void setAGthursday(Boolean aGthursday) {
		AGthursday = aGthursday;
	}


	public Boolean getAGfriday() {
		return AGfriday;
	}


	public void setAGfriday(Boolean aGfriday) {
		AGfriday = aGfriday;
	}


	public Boolean getAGsaturday() {
		return AGsaturday;
	}


	public void setAGsaturday(Boolean aGsaturday) {
		AGsaturday = aGsaturday;
	}


	public Boolean getAGsunday() {
		return AGsunday;
	}


	public void setAGsunday(Boolean aGsunday) {
		AGsunday = aGsunday;
	}


	public Set<User> getTeachers() {
		return teachers;
	}


	public void setTeachers(Set<User> teachers) {
		this.teachers = teachers;
	}


	public Organization getOrganization() {
		return organization;
	}


	public void setOrganization(Organization organization) {
		this.organization = organization;
	}


	public static String getAgid() {
		return AGID;
	}


	public String getAGname() {
		return AGname;
	}


	public void setAGname(String aGname) {
		AGname = aGname;
	}
	
	
}
