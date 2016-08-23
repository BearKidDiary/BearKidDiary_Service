package com.bearkiddiary.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "Kid")
public class Kid implements Serializable {
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Kid;
	/**
	 * ��������
	 */
	@Expose
	private String Kname;
	/**
	 * �����Ա�
	 */
	@Expose
	private String Ksex;
	/**
	 * ���ӳ���ʱ��
	 */
	@Expose
	private Long Kbirthday;
	/**
	 * �ҳ�����
	 */
	@Expose
	private String Kask;
	/**
	 * ���ӻ�õ�С�컨��Ŀ
	 */
	@Expose
	private Integer Kflowers;
	/**
	 * ���ڵļ�ͥ
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Fid")
	private Family family;
	/**
	 * ͷ��URL
	 */
	@Expose
	private String Kavatar;
	/**
	 * ���ӵ�ʱ����
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kid")
	private Set<TimeLine> Ktimeline = new HashSet<>();
	/**
	 * ���ӵ�����
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kid")
	private Set<Weight> weight = new HashSet<>();
	/**
	 * ���ӵ�����
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kid")
	private Set<Vision> vision = new HashSet<>();
	/**
	 * ���ӵ����
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kid")
	private Set<Height> height = new HashSet<>();
	/**
	 * ���Ӳ���Ŀγ�
	 */
	@ManyToMany(cascade = CascadeType.MERGE, mappedBy = "students")
	private Set<Course> attendCourse = new HashSet<>();

	/**
	 * �����ڲ�ͬ�γ��л�õĳɼ�
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
	private Set<Score> score = new HashSet<>();

	public Long getKid() {
		return Kid;
	}

	public void setKid(Long kid) {
		Kid = kid;
	}

	public String getKname() {
		return Kname;
	}

	public void setKname(String kname) {
		Kname = kname;
	}

	public String getKsex() {
		return Ksex;
	}

	public void setKsex(String ksex) {
		Ksex = ksex;
	}

	public Long getKbirthday() {
		return Kbirthday;
	}

	public void setKbirthday(Long kbirthday) {
		Kbirthday = kbirthday;
	}

	public String getKask() {
		return Kask;
	}

	public void setKask(String kask) {
		Kask = kask;
	}

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public String getKavatar() {
		return Kavatar;
	}

	public void setKavatar(String kavatar) {
		Kavatar = kavatar;
	}

	public Set<TimeLine> getKtimeline() {
		return Ktimeline;
	}

	public void setKtimeline(Set<TimeLine> ktimeline) {
		Ktimeline = ktimeline;
	}

	public Set<Weight> getWeight() {
		return weight;
	}

	public void setWeight(Set<Weight> weight) {
		this.weight = weight;
	}

	public Set<Vision> getVision() {
		return vision;
	}

	public void setVision(Set<Vision> vision) {
		this.vision = vision;
	}

	public Set<Height> getHeight() {
		return height;
	}

	public void setHeight(Set<Height> height) {
		this.height = height;
	}

	public Set<Course> getAttendCourse() {
		return attendCourse;
	}

	public void setAttendCourse(Set<Course> attendCourse) {
		this.attendCourse = attendCourse;
	}

	public Integer getKflowers() {
		return Kflowers;
	}

	public void setKflowers(Integer kflowers) {
		Kflowers = kflowers;
	}

	public Set<Score> getScore() {
		return score;
	}

	public void setScore(Set<Score> score) {
		this.score = score;
	}
}