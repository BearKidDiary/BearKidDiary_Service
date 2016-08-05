package com.bearkiddiary.bean;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Family")
public class Family implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Fid;
	/**
	 * 家庭的名字
	 */
	private String Fname;
	/**
	 * 家庭的创建者， 只有创建者可以新增孩子
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Uid")
	private User creator;
	/**
	 * 家庭的成员
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Family_User", joinColumns = @JoinColumn(name = "Fid"), inverseJoinColumns = @JoinColumn(name = "Uid"))
	private Set<User> members = new HashSet<>();
	/**
	 * 家庭的孩子
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "family")
	private Set<Kid> kid = new HashSet<>();

	public Long getFid() {
		return Fid;
	}

	public void setFid(Long fid) {
		Fid = fid;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}

	public Set<Kid> getKid() {
		return kid;
	}

	public void setKid(Set<Kid> kid) {
		this.kid = kid;
	}
}
