package com.acainfo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JList;

import com.acainfo.component.KButton;
import com.acainfo.component.KDialog;
import com.acainfo.component.cboItem;
import com.acainfo.controller.Controller;
import com.acainfo.dto.DpmDto;

public class DpmMemMgtView extends KDialog {

	private JList memList;
	private JList dpmList;

	private KButton btnSave;
	private KButton btnClose;
	private KButton btnAdd;
	private KButton btnRemove;

	private JComboBox<cboItem> cboDpm;

	public DpmMemMgtView(Controller controller) {
		super(controller);

		btnInit();

		listInit();

		cboInit();

		selectDpmList();

		selectNoneDpmMember();

		init();
	}

	private void selectNoneDpmMember() {
		ArrayList<DpmDto> dpmDto = controller.selectNoneDpmMember();
	}

	private void selectDpmList() {
		ArrayList<DpmDto> dpmDto = controller.selectDpmList();
		for (DpmDto dto : dpmDto) {
			int deNum = dto.getDenum();
			String name = dto.getName();
			cboDpm.addItem(new cboItem(deNum, name));
		}
	}

	private void cboInit() {
		cboDpm = new JComboBox<cboItem>();
		cboDpm.setBounds(290, 50, 100, 20);
		add(cboDpm);
	}

	private void listInit() {
		memList = new JList();
		memList.setBounds(5, 10, 250, 380);
		add(memList);

		dpmList = new JList();
		dpmList.setBounds(390, 50, 250, 340);
		add(dpmList);
	}

	private void btnInit() {
		btnSave = new KButton("저장");
		btnSave.setLocation(430, 10);
		add(btnSave);

		btnClose = new KButton("닫기");
		btnClose.setLocation(540, 10);
		add(btnClose);

		btnAdd = new KButton("▶");
		btnAdd.setLocation(275, 150);
		add(btnAdd);

		btnRemove = new KButton("◀");
		btnRemove.setLocation(275, 250);
		add(btnRemove);

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
				} else if (target == btnAdd) {

				} else if (target == btnRemove) {

				}
			}
		};
		btnSave.addActionListener(listener);
		btnClose.addActionListener(listener);
		btnAdd.addActionListener(listener);
		btnRemove.addActionListener(listener);
	}

	private void init() {
		setTitle("[ 학과 회원 관리 ]");
		setSize(650, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
