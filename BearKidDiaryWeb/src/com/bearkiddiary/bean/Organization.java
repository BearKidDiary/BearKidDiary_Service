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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "Organization")
public class Organization implements Serializable {
	
	//��������ǽ�ʦ���Ǽҳ�
	public final static Integer TEACHER = 0;
	public final static Integer PARENT = 1;
	
	//��������ɢ���޸Ļ���
	public final static Integer CREATE = 1;
	public final static Integer DELETE = -1;
	public final static Integer UPDATE = 0;
	
	public final static String OID = "Oid";
	public final static String ONAME = "Oname";
	public final static String OADDRESS = "Oaddress";
	public final static String OTIME = "Otime";
	public final static String OANNOUNCE = "Oannounce";
	public final static String UID = "Uid";
	
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Oid;
	/**
	 * ����������
	 */
	@Expose
	private String Oname;
	/**
	 * �����ĵ�ַ
	 */
	@Expose
	private String Oaddress;
	/**
	 * �����ĳ���ʱ��
	 */
	@Expose
	private Long Otime;
	/**
	 * �����ı�־ͼ
	 */
	@Expose
	private String Oavatar;
	/**
	 * �����Ĺ���
	 */
	@Expose
	private String Oannounce;
	/**
	 * �����Ĵ�����
	 */
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "creator")
	private User creator;
	/**
	 * ��������ʦ
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Organization_Teachers", joinColumns = @JoinColumn(name = "Oid"), inverseJoinColumns = @JoinColumn(name = "Uid"))
	private Set<User> teachers = new HashSet<>();
	
	/**
	 * �����ļҳ�
	 */
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "Organization_Parents", joinColumns = @JoinColumn(name = "Oid"), inverseJoinColumns = @JoinColumn(name = "Uid"))
	private Set<User> parents = new HashSet<>();
	/**
	 * ��������Ŀγ�
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
	private Set<Course> courses = new HashSet<>();

	/**
	 * ������Ӧ���������
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "LAorg")
	private Set<Leave_Application> application = new HashSet<>();
	
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
	
}
