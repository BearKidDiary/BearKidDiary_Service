package com.bearkiddiary.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "Family")
public class Family implements Serializable {

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Fid;
	/**
	 * 家庭的名字
	 */
	@Expose
	private String Fname;
	/**
	 * 家庭的创建者， 只有创建者可以新增孩子
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Uid")
	private User creator;
	/**
	 * 家庭的成员
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Family_User", joinColumns = @JoinColumn(name = "Fid"), inverseJoinColumns = @JoinColumn(name = "Uid"))
	private Set<User> members = new HashSet<>();
	/**
	 * 家庭的孩子
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "family", fetch = FetchType.EAGER)
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Family) {
			Family other = (Family) obj;
			return other.Fid != null && other.Fid.equals(Fid);
		}
		return false;
	}

	@Override
	public int hashCode() {
		if (Fid != null) {
			return Fid.hashCode();
		}
		return super.hashCode();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Fid: " + Fid + " Fname: " + Fname);
		if (creator != null) {
			sb.append("  creator: " + creator);
		}
		if (members != null) {
			sb.append(" members [ ");
			for (User user : members) {
				sb.append(user + " ");
			}
			sb.append("]");
		}
		if (kid != null) {
			sb.append(" kids [ ");
			for (Kid k : kid) {
				sb.append(k + " ");
			}
			sb.append("]");
		}
		return sb.toString();
	}
}
