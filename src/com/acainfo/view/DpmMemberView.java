package com.acainfo.view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.acainfo.component.KPanel;
import com.acainfo.component.KTable;
import com.acainfo.component.ValueItem;
import com.acainfo.controller.Controller;
import com.acainfo.dto.DpmDto;
import com.acainfo.dto.MemberDto;

public class DpmMemberView extends KPanel {
	private Controller controller;

	private JComboBox<ValueItem> cboDpm;
	private KTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;

	public DpmMemberView(Controller controller) {
		this.controller = controller;

		cboInit();

		tableInit();
	}

	public void selectMemMemDpm() {
		model.setNumRows(0);

		ArrayList<MemberDto> list = null;

		ValueItem vi = (ValueItem) cboDpm.getSelectedItem();

		if(vi == null) {
			return;
		}
		
		list = controller.selectMemDpm(vi.getValue());
		if (list == null) {
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

	public void selectDpmList() {
		cboDpm.removeAllItems();

		ArrayList<DpmDto> dpmDto = controller.selectDpmList(MainView.memberDto.getNum());
		for (DpmDto dto : dpmDto) {
			int deNum = dto.getDenum();
			String name = dto.getName();
			cboDpm.addItem(new ValueItem(deNum, name));
		}
	}

	private void tableInit() {
		String col[] = { "회원 번호", "아이디", "이름", "이메일", "전화번호", "주소", "학년", "재학 여부", "구분", "등록 날짜" };
		model = new DefaultTableModel(col, 0);
		table = new KTable();
		table.setModel(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(10, 40, 580, 480);
		add(scroll);
	}

	private void cboInit() {
		cboDpm = new JComboBox<ValueItem>();
		cboDpm.setBounds(10, 10, 140, 20);
		add(cboDpm);

		cboListener();
	}

	private void cboListener() {
		cboDpm.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					selectMemMemDpm();
				}
			}
		});
	}
}
