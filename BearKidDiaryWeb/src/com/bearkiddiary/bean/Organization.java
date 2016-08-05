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

@Entity
@Table(name = "Organization")
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
	/**
	 * �����Ĵ�����
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "creator")
	private User creator;
	/**
	 * �����ĳ�Ա
	 */
	@ManyToMany(cascade = CascadeType.ALL)
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
