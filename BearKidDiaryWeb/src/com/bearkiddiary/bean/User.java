package com.bearkiddiary.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 用户表
 */
@Entity
@Table(name="User")
public class User implements Serializable {
    public static final String NAME = "Uname";
    public static final String SEX = "Usex";
    public static final String PHONE = "Uphone";
    public static final String AREA = "Uarea";
    public static final String PSW = "Upsw";
    public static final String AVATAR = "Uavatar";
    public static final String WORKEXPERIENCE = "Uworkexperience";
    public static final String SPECIALTY = "Uspecialty";
    public static final String EDUEXPERIENCE = "Ueduexperience";
    public static final String EMAIL = "Uemail";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Uid;
    private String Uname = null;
    private String Usex = null;
    private String Uphone;
    private String Uarea = null;
    private String Upsw;
    //private String Uavatar;
    private String Uemail = null;

    public String getUname() {
        return Uname;
    }

    public void setUname(String Uname) {
        this.Uname = Uname;
    }

    public String getUsex() {
        return Usex;
    }

    public void setUsex(String Usex) {
        this.Usex = Usex;
    }

    public String getUphone() {
        return Uphone;
    }

    public void setUphone(String Uphone) {
        this.Uphone = Uphone;
    }

    public String getUarea() {
        return Uarea;
    }

    public void setUarea(String Uarea) {
        this.Uarea = Uarea;
    }

    public String getUemail() {
        return Uemail;
    }

    public void setUemail(String Uemail) {
        this.Uemail = Uemail;
    }

    public String getUpsw() {
        return Upsw;
    }

    public void setUpsw(String Upsw) {
        this.Upsw = Upsw;
    }
}
