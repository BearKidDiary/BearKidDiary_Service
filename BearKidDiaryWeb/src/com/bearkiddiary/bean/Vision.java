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
@Table(name = "Vision")
public class Vision implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Vid;

	private Float Vleft;

	private Float Vright;

	private Long Vtime;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Kid")
	private Kid kid;

	public Long getVid() {
		return Vid;
	}

	public void setVid(Long vid) {
		Vid = vid;
	}

	public Float getVleft() {
		return Vleft;
	}

	public void setVleft(Float vleft) {
		Vleft = vleft;
	}

	public Float getVright() {
		return Vright;
	}

	public void setVright(Float vright) {
		Vright = vright;
	}

	public Kid getKid() {
		return kid;
	}

	public void setKid(Kid kid) {
		this.kid = kid;
	}

	public Long getVtime() {
		return Vtime;
	}

	public void setVtime(Long vtime) {
		Vtime = vtime;
	}
}
