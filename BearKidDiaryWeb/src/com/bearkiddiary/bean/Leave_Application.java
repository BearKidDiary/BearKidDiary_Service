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
@Table(name = "Leave_Application")
public class Leave_Application implements Serializable{

	public static final Integer APPROVE_YES = 1;//ͬ��
	public static final Integer APPROVE_NO = 0;//��ͬ��
	
	public static final Integer APPROVED = 1;//������
	public static final Integer UNAPPROVED = 0;//δ����
	
	public static final Integer SICK_LEAVE = 1;//����
	public static final Integer THING_LEAVE = 2;//�¼�
	
	public static final String LAID = "LAid"; 
	public static final String LAISAPPROVED = "LAisapproved";
	public static final String LASTATUS = "LAstatus";
	public static final String LASTARTTIME = "LAstarttime";
	public static final String LAENDTIME = "LAendtime";
	public static final String LATYPE = "LAtype";
	public static final String LAREASON = "LAreason";
	public static final String LACOMMENT = "LAcomment";
	
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long LAid;
	/**
	 * �Ƿ�ͬ��
	 */
	@Expose
	private Integer LAisapproved;
	/**
	 * ����״̬
	 */
	@Expose
	private Integer LAstatus;
	
	/**
	 * ��ٿ�ʼʱ��
	 */
	@Expose
	private Long LAstarttime;
	
	/**
	 * ��ٽ���ʱ��
	 */
	@Expose
	private Long LAendtime;
	
	/**
	 * �������
	 */
	@Expose
	private Integer LAtype;
	
	/**
	 * ���ԭ��
	 */
	@Expose
	private String LAreason;
	
	/**
	 * ����������
	 */
	@Expose
	private String LAcomment;
	
	/**
	 * ������
	 */
	@Expose
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LArover")
	private User LArover;
	
	/**
	 * ������
	 */
	@Expose
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LAapplication")
	private User LAapplicant;
	
	/**
	 * ��Ӧ�Ļ���
	 */
	@Expose
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LAorg")
	private Organization LAorg;

	public Long getLAid() {
		return LAid;
	}

	public void setLAid(Long lAid) {
		LAid = lAid;
	}

	public Integer getLAisapproved() {
		return LAisapproved;
	}

	public void setLAisapproved(Integer lAisapproved) {
		LAisapproved = lAisapproved;
	}

	public Integer getLAstatus() {
		return LAstatus;
	}

	public void setLAstatus(Integer lAstatus) {
		LAstatus = lAstatus;
	}

	public Long getLAstarttime() {
		return LAstarttime;
	}

	public void setLAstarttime(Long lAstarttime) {
		LAstarttime = lAstarttime;
	}

	public Long getLAendtime() {
		return LAendtime;
	}

	public void setLAendtime(Long lAendtime) {
		LAendtime = lAendtime;
	}

	public Integer getLAtype() {
		return LAtype;
	}

	public void setLAtype(Integer lAtype) {
		LAtype = lAtype;
	}

	public String getLAreason() {
		return LAreason;
	}

	public void setLAreason(String lAreason) {
		LAreason = lAreason;
	}

	public String getLAcomment() {
		return LAcomment;
	}

	public void setLAcomment(String lAcomment) {
		LAcomment = lAcomment;
	}

	public User getLArover() {
		return LArover;
	}

	public void setLArover(User lArover) {
		LArover = lArover;
	}

	public User getLAapplicant() {
		return LAapplicant;
	}

	public void setLAapplicant(User lAapplicant) {
		LAapplicant = lAapplicant;
	}

	public Organization getLAorg() {
		return LAorg;
	}

	public void setLAorg(Organization lAorg) {
		LAorg = lAorg;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{" + "LAid:" + LAid + "," + "LAstatus:" + LAstatus + "," + "LAstarttime:" + LAstarttime + "," + "LAendtime:" + LAendtime + ","
				+ "LAtype:" + LAtype + "," + "LAreason:" + LAreason + "," + "LAcomment:" + LAcomment + "}";
	}
}