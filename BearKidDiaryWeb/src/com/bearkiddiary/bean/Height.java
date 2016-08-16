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
@Table(name = "Height")
public class Height implements Serializable {
	@Id
	@Column(name = "hid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Hid;
	
	private Long Htime;
	private Float Hheight;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "kid")
	private Kid kid;

	public Long getHid() {
		return Hid;
	}

	public void setHid(Long hid) {
		Hid = hid;
	}

	public Long getHtime() {
		return Htime;
	}

	public void setHtime(Long htime) {
		Htime = htime;
	}

	public Float getHheight() {
		return Hheight;
	}

	public void setHheight(Float hheight) {
		Hheight = hheight;
	}

	public Kid getKid() {
		return kid;
	}

	public void setKid(Kid kid) {
		this.kid = kid;
	}
}
