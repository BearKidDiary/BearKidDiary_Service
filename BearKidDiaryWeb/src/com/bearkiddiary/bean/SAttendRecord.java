package com.bearkiddiary.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * 学生出勤记录
 * @author Hung_Xum
 *
 */
@Entity
@Table(name = "SAttendRecord")
public class SAttendRecord implements Serializable{

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long STid;
	
	/**
	 * 出勤时间
	 */
	@Expose
	private Long STtime;
	
	/**
	 * 是否出勤
	 */
	@Expose
	private Boolean isAttend;
	
	/**
	 * 出勤的学生
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "student")
	private Kid student;
	
	/**
	 * 对应的课程
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "course")
	private Course course;

	public Long getSTid() {
		return STid;
	}

	public void setSTid(Long sTid) {
		STid = sTid;
	}

	public Long getSTtime() {
		return STtime;
	}

	public void setSTtime(Long sTtime) {
		STtime = sTtime;
	}

	public Boolean getIsAttend() {
		return isAttend;
	}

	public void setIsAttend(Boolean isAttend) {
		this.isAttend = isAttend;
	}

	public Kid getStudent() {
		return student;
	}

	public void setStudent(Kid student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
