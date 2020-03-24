package com.acainfo.component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class KButton extends JButton {
	public KButton() {
		init();
	}

	public KButton(String text) {
		setText(text);
		init();
	}

	private void init() {
		setBackground(Color.white);
		setFont(new Font("맑은 고딕", Font.BOLD, 14));
		setSize(100, 30);
	}
}
