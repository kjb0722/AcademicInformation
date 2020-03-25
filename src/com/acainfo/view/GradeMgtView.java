package com.acainfo.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.acainfo.component.KPanel;
import com.acainfo.component.KTable;
import com.acainfo.controller.Controller;
import com.acainfo.dto.GradeDto;

public class GradeMgtView extends KPanel {
	Controller controller;

	MainView main;
	KTable table;
	DefaultTableModel model;
	JScrollPane scroll;

	public GradeMgtView(Controller controller) {
		this.controller = controller;

		tableInit();

		//selectGradeMgt();
	}

	public void selectGradeMgt() {
		model.setRowCount(0);
		
		ArrayList<GradeDto> arrayDto = new ArrayList<GradeDto>();
		if (MainView.memberDto.getNum() == -1) {
			return;
		}

		int num = MainView.memberDto.getNum();
		arrayDto = controller.selectMemGradeMgt(num);

		for (GradeDto dto : arrayDto) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(dto.getLeNum());
			vec.add(dto.getNum());
			vec.add(dto.getScore());
			vec.add(dto.getRank());
			vec.add(dto.getGrdate());
			model.addRow(vec);
		}
	}

	private void tableInit() {
		String col[] = { "강의 번호", "회원 번호", "점수", "등급", "점수 등록 날짜" };
		model = new DefaultTableModel(col, 0);
		table = new KTable();
		table.setModel(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(10, 10, 575, 510);
		add(scroll);

		tableListener();
	}

	private void tableListener() {
		MouseListener listener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int leNum = (int) model.getValueAt(table.getSelectedRow(), 0);
					int num = (int) model.getValueAt(table.getSelectedRow(), 1);
					int score = (int) model.getValueAt(table.getSelectedRow(), 2);
					String rank = (String) model.getValueAt(table.getSelectedRow(), 3);
					GradeDto gradeDto = new GradeDto(leNum, num, score, rank, "N", null);
					GradeInputView inputView = new GradeInputView(controller, gradeDto);
					selectGradeMgt();
				}
			}
		};
		table.addMouseListener(listener);
	}
}
