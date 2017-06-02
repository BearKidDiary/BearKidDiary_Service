package com.bearkiddiary.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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

/**
 * 用户表
 */
@Entity
@Table(name = "User")
public class User implements Serializable {

//	public enum Gender{
//		男,女
//	}
	
	public static final String ID = "Uid";
	public static final String NAME = "Uname";
	public static final String SEX = "Usex";
	public static final String PHONE = "Uphone";
	public static final String AREA = "Uarea";
	public static final String PSW = "Upsw";
	public static final String AVATAR = "Uavatar";
	public static final String WORKEXPERIENCE = "Uworkexperience";
	public static final String SPECIALTY = "Uspecialty";
	public static final String EDUEXPERIENCE = "Ueduexperience";
	public static final String EMAIL = "Uemail";
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Uid;

	@Expose
	private String Uname = null;

	/**
	 * 对接时再进行修改
	 */
//	@Expose
//	@Enumerated(EnumType.ORDINAL)
//	private Gender sex;
	
	@Expose
	private String Usex = null;

	@Expose
	private String Uphone = null;

	@Expose
	private String Uarea = null;

	private String Upsw;

	@Expose
	private String Uavatar = null;

	@Expose
	private String Uemail = null;

	/**
	 * 工作经历
	 */
	@Expose
	private String Uworkexperience;
	
	/**
	 * 特长
	 */
	@Expose
	private String Uspecially;
	
	/**
	 * 教育经历
	 */
	@Expose
	private String Ueducationexperience;
	/**
	 * 该用户天生所在的家庭
	 */
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "creator")
	private Family ownFamily;

	/**
	 * 该用户参与的家庭，即别人邀请自己加入的家庭
	 */
	@ManyToMany(cascade = CascadeType.MERGE, mappedBy = "members")
	private Set<Family> accessFamily = new HashSet<>();

	/**
	 * 如果该用户是管理员，批准过的课程列表
	 */
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "approver")
	private Set<Course> approverCourse = new HashSet<>();

	/**
	 * 如果该用户是教师，任教的课程
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
	private Set<Course> teachCourse = new HashSet<>();

	/**
	 * 如果是管理员，创建的机构
	 */
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "creator")
	private Organization createOrganization;
	/**
	 * 作为老师参与其中的机构
	 */
	@ManyToMany(cascade = CascadeType.MERGE, mappedBy = "teachers")
	private Set<Organization> workOrganization = new HashSet<>();
	/**
	 * 作为家长参与其中的机构
	 */
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "parents")
	private Set<Organization> parentOrganization = new HashSet<>();
	/**
	 * 请假申请
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "LAapplicant")
	private Set<Leave_Application> application = new HashSet<>();
	/**
	 * 请假审批
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "LArover")
	private Set<Leave_Application> approval = new HashSet<>();
	/**
	 * 用户在不同机构中所在的分组
	 */
	@ManyToMany(cascade = CascadeType.MERGE, mappedBy = "members")
	private Set<Group> groups = new HashSet<>();

	/**
	 * 教师考勤组
	 */
	@ManyToMany(cascade = CascadeType.MERGE, mappedBy = "teachers", fetch = FetchType.EAGER)
	private Set<AttendanceGroup> attendancegroup = new HashSet<>();
	
	/**
	 * 教师出勤记录（打卡）
	 * @return
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
	private Set<TAttendRecord> attendancerecord = new HashSet<>();
	
	/**
	 * 教师对应的KPI
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
	private Set<KPI> kpis = new HashSet<>();
	
	/**
	 * 联系人
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "contacts", 
	joinColumns=@JoinColumn(name = "uid"),
	inverseJoinColumns=@JoinColumn(name = "contact_id"))
	private Set<User> contacts = new HashSet<>();
	
	/**
	 * 上传的图片
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Pictures> pictures = new HashSet<>();
	
	public Long getUid() {
		return Uid;
	}

	public void setUid(Long uid) {
		Uid = uid;
	}

	public String getUname() {
		return Uname;
	}

	public void setUname(String uname) {
		Uname = uname;
	}

	public String getUsex() {
		return Usex;
	}

	public void setUsex(String usex) {
		Usex = usex;
	}

	public String getUphone() {
		return Uphone;
	}

	public void setUphone(String uphone) {
		Uphone = uphone;
	}

	public String getUarea() {
		return Uarea;
	}

	public void setUarea(String uarea) {
		Uarea = uarea;
	}

	public String getUpsw() {
		return Upsw;
	}

	public void setUpsw(String upsw) {
		Upsw = upsw;
	}

	public String getUavatar() {
		return Uavatar;
	}

	public void setUavatar(String uavatar) {
		Uavatar = uavatar;
	}

	public String getUemail() {
		return Uemail;
	}

	public void setUemail(String uemail) {
		Uemail = uemail;
	}

	public Family getOwnFamily() {
		return ownFamily;
	}

	public void setOwnFamily(Family ownFamily) {
		this.ownFamily = ownFamily;
	}

	public Set<Family> getAccessFamily() {
		return accessFamily;
	}

	public void setAccessFamily(Set<Family> accessFamily) {
		this.accessFamily = accessFamily;
	}

	public Set<Course> getApproverCourse() {
		return approverCourse;
	}

	public void setApproverCourse(Set<Course> approverCourse) {
		this.approverCourse = approverCourse;
	}

	public Organization getCreateOrganization() {
		return createOrganization;
	}

	public void setCreateOrganization(Organization createOrganization) {
		this.createOrganization = createOrganization;
	}

	public Set<Organization> getWorkOrganization() {
		return workOrganization;
	}

	public void setWorkOrganization(Set<Organization> workOrganization) {
		this.workOrganization = workOrganization;
	}

	public Set<Organization> getParentOrganization() {
		return parentOrganization;
	}

	public void setParentOrganization(Set<Organization> parentOrganization) {
		this.parentOrganization = parentOrganization;
	}

	public Set<Leave_Application> getApplication() {
		return application;
	}

	public void setApplication(Set<Leave_Application> application) {
		this.application = application;
	}

	public Set<Leave_Application> getApproval() {
		return approval;
	}

	public void setApproval(Set<Leave_Application> approval) {
		this.approval = approval;
	}

	public Set<Course> getTeachCourse() {
		return teachCourse;
	}

	public void setTeachCourse(Set<Course> teachCourse) {
		this.teachCourse = teachCourse;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Uid: " + Uid + " Uname: " + Uname + " Uphone: " + Uphone);
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User u = (User) obj;
			if (u.getUid() != null && Uid != null && u.getUid().equals(Uid)) {
				return true;
			}
			if (u.getUphone() != null && Uphone != null && u.getUphone().equals(Uphone)) {
				return true;
			}
		}
		return false;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<AttendanceGroup> getAttendancegroup() {
		return attendancegroup;
	}

	public void setAttendancegroup(Set<AttendanceGroup> attendancegroup) {
		this.attendancegroup = attendancegroup;
	}

	public String getUworkexperience() {
		return Uworkexperience;
	}

	public void setUworkexperience(String uworkexperience) {
		Uworkexperience = uworkexperience;
	}

	public String getUspecially() {
		return Uspecially;
	}

	public void setUspecially(String uspecially) {
		Uspecially = uspecially;
	}

	public String getUeducationexperience() {
		return Ueducationexperience;
	}

	public void setUeducationexperience(String ueducationexperience) {
		Ueducationexperience = ueducationexperience;
	}

	public Set<TAttendRecord> getAttendancerecord() {
		return attendancerecord;
	}

	public void setAttendancerecord(Set<TAttendRecord> attendancerecord) {
		this.attendancerecord = attendancerecord;
	}

	public Set<KPI> getKpis() {
		return kpis;
	}

	public void setKpis(Set<KPI> kpis) {
		this.kpis = kpis;
	}

	public Set<User> getContacts() {
		return contacts;
	}

	public void setContacts(Set<User> contacts) {
		this.contacts = contacts;
	}

	public Set<Pictures> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Pictures> pictures) {
		this.pictures = pictures;
	}

//	public Gender getSex() {
//		return sex;
//	}
//
//	public void setSex(Gender sex) {
//		this.sex = sex;
//	}
	
	
}
