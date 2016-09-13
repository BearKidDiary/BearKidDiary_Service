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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "Groups")
public class Group implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Long Gid;
	/**
	 * 分组标签名称
	 */
	@Expose
	private String Gname;
	/**
	 * 所属机构
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "org")
	private Organization org;
	/**
	 * 分组成员列表
	 */
	@Expose
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "Group_User", joinColumns = @JoinColumn(name = "groups"), inverseJoinColumns = @JoinColumn(name = "member"))
	private Set<User> members = new HashSet<>();

	public Long getGid() {
		return Gid;
	}

	public void setGid(Long gid) {
		Gid = gid;
	}

	public String getGname() {
		return Gname;
	}

	public void setGname(String gname) {
		Gname = gname;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}
}
