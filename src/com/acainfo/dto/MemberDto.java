package com.acainfo.dto;

import java.util.Date;

public class MemberDto {
	private int num;
	private String id;
	private String name;
	private String email;
	private String phone;
	private String addr;
	private int hagnyeno;
	private String del_yn;
	private int auth;
	private Date usdate;

	public MemberDto(int num, String id, String name, String email, String phone, String addr, int hagnyeno,
			String del_yn, int auth, Date usdate) {
		this.num = num;
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
		this.hagnyeno = hagnyeno;
		this.del_yn = del_yn;
		this.auth = auth;
		this.usdate = usdate;
	}
	public MemberDto(int num, String name) {
		this.num = num;
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
		this.hagnyeno = hagnyeno;
		this.del_yn = del_yn;
		this.auth = auth;
		this.usdate = usdate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getHagnyeno() {
		return hagnyeno;
	}

	public void setHagnyeno(int hagnyeno) {
		this.hagnyeno = hagnyeno;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public Date getMedate() {
		return usdate;
	}

	public void setMedate(Date usdate) {
		this.usdate = usdate;
	}
}
