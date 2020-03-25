package com.acainfo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.acainfo.component.KButton;
import com.acainfo.component.KDialog;
import com.acainfo.component.KLabel;
import com.acainfo.component.ValueItem;
import com.acainfo.controller.Controller;
import com.acainfo.dto.DpmDto;
import com.acainfo.dto.DpmMemDto;
import com.acainfo.dto.MemberDto;

public class DpmMemMgtView extends KDialog {

	private JList memList;
	private JList dpmList;

	private KButton btnSave;
	private KButton btnClose;
	private KButton btnAdd;
	private KButton btnRemove;

	private KLabel lblNoneMem;

	private JComboBox<ValueItem> cboDpm;

	private DefaultListModel<ValueItem> memModel;
	private DefaultListModel<ValueItem> dpmModel;

	public DpmMemMgtView(Controller controller) {
		super(controller);

		lblInit();

		btnInit();

		listInit();

		cboInit();

		resetAll();

		init();
	}

	private void lblInit() {
		lblNoneMem = new KLabel("학과 미정 학생 목록");
		lblNoneMem.setBounds(5, 5, 140, 25);
		add(lblNoneMem);
	}

	private void selectDpmMemList() {
		dpmModel.setSize(0);

		if (cboDpm.getSelectedIndex() == -1) {
			return;
		}
		ValueItem vi = (ValueItem) cboDpm.getSelectedItem();
		ArrayList<MemberDto> MemberDto = controller.selectDpmMemList(vi.getValue());
		for (MemberDto dto : MemberDto) {
			dpmModel.addElement(new ValueItem(dto.getNum(), dto.getName()));
		}
	}

	private void selectNoneDpmMember() {
		memModel.setSize(0);

		ArrayList<MemberDto> MemberDto = controller.selectNoneDpmMember();
		for (MemberDto dto : MemberDto) {
			memModel.addElement(new ValueItem(dto.getNum(), dto.getName()));
		}
	}

	private void selectDpmList() {
		cboDpm.removeAllItems();

		ArrayList<DpmDto> dpmDto = controller.selectDpmList();
		for (DpmDto dto : dpmDto) {
			int deNum = dto.getDenum();
			String name = dto.getName();
			cboDpm.addItem(new ValueItem(deNum, name));
		}
	}

	private void cboInit() {
		cboDpm = new JComboBox<ValueItem>();
		cboDpm.setBounds(290, 50, 100, 20);
		add(cboDpm);

		cboListener();
	}

	private void cboListener() {
		cboDpm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectDpmMemList();
				selectNoneDpmMember();
			}
		});
	}

	private void listInit() {
		memModel = new DefaultListModel<ValueItem>();
		memList = new JList(memModel);
		memList.setBounds(5, 30, 250, 360);
		add(memList);

		dpmModel = new DefaultListModel<ValueItem>();
		dpmList = new JList(dpmModel);
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
					listSave();
				} else if (target == btnClose) {
					dispose();
				} else if (target == btnAdd) {
					moveNoneToDpm();
				} else if (target == btnRemove) {
					moveDpmToNone();
				}
			}
		};
		btnSave.addActionListener(listener);
		btnClose.addActionListener(listener);
		btnAdd.addActionListener(listener);
		btnRemove.addActionListener(listener);
	}

	private void listSave() {
		ValueItem cboVi = (ValueItem) cboDpm.getSelectedItem();
		int deNum = cboVi.getValue();

		ArrayList<DpmMemDto> arrayDto = new ArrayList<DpmMemDto>();
		for (int i = 0; i < dpmModel.size(); i++) {
			ValueItem vi = dpmModel.get(i);
			arrayDto.add(new DpmMemDto(deNum, vi.getValue()));
		}
		if (controller.delInsDpmMem(arrayDto, deNum)) {
			JOptionPane.showMessageDialog(this, "[ 학과 학생 저장 성공 ]");
			resetAll();
		}
	}

	private void resetAll() {
		selectDpmList();
		selectNoneDpmMember();
	}

	private void moveDpmToNone() {
		if (dpmList.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "학생을 지정하세요.");
			return;
		}

		ValueItem vi = dpmModel.get(dpmList.getSelectedIndex());
		dpmModel.remove(dpmList.getSelectedIndex());
		memModel.addElement(vi);
		dpmList.setSelectedIndex(0);
	}

	private void moveNoneToDpm() {
		if (memList.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "학생을 지정하세요.");
			return;
		}

		ValueItem vi = memModel.get(memList.getSelectedIndex());
		memModel.remove(memList.getSelectedIndex());
		dpmModel.addElement(vi);
		memList.setSelectedIndex(0);
	}

	private void init() {
		setTitle("[ 학과 회원 관리 ]");
		setSize(650, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
