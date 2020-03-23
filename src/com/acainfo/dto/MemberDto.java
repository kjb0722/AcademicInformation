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

	public Date getUsdate() {
		return usdate;
	}

	public void setUsdate(Date usdate) {
		this.usdate = usdate;
	}

}
