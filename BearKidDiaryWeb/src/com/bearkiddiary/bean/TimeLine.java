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
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "TimeLine")
public class TimeLine implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Long Tid;

	@Expose
	private Long Treleasetime;

	@Expose
	private String Treleasecontent;

	@Expose
	private String Timage1, Timage2, Timage3;

	@Expose
	private String Ttype;

	@Expose
	private Integer Ttypelogo;
	
	@Expose
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Uid")
	private User author;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Kid")
	private Kid kid;

	public Long getTid() {
		return Tid;
	}

	public void setTid(Long tid) {
		Tid = tid;
	}

	public Long getTreleasetime() {
		return Treleasetime;
	}

	public void setTreleasetime(Long treleasetime) {
		Treleasetime = treleasetime;
	}

	public String getTreleasecontent() {
		return Treleasecontent;
	}

	public void setTreleasecontent(String treleasecontent) {
		Treleasecontent = treleasecontent;
	}

	public String getTimage1() {
		return Timage1;
	}

	public void setTimage1(String timage1) {
		Timage1 = timage1;
	}

	public String getTimage2() {
		return Timage2;
	}

	public void setTimage2(String timage2) {
		Timage2 = timage2;
	}

	public String getTimage3() {
		return Timage3;
	}

	public void setTimage3(String timage3) {
		Timage3 = timage3;
	}

	public String getTtype() {
		return Ttype;
	}

	public void setTtype(String ttype) {
		Ttype = ttype;
	}

	public Integer getTtypelogo() {
		return Ttypelogo;
	}

	public void setTtypelogo(Integer ttypelogo) {
		Ttypelogo = ttypelogo;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Kid getKid() {
		return kid;
	}

	public void setKid(Kid kid) {
		this.kid = kid;
	}
}