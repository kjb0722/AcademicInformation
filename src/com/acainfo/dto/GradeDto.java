package com.acainfo.dto;

import java.util.Date;

public class GradeDto {
	private int leNum;
	private int num;
	private int score;
	private String rank;
	private String del_yn;
	private Date grdate;

	public GradeDto(int leNum, int num, int score, String rank, String del_yn, Date grdate) {
		this.leNum = leNum;
		this.num = num;
		this.score = score;
		this.rank = rank;
		this.del_yn = del_yn;
		this.grdate = grdate;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

	public Date getGrdate() {
		return grdate;
	}

	public void setGrdate(Date grdate) {
		this.grdate = grdate;
	}
}
