package com.acainfo.dto;

public class DpmDto {
	private int denum;
	private String name;
	private String del_yn;

	public DpmDto(int denum, String name, String del_yn) {
		this.denum = denum;
		this.name = name;
		this.del_yn = del_yn;
	}

	public int getDenum() {
		return denum;
	}

	public void setDenum(int denum) {
		this.denum = denum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
}
