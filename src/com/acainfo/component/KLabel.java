package com.acainfo.component;

import java.awt.Font;

import javax.swing.JLabel;

public class KLabel extends JLabel {
	public KLabel() {
		init();
	}

	public KLabel(String name) {
		setText(name);
		init();
	}

	private void init() {
		setSize(150, 25);
		setFont(new Font("맑은 고딕", Font.BOLD, 14));
	}
}
