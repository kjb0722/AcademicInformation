package com.acainfo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.acainfo.component.KButton;
import com.acainfo.component.KLabel;
import com.acainfo.component.KPanel;
import com.acainfo.controller.Controller;
import com.acainfo.dto.MemberDto;

public class MemberInfo extends KPanel {
	Controller controller;

	MemberDto dto;

	private KLabel lblId;
	private KLabel lblNum;
	private KLabel lblHagnyeno;
	private KLabel lblName;
	private KLabel lblEmail;
	private KLabel lblPhone;
	private KLabel lblAddr;
	private KLabel lblAuth;

	private JTextField txtId;
	private JTextField txtNum;
	private JTextField txtHagnyeno;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtAddr;
	private JTextField txtAuth;

	private KButton btnSave;
	private KButton btnInit;

	public MemberInfo(Controller controller) {
		this.controller = controller;

		lblInit();

		txtInit();

		btnInit();
	}

	private void btnInit() {
		btnSave = new KButton("저장");
		btnSave.setLocation(150, 370);
		add(btnSave);

		btnInit = new KButton("초기화");
		btnInit.setLocation(280, 370);
		add(btnInit);

		btnListener();
	}

	private void btnListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object target = e.getSource();
				if (target == btnSave) {
					saveMemInfo();
				} else if (target == btnInit) {
					setMemInfo(dto);
				}
			}
		};
		btnSave.addActionListener(listener);
		btnInit.addActionListener(listener);
	}

	private void saveMemInfo() {
		String id = txtId.getText();
		String name = txtName.getText();
		String email = txtEmail.getText();
		String phone = txtPhone.getText();
		String addr = txtAddr.getText();
		int hagnyeno = Integer.parseInt(txtHagnyeno.getText());
		int auth = dto.getAuth();
		MemberDto dto = new MemberDto(this.dto.getNum(), id, name, email, phone, addr, hagnyeno, "N", auth, null);
		if (controller.updateMemInfo(dto)) {
			JOptionPane.showMessageDialog(this, "[ 수정 성공 ]");
			setMemInfo(dto);
		} else {
			JOptionPane.showMessageDialog(this, "[ 수정 실패 ]");
		}
	}

	private void lblInit() {
		lblId = new KLabel("아이디 : ");
		lblId.setBounds(50, 30, 100, 30);
		add(lblId);

		lblNum = new KLabel("학번 : ");
		lblNum.setBounds(50, 70, 100, 30);
		add(lblNum);

		lblHagnyeno = new KLabel("학년 : ");
		lblHagnyeno.setBounds(50, 110, 100, 30);
		add(lblHagnyeno);

		lblName = new KLabel("이름 : ");
		lblName.setBounds(50, 150, 100, 30);
		add(lblName);

		lblEmail = new KLabel("이메일 : ");
		lblEmail.setBounds(50, 190, 100, 30);
		add(lblEmail);

		lblPhone = new KLabel("휴대폰 번호 : ");
		lblPhone.setBounds(50, 230, 100, 30);
		add(lblPhone);

		lblAddr = new KLabel("주소 : ");
		lblAddr.setBounds(50, 270, 100, 30);
		add(lblAddr);

		lblAuth = new KLabel("구분 : ");
		lblAuth.setBounds(50, 310, 100, 30);
		add(lblAuth);
	}

	public void setMemInfo(MemberDto memberDto) {
		this.dto = memberDto;

		txtId.setText(dto.getId());
		txtNum.setText(Integer.toString(dto.getNum()));
		txtHagnyeno.setText(Integer.toString(dto.getHagnyeno()));
		txtName.setText(dto.getName());
		txtEmail.setText(dto.getEmail());
		txtPhone.setText(dto.getPhone());
		txtAddr.setText(dto.getAddr());

		String auth = "";
		if (dto.getAuth() == 10) {
			auth = "학생";
		} else if (dto.getAuth() == 50) {
			auth = "교수";
		} else if (dto.getAuth() == 99) {
			auth = "관리자";
		}
		txtAuth.setText(auth);
	}

	private void txtInit() {
		txtId = new JTextField();
		txtId.setBounds(120, 35, 100, 25);
		add(txtId);

		txtNum = new JTextField();
		txtNum.setBounds(120, 75, 100, 25);
		txtNum.setEditable(false);
		add(txtNum);

		txtHagnyeno = new JTextField();
		txtHagnyeno.setBounds(120, 115, 100, 25);
		txtHagnyeno.setEditable(false);
		add(txtHagnyeno);

		txtName = new JTextField();
		txtName.setBounds(120, 155, 100, 25);
		add(txtName);

		txtEmail = new JTextField();
		txtEmail.setBounds(120, 195, 170, 25);
		add(txtEmail);

		txtPhone = new JTextField();
		txtPhone.setBounds(150, 235, 100, 25);
		add(txtPhone);

		txtAddr = new JTextField();
		txtAddr.setBounds(120, 275, 300, 25);
		add(txtAddr);

		txtAuth = new JTextField();
		txtAuth.setBounds(120, 315, 100, 25);
		txtAuth.setEditable(false);
		add(txtAuth);
	}

}
