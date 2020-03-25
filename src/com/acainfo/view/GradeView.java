package com.acainfo.view;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.acainfo.component.KPanel;
import com.acainfo.component.KTable;
import com.acainfo.controller.Controller;
import com.acainfo.dto.GradeDto;

public class GradeView extends KPanel {
	Controller controller;

	MainView main;
	KTable table;
	DefaultTableModel model;
	JScrollPane scroll;

	public GradeView(Controller controller) {
		this.controller = controller;

		tableInit();

		selectGrade();
	}

	public void selectGrade() {
		ArrayList<GradeDto> arrayDto = new ArrayList<GradeDto>();
		int num = MainView.memberDto.getNum();
		arrayDto = controller.selectMemGrade(num);
	}

	private void tableInit() {
		String col[] = { "강의 번호", "회원 번호", "점수", "등급", "점수 등록 날짜" };
		model = new DefaultTableModel(col, 0);
		table = new KTable();
		table.setModel(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(10, 10, 575, 510);
		add(scroll);
	}
}
