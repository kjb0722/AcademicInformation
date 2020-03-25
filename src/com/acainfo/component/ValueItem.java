package com.acainfo.component;

public class ValueItem {
	private int value;
	private String text;

	public ValueItem(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return this.value;
	}

	public String getText() {
		return this.text;
	}

	@Override
	public String toString() {
		return text;
	}
}
