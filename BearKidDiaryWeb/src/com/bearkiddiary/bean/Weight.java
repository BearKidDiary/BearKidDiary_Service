package com.bearkiddiary.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Weight")
public class Weight implements Serializable{
	@Id
	@Column(name = "wid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Wid;

	private Long Wtime;

	private float Wweight;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "kid")
	private Kid kid;

	public Long getWid() {
		return Wid;
	}

	public void setWid(Long wid) {
		Wid = wid;
	}

	public Long getWtime() {
		return Wtime;
	}

	public void setWtime(Long wtime) {
		Wtime = wtime;
	}

	public float getWweight() {
		return Wweight;
	}

	public void setWweight(float wweight) {
		Wweight = wweight;
	}

	public Kid getKid() {
		return kid;
	}

	public void setKid(Kid kid) {
		this.kid = kid;
	}

}
