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

@Entity
@Table(name = "Score")
public class Score implements Serializable {
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Sid;
	/**
	 * ��ʦ����
	 */
	@Expose
	private String Scomment;
	/**
	 * �������Ǽ�
	 */
	@Expose
	private Integer Sstars;
	/**
	 * С��ķ���
	 */
	@Expose
	private Integer Sscore;
	/**
	 * С�������
	 */
	@Expose
	private String Stheme;
	/**
	 * ���ֵ�ʱ��
	 */
	@Expose
	private Long Stime;
	/**
	 * ������Ӧ�Ŀγ�
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "course")
	private Course course;
	/**
	 * ������Ӧ��ѧ��
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "student")
	private Kid student;

	public Long getSid() {
		return Sid;
	}

	public void setSid(Long sid) {
		Sid = sid;
	}

	public String getScomment() {
		return Scomment;
	}

	public void setScomment(String scomment) {
		Scomment = scomment;
	}

	public Integer getSstars() {
		return Sstars;
	}

	public void setSstars(Integer sstars) {
		Sstars = sstars;
	}

	public Integer getSscore() {
		return Sscore;
	}

	public void setSscore(Integer sscore) {
		Sscore = sscore;
	}

	public String getStheme() {
		return Stheme;
	}

	public void setStheme(String stheme) {
		Stheme = stheme;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Kid getStudent() {
		return student;
	}

	public void setStudent(Kid student) {
		this.student = student;
	}

	public Long getStime() {
		return Stime;
	}

	public void setStime(Long stime) {
		Stime = stime;
	}
}
