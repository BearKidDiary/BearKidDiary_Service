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

@Entity
@Table(name = "Height")
public class Height implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Hid;

	private Float Hheight;

	private Long Htime;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Kid")
	private Kid kid;

	public Long getHid() {
		return Hid;
	}

	public void setHid(Long hid) {
		Hid = hid;
	}

	public Float getHheight() {
		return Hheight;
	}

	public void setHheight(Float hheight) {
		Hheight = hheight;
	}

	public Long getHtime() {
		return Htime;
	}

	public void setHtime(Long htime) {
		Htime = htime;
	}

	public Kid getKid() {
		return kid;
	}

	public void setKid(Kid kid) {
		this.kid = kid;
	}
}
