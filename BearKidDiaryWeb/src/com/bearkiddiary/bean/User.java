package com.bearkiddiary.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 用户表
 */
@Entity
@Table(name = "User")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Uid;

	private String Uname = null;

	private String Usex = null;

	private String Uphone = null;

	private String Uarea = null;

	private String Upsw;

	private String Uavatar;

	private String Uemail = null;

	/**
	 * 该用户天生所在的家庭
	 */
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "creator")
	private Family ownFamily;

	/**
	 * 该用户参与的家庭，即别人邀请自己加入的家庭
	 */
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "members")
	private Set<Family> accessFamily = new HashSet<>();

	/**
	 * 如果该用户是管理员，批准过的课程列表
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "approver")
	private Set<Course> approverCourse = new HashSet<>();

	/**
	 * 如果是管理员，创建的机构
	 */
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "creator")
	private Organization createOrganization;

	/**
	 * 作为成员参与其中的机构
	 */
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "members")
	private Set<Organization> attendOrganization = new HashSet<>();

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

	public Set<Organization> getAttendOrganization() {
		return attendOrganization;
	}

	public void setAttendOrganization(Set<Organization> attendOrganization) {
		this.attendOrganization = attendOrganization;
	}
}
