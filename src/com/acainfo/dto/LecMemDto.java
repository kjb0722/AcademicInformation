package com.acainfo.dto;

import java.util.Date;

public class LecMemDto {
	private int lmNum;
	private int leNum;
	private int num;
	private Date leDate;
	private String del_yn;

	public LecMemDto(int lmNum, int leNum, int num, Date leDate, String del_yn) {
		this.lmNum = lmNum;
		this.leNum = leNum;
		this.num = num;
		this.leDate = leDate;
		this.del_yn = del_yn;
	}

	public int getLmNum() {
		return lmNum;
	}

	public void setLmNum(int lmNum) {
		this.lmNum = lmNum;
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

	public Date getLeDate() {
		return leDate;
	}

	public void setLeDate(Date leDate) {
		this.leDate = leDate;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
}
