package com.acainfo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.acainfo.component.KButton;
import com.acainfo.component.KDialog;
import com.acainfo.component.KPanel;
import com.acainfo.component.KTable;
import com.acainfo.controller.Controller;
import com.acainfo.dto.MemberDto;

public class MemberMgtView extends KDialog {
	KPanel pnlMenu;
	KPanel pnlTable;

	KButton btnProfessor;
	KButton btnStudent;
	KButton btnAdd;
	KButton btnRemove;
	KButton btnClose;

	KTable table;
	DefaultTableModel model;
	JScrollPane scroll;

	public MemberMgtView(Controller controller) {
		super(controller);

		menuInit();

		tableInit();

		listenerInit();

		selectMember(0);

		init();
	}

	private void selectMember(int auth) {
		model.setNumRows(0);

		ArrayList<MemberDto> list = null;
		if (auth == 0) {
			list = controller.selectMemListAll();
		} else {
			list = controller.selectMemList(auth);
		}

		if (list == null) {
			JOptionPane.showMessageDialog(this, "[ memSrh() 오류 ]");
			return;
		}

		for (MemberDto dto : list) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(dto.getNum());
			vec.add(dto.getId());
			vec.add(dto.getName());
			vec.add(dto.getEmail());
			vec.add(dto.getPhone());
			vec.add(dto.getAddr());
			vec.add(dto.getHagnyeno());
			vec.add(dto.getDel_yn());
			vec.add(dto.getAuth());
			vec.add(dto.getMedate());
			model.addRow(vec);
		}
	}

	private void listenerInit() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object target = e.getSource();
				if (target == btnProfessor) {
					selectMember(50);
				} else if (target == btnStudent) {
					selectMember(10);
				} else if (target == btnAdd) {
					new MemberAddView(controller);
					selectMember(0);
				} else if (target == btnRemove) {
					deleteMem();
				} else if (target == btnClose) {
					dispose();
				}
			}
		};

		btnProfessor.addActionListener(listener);
		btnStudent.addActionListener(listener);
		btnAdd.addActionListener(listener);
		btnRemove.addActionListener(listener);
		btnClose.addActionListener(listener);
	}

	private void deleteMem() {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "삭제하실 회원을 선택하세요.");
			return;
		}

		int num = (int) model.getValueAt(table.getSelectedRow(), 0);
		if (controller.deleteMem(num)) {
			JOptionPane.showMessageDialog(this, "[ 회원 삭제 성공 ]");
			selectMember(0);
		}
	}

	private void tableInit() {
		table = new KTable();
		String col[] = { "회원 번호", "아이디", "이름", "이메일", "전화번호", "주소", "학년", "재학 여부", "구분", "등록 날짜" };
		model = new DefaultTableModel(col, 0);
		table.setModel(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 50, 900, 500);
		add(scroll);
	}

	private void menuInit() {
		pnlMenu = new KPanel();
		pnlMenu.setBounds(0, 0, 900, 50);
		add(pnlMenu);

		btnProfessor = new KButton("교수 검색");
		btnProfessor.setLocation(5, 10);
		pnlMenu.add(btnProfessor);

		btnStudent = new KButton("학생 검색");
		btnStudent.setLocation(125, 10);
		pnlMenu.add(btnStudent);

		btnAdd = new KButton("추가");
		btnAdd.setLocation(550, 10);
		pnlMenu.add(btnAdd);

		btnRemove = new KButton("삭제");
		btnRemove.setLocation(670, 10);
		pnlMenu.add(btnRemove);

		btnClose = new KButton("닫기");
		btnClose.setLocation(790, 10);
		pnlMenu.add(btnClose);
	}

	private void init() {
		setTitle("[ 회원 관리 ]");
		setSize(900, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
