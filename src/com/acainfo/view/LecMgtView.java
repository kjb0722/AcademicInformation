package com.acainfo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.acainfo.component.KButton;
import com.acainfo.component.KPanel;
import com.acainfo.component.KTable;
import com.acainfo.controller.Controller;
import com.acainfo.dto.LectureDto;

public class LecMgtView extends KPanel {
	Controller controller;

	KTable table;
	DefaultTableModel model;
	JScrollPane scroll;

	JTextField txtLecName;

	KButton btnAdd;
	KButton btnRemove;

	MainView main;

	public LecMgtView(Controller controller) {
		this.controller = controller;

		txtInit();

		btnInit();

		tableInit();
	}

	public void selectLec() {
		model.setNumRows(0);

		ArrayList<LectureDto> arrayDto = new ArrayList<LectureDto>();
		arrayDto = controller.selectLec(MainView.memberDto.getNum());
		for (LectureDto dto : arrayDto) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(dto.getLeNum());
			vec.add(dto.getName());
			vec.add(dto.getNum());
			vec.add(dto.getLec_mem());
			model.addRow(vec);
		}
	}

	private void txtInit() {
		txtLecName = new JTextField();
		txtLecName.setBounds(10, 10, 100, 25);
		add(txtLecName);

		txtListener();
	}

	private void txtListener() {
		txtLecName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnAdd.doClick();
				}
			}
		});
	}

	private void btnInit() {
		btnAdd = new KButton("추가");
		btnAdd.setLocation(120, 7);
		add(btnAdd);

		btnRemove = new KButton("삭제");
		btnRemove.setLocation(490, 7);
		add(btnRemove);

		btnListenerInit();
	}

	private void btnListenerInit() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object target = e.getSource();
				if (target == btnAdd) {
					insertLec();
				} else if (target == btnRemove) {
					deleteLec();
				}
			}
		};
		btnAdd.addActionListener(listener);
		btnRemove.addActionListener(listener);
	}

	private void deleteLec() {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "삭제하실 강의를 선택하세요.");
			return;
		}

		int lenum = (int) model.getValueAt(table.getSelectedRow(), 0);
		if (controller.deleteLec(lenum)) {
			JOptionPane.showMessageDialog(this, "[ 강의 삭제 성공 ]");
			selectLec();
		}
	}

	private void insertLec() {
		if (txtLecName.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "강의명을 입력하세요.");
			return;
		}

		int num = MainView.memberDto.getNum();
		String name = txtLecName.getText();
		LectureDto dto = new LectureDto(-1, num, name, "N");
		if (controller.insertLec(dto)) {
			JOptionPane.showMessageDialog(this, "[ 강의 추가 성공 ]");
			txtLecName.setText("");
			txtLecName.requestFocus();
			selectLec();
		}
	}

	private void tableInit() {
		String col[] = { "강의 번호", "강의명", "교수 번호", "교수" };
		model = new DefaultTableModel(col, 0);
		table = new KTable();
		table.setModel(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(10, 40, 580, 480);
		add(scroll);
	}
}
