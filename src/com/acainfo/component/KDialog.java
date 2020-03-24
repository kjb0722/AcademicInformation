package com.acainfo.component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JDialog;

import com.acainfo.controller.Controller;

public class KDialog extends JDialog {
	protected Controller controller;

	public KDialog(Controller controller) {
		this.controller = controller;

		init();
	}

	private void init() {
		setSize(500, 500);
		setLayout(null);
		setResizable(false);
		setUndecorated(true);
		setModal(true);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLUE));
	}
}
