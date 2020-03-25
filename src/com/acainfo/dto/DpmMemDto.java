package com.acainfo.dto;

public class DpmMemDto {
	private int deNum;
	private int num;

	public DpmMemDto(int deNum, int num) {
		this.deNum = deNum;
		this.num = num;
	}

	public int getDenum() {
		return deNum;
	}

	public void setDenum(int denum) {
		this.deNum = denum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
