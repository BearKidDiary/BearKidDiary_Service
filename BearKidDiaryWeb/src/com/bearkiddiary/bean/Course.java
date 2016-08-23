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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "Course")
public class Course implements Serializable {
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Cid;
	/**
	 * �Ͽ�ʱ��
	 */
	@Expose
	private Long Cclasstime;
	/**
	 * �¿�ʱ��
	 */
	@Expose
	private Long Cendtime;
	/**
	 * ��ѧʱ��
	 */
	@Expose
	private Long Ctime;
	/**
	 * ��ҵʱ��
	 */
	@Expose
	private Long Cofftime;
	/**
	 * ����������
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "approver")
	private User approver;
	/**
	 * �γ̵Ŀ��α�����ԭ��
	 */
	@Expose
	private String Cbackground;
	/**
	 * �γ�����
	 */
	@Expose
	private String Cdesc;
	/**
	 * �γ�����
	 */
	@Expose
	private String Cname;
	/**
	 * ��һ�������Ƿ���Ҫ�Ͽ�
	 */
	@Expose
	private Boolean Cmonday, Ctuesday, Cwednesday, Cthursday, Cfriday, Csaturday, Csunday;
	/**
	 * �γ̵ļ��ͼƬURL
	 */
	@Expose
	private String Cimage;
	/**
	 * �γ������Ļ���
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "organization")
	private Organization organization;
	/**
	 * �γ̵��ο���ʦ
	 */
	@Expose
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "teacher")
	private User teacher;
	/**
	 * ����γ̵ĺ���
	 */
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "Course_Student", joinColumns = @JoinColumn(name = "Cid"), inverseJoinColumns = @JoinColumn(name = "Kid"))
	private Set<Kid> students = new HashSet<>();
	/**
	 * �γ��и���ѧ����õĳɼ�
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
	private Set<Score> score = new HashSet<>();

	public Long getCclasstime() {
		return Cclasstime;
	}

	public void setCclasstime(Long cclasstime) {
		Cclasstime = cclasstime;
	}

	public Long getCendtime() {
		return Cendtime;
	}

	public void setCendtime(Long cendtime) {
		Cendtime = cendtime;
	}

	public Long getCtime() {
		return Ctime;
	}

	public void setCtime(Long ctime) {
		Ctime = ctime;
	}

	public Long getCofftime() {
		return Cofftime;
	}

	public void setCofftime(Long cofftime) {
		Cofftime = cofftime;
	}

	public String getCbackground() {
		return Cbackground;
	}

	public void setCbackground(String cbackground) {
		Cbackground = cbackground;
	}

	public String getCdesc() {
		return Cdesc;
	}

	public void setCdesc(String cdesc) {
		Cdesc = cdesc;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public Boolean getCmonday() {
		return Cmonday;
	}

	public void setCmonday(Boolean cmonday) {
		Cmonday = cmonday;
	}

	public Boolean getCtuesday() {
		return Ctuesday;
	}

	public void setCtuesday(Boolean ctuesday) {
		Ctuesday = ctuesday;
	}

	public Boolean getCwednesday() {
		return Cwednesday;
	}

	public void setCwednesday(Boolean cwednesday) {
		Cwednesday = cwednesday;
	}

	public Boolean getCthursday() {
		return Cthursday;
	}

	public void setCthursday(Boolean cthursday) {
		Cthursday = cthursday;
	}

	public Boolean getCfriday() {
		return Cfriday;
	}

	public void setCfriday(Boolean cfriday) {
		Cfriday = cfriday;
	}

	public Boolean getCsaturday() {
		return Csaturday;
	}

	public void setCsaturday(Boolean csaturday) {
		Csaturday = csaturday;
	}

	public Long getCid() {
		return Cid;
	}

	public void setCid(Long cid) {
		Cid = cid;
	}

	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Set<Kid> getStudents() {
		return students;
	}

	public void setStudents(Set<Kid> students) {
		this.students = students;
	}

	public String getCimage() {
		return Cimage;
	}

	public void setCimage(String cimage) {
		Cimage = cimage;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public Boolean getCsunday() {
		return Csunday;
	}

	public void setCsunday(Boolean csunday) {
		Csunday = csunday;
	}
}
