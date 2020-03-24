package com.acainfo.view;

import com.acainfo.component.KDialog;
import com.acainfo.controller.Controller;

public class DpmMemMgtView extends KDialog {
	public DpmMemMgtView(Controller controller) {
		super(controller);

		init();
	}

	private void init() {
		setTitle("[ 학과 회원 관리 ]");
		setSize(450, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
