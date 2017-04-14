package com.bearkiddiary.bean;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "Organization")
public class Organization implements Serializable {

	// 加入机构是教师还是家长
	public final static Integer TEACHER = 0;
	public final static Integer PARENT = 1;

	// 创建，解散，修改机构
	public final static Integer CREATE = 1;
	public final static Integer DELETE = -1;
	public final static Integer UPDATE = 0;

	public final static String OID = "Oid";
	public final static String ONAME = "Oname";
	public final static String OADDRESS = "Oaddress";
	public final static String OTIME = "Otime";
	public final static String OANNOUNCE = "Oannounce";
	public final static String UID = "Uid";
	public final static String OAVATAR = "Oavatar";

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Oid;
	/**
	 * 机构的名称
	 */
	@Expose
	private String Oname;
	/**
	 * 机构的地址
	 */
	@Expose
	private String Oaddress;
	/**
	 * 机构的成立时间
	 */
	@Expose
	private Long Otime;
	/**
	 * 机构的标志图
	 */
	@Expose
	private String Oavatar;
	/**
	 * 机构的公告
	 */
	@Expose
	private String Oannounce;
	/**
	 * 机构的创建者
	 */
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "creator")
	private User creator;
	/**
	 * 机构的老师
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Organization_Teachers", joinColumns = @JoinColumn(name = "Oid"), inverseJoinColumns = @JoinColumn(name = "Uid"))
	private Set<User> teachers = new HashSet<>();

	/**
	 * 机构的家长
	 */
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "Organization_Parents", joinColumns = @JoinColumn(name = "Oid"), inverseJoinColumns = @JoinColumn(name = "Uid"))
	private Set<User> parents = new HashSet<>();
	/**
	 * 机构开设的课程
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
	private Set<Course> courses = new HashSet<>();

	/**
	 * 机构对应的请假申请
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "LAorg")
	private Set<Leave_Application> application = new HashSet<>();
	/**
	 * 机构中的分组
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "org")
	private Set<Group> groups = new HashSet<>();

	/**
	 * 该机构中的考勤组
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
	private Set<AttendanceGroup> attendancegroups = new HashSet<>();
	
	/**
	 * 打卡记录
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
	private Set<TAttendRecord> punchRecord = new HashSet<>();
	
	/**
	 * kpi记录
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
	private Set<KPI> kpis = new HashSet<>();
	
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Set<User> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<User> teachers) {
		this.teachers = teachers;
	}

	public Set<User> getParents() {
		return parents;
	}

	public void setParents(Set<User> parents) {
		this.parents = parents;
	}

	public Set<Leave_Application> getApplication() {
		return application;
	}

	public void setApplication(Set<Leave_Application> application) {
		this.application = application;
	}
	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<AttendanceGroup> getAttendancegroups() {
		return attendancegroups;
	}

	public void setAttendancegroups(Set<AttendanceGroup> attendancegroups) {
		this.attendancegroups = attendancegroups;
	}

	public Set<TAttendRecord> getPunchRecord() {
		return punchRecord;
	}

	public void setPunchRecord(Set<TAttendRecord> punchRecord) {
		this.punchRecord = punchRecord;
	}

	public Set<KPI> getKpis() {
		return kpis;
	}

	public void setKpis(Set<KPI> kpis) {
		this.kpis = kpis;
	}
	
}
