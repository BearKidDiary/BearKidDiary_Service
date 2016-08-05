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
	 * 开课时间
	 */
	private Long Cclasstime;
	/**
	 * 结束时间
	 */
	private Long Cendtime;
	/**
	 * 开学时间
	 */
	private Long Ctime;
	/**
	 * 毕业时间
	 */
	private Long Cofftime;
	/**
	 * 开课审批人
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "approver")
	private User approver;
	/**
	 * 课程的开课背景、原因
	 */
	private String Cbackground;
	/**
	 * 课程描述
	 */
	private String Cdesc;
	/**
	 * 课程名称
	 */
	private String Cname;
	/**
	 * 周一到周日是否需要上课
	 */
	private Boolean Cmonday, Ctuesday, Cwednesday, Cthursday, Cfriday, Csaturday;
	/**
	 * 课程所属的机构
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "organization")
	private Organization organization;
	/**
	 * 参与课程的孩子
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
