package com.acainfo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.acainfo.component.KButton;
import com.acainfo.component.KDialog;
import com.acainfo.controller.Controller;

public class GradeInputView extends KDialog {

	KButton btnSave;
	KButton btnClose;

	public GradeInputView(Controller controller) {
		super(controller);

		btnInit();

		init();
	}

	private void btnInit() {
		btnClose = new KButton("닫기");
		btnClose.setLocation(0, 0);
		add(btnClose);

		btnListener();
	}

	private void btnListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object target = e.getSource();
				if (target == btnSave) {
					
				} else if (target == btnClose) {
					dispose();
				}
			}
		};
	}

	private void init() {
		setTitle("[ 성적 입력 ]");
		setSize(250, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
