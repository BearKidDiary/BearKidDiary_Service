package com.bearkiddiary.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Kid")
public class Kid implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kid")
	private Long Kid;

	private String Kname;

	private String Ksex;

	private Long Kbirthday;

	private String Kask;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Fid")
	private Family family;

	private String Kavatar;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kid")
	private Set<TimeLine> Ktimeline = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kid")
	private Set<Weight> weight = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kid")
	private Set<Vision> vision = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kid")
	private Set<Height> height = new HashSet<>();

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
}