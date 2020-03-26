package com.acainfo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.acainfo.component.KButton;
import com.acainfo.component.KPanel;
import com.acainfo.component.KTable;
import com.acainfo.controller.Controller;
import com.acainfo.dto.LecMemDto;
import com.acainfo.dto.LectureDto;

public class LecAppView extends KPanel {
	Controller controller;

	KButton btnAdd;
	KButton btnRemove;

	KTable table;
	DefaultTableModel model;
	JScrollPane scroll;

	public LecAppView(Controller controller) {
		this.controller = controller;

		btnInit();

		tableInit();
	}

	public void selectLecAll() {
		model.setNumRows(0);

		ArrayList<LectureDto> list = controller.selectLecAll(MainView.memberDto.getNum());
		for (LectureDto dto : list) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(dto.getLeNum());
			vec.add(dto.getLec_mem());
			vec.add(dto.getName());
			vec.add(dto.getLec_yn().equals("Y") ? "수강중" : "");
			model.addRow(vec);
		}
	}

	private void tableInit() {
		String col[] = { "강의 번호", "강의 교수", "강의명", "수강 여부" };
		model = new DefaultTableModel(col, 0);
		table = new KTable();
		table.setModel(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(10, 50, 580, 470);
		add(scroll);
	}

	private void btnInit() {
		btnAdd = new KButton("수강 신청");
		btnAdd.setLocation(10, 10);
		add(btnAdd);

		btnRemove = new KButton("수강 취소");
		btnRemove.setLocation(130, 10);
		add(btnRemove);

		btnListener();
	}

	private void btnListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object target = e.getSource();
				if (target == btnAdd) {
					lecAdd();
				} else if (target == btnRemove) {
					lecRemove();
				}
			}
		};
		btnAdd.addActionListener(listener);
		btnRemove.addActionListener(listener);
	}

	private void lecRemove() {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "취소할 강의를 선택하세요.");
			return;
		}

		if (model.getValueAt(table.getSelectedRow(), 3).equals("")) {
			JOptionPane.showMessageDialog(this, "추가한 강의가 아닙니다.");
			return;
		}

		int leNum = (int) model.getValueAt(table.getSelectedRow(), 0);
		int num = MainView.memberDto.getNum();
		if (controller.deleteLecMem(new LecMemDto(-1, leNum, num, null, "N"))) {
			JOptionPane.showMessageDialog(this, "[ 수강 취소 완료 ]");
			selectLecAll();
		}
	}

	private void lecAdd() {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "추가할 강의를 선택하세요.");
			return;
		}

		if (model.getValueAt(table.getSelectedRow(), 3).equals("수강중")) {
			JOptionPane.showMessageDialog(this, "이미 수강한 강의입니다.");
			return;
		}

		int leNum = (int) model.getValueAt(table.getSelectedRow(), 0);
		int num = MainView.memberDto.getNum();
		if (controller.insertLecMem(new LecMemDto(-1, leNum, num, null, "N"))) {
			JOptionPane.showMessageDialog(this, "[ 수강 신청 완료 ]");
			selectLecAll();
		}
	}
}
