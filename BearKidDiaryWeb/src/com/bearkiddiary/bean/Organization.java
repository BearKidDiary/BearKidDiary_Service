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
	//��������ɢ���޸Ļ���
	public final static Integer CREATE = 1;
	public final static Integer DELETE = -1;
	public final static Integer UPDATE = 0;
	
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
	 * �����ĳ�Ա
	 */
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "Organization_User", joinColumns = @JoinColumn(name = "Oid"), inverseJoinColumns = @JoinColumn(name = "Uid"))
	private Set<User> members = new HashSet<>();
	/**
	 * ��������Ŀγ�
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
	private Set<Course> courses = new HashSet<>();

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

	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}
}
