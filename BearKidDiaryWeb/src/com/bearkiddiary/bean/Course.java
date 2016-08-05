package com.bearkiddiary.bean;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Cid;
	/**
	 * ����ʱ��
	 */
	private Long Cclasstime;
	/**
	 * ����ʱ��
	 */
	private Long Cendtime;
	/**
	 * ��ѧʱ��
	 */
	private Long Ctime;
	/**
	 * ��ҵʱ��
	 */
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
	private String Cbackground;
	/**
	 * �γ�����
	 */
	private String Cdesc;
	/**
	 * �γ�����
	 */
	private String Cname;
	/**
	 * ��һ�������Ƿ���Ҫ�Ͽ�
	 */
	private Boolean Cmonday, Ctuesday, Cwednesday, Cthursday, Cfriday, Csaturday;
	/**
	 * �γ������Ļ���
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "organization")
	private Organization organization;
	/**
	 * ����γ̵ĺ���
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Course_Student", joinColumns = @JoinColumn(name = "Cid"), inverseJoinColumns = @JoinColumn(name = "Kid"))
	private Set<Kid> students = new HashSet<>();

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
}
