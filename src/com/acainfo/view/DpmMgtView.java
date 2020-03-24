package com.acainfo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.acainfo.component.KButton;
import com.acainfo.component.KDialog;
import com.acainfo.component.KPanel;
import com.acainfo.component.KTable;
import com.acainfo.controller.Controller;
import com.acainfo.dto.DpmDto;

public class DpmMgtView extends KDialog {
	KPanel pnlMenu;
	KPanel pnlTable;

	KButton btnAdd;
	KButton btnDelete;
	KButton btnClose;

	JTextField txtDpmName;

	KTable table;
	DefaultTableModel model;
	JScrollPane scroll;

	public DpmMgtView(Controller controller) {
		super(controller);

		menuInit();

		tableInit();

		listenerInit();

		dpmSrh();

		init();
	}

	private void dpmSrh() {
		model.setNumRows(0);

		ArrayList<DpmDto> list = controller.selectDpmList();

		for (DpmDto dto : list) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(dto.getDenum());
			vec.add(dto.getName());
			vec.add(dto.getDel_yn());
			model.addRow(vec);
		}
		table.setRowSelectionInterval(0, 0);
	}

	private void listenerInit() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object target = e.getSource();
				if (target == btnAdd) {
					insertDpm();
				} else if (target == btnDelete) {
					deleteDpm();
				} else if (target == btnClose) {
					dispose();
				}
			}

		};

		btnAdd.addActionListener(listener);
		btnDelete.addActionListener(listener);
		btnClose.addActionListener(listener);
	}

	private void deleteDpm() {
		int rowIndex = table.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "삭제하실 학과를 선택하세요.");
			return;
		}
		int deNum = Integer.parseInt(model.getValueAt(rowIndex, 0).toString());
		if (controller.deleteDpm(deNum)) {
			JOptionPane.showMessageDialog(this, "[ 학과 삭제 성공 ]");
		}
		
		dpmSrh();
	}

	private void insertDpm() {
		if (txtDpmName.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "학과명을 입력하세요.");
			return;
		}
		if (controller.insertDpm(txtDpmName.getText())) {
			JOptionPane.showMessageDialog(this, "[ 학과 추가 성공 ]");
			txtDpmName.setText("");
			txtDpmName.requestFocus();
			dpmSrh();
		}

	}

	private void tableInit() {
		table = new KTable();
		String col[] = { "학과 번호", "학과명", "사용 여부" };
		model = new DefaultTableModel(col, 0);
		table.setModel(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 50, 450, 500);
		add(scroll);
	}

	private void menuInit() {
		pnlMenu = new KPanel();
		pnlMenu.setBounds(0, 0, 550, 50);
		add(pnlMenu);

		txtDpmName = new JTextField();
		txtDpmName.setBounds(10, 15, 110, 25);
		pnlMenu.add(txtDpmName);

		btnAdd = new KButton("추가");
		btnAdd.setBounds(125, 10, 80, 30);
		pnlMenu.add(btnAdd);

		btnDelete = new KButton("삭제");
		btnDelete.setBounds(270, 10, 80, 30);
		pnlMenu.add(btnDelete);

		btnClose = new KButton("닫기");
		btnClose.setBounds(360, 10, 80, 30);
		pnlMenu.add(btnClose);
	}

	private void init() {
		setTitle("[ 회원 관리 ]");
		setSize(450, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
