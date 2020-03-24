package com.acainfo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.acainfo.component.KButton;
import com.acainfo.component.KDialog;
import com.acainfo.component.KPanel;
import com.acainfo.controller.Controller;
import com.acainfo.dto.DpmDto;

public class DpmListView extends KDialog {
	KPanel pnlMenu;
	KPanel pnlTable;

	KButton btnAdd;
	KButton btnClose;

	JTable table;
	DefaultTableModel model;
	JScrollPane scroll;

	public DpmListView(Controller controller) {
		super(controller);

		menuInit();

		tableInit();

		listenerInit();

		dpmSrh();

		init();
	}

	private void dpmSrh() {
		ArrayList<DpmDto> list = controller.selectDpmList();

		for (DpmDto dto : list) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(dto.getDenum());
			vec.add(dto.getName());
			vec.add(dto.getDel_yn());
			model.addRow(vec);
		}
	}

	private void listenerInit() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object target = e.getSource();
				if (target == btnAdd) {

				} else if (target == btnClose) {
					dispose();
				}
			}
		};

		btnAdd.addActionListener(listener);
		btnClose.addActionListener(listener);
	}

	private void tableInit() {
		table = new JTable();
		String col[] = { "학과 번호", "학과명", "사용 여부" };
		model = new DefaultTableModel(col, 0);
		table.setModel(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 50, 350, 500);
		add(scroll);
	}

	private void menuInit() {
		pnlMenu = new KPanel();
		pnlMenu.setBounds(0, 0, 550, 50);
		add(pnlMenu);

		btnAdd = new KButton("추가");
		btnAdd.setLocation(130, 10);
		pnlMenu.add(btnAdd);

		btnClose = new KButton("닫기");
		btnClose.setLocation(240, 10);
		pnlMenu.add(btnClose);
	}

	private void init() {
		setTitle("[ 회원 관리 ]");
		setSize(350, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
