package com.acainfo.dto;

public class LectureDto {
	private int leNum;
	private int num;
	private String name;
	private String del_yn;
	private String lec_yn; // 강의 신청 여부
	private String lec_mem; // 강의 교수

	public String getLec_mem() {
		return lec_mem;
	}

	public void setLec_mem(String lec_mem) {
		this.lec_mem = lec_mem;
	}

	public String getLec_yn() {
		return lec_yn;
	}

	public void setLec_yn(String lec_yn) {
		this.lec_yn = lec_yn;
	}

	public LectureDto(int leNum, int num, String name, String del_yn) {
		this.leNum = leNum;
		this.num = num;
		this.name = name;
		this.del_yn = del_yn;
	}

	public int getLeNum() {
		return leNum;
	}

	public void setLeNum(int leNum) {
		this.leNum = leNum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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
