package com.acainfo.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.acainfo.component.KButton;
import com.acainfo.component.KLabel;
import com.acainfo.controller.Controller;
import com.acainfo.dto.LoginDto;

public class LoginView extends JDialog {
	Controller controller;
	KLabel lblId;
	KLabel lblPw;
	JTextField txtId;
	JPasswordField txtPw;
	KButton btnLogin;
	KButton btnClose;

	public LoginView(Controller controller) {
		this.controller = controller;
		lblInit();

		txtInit();

		btnInit();

		init();

		btnLogin.setFocusable(true);
	}

	private void btnInit() {
		btnLogin = new KButton("로그인");
		btnLogin.setBounds(20, 110, 150, 50);
		add(btnLogin);

		btnClose = new KButton("종료");
		btnClose.setBounds(180, 110, 150, 50);
		add(btnClose);

		btnAddAction();
	}

	private void btnAddAction() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object target = e.getSource();
				if (target == btnLogin) {
					String id = txtId.getText();
					String pw = String.valueOf(txtPw.getPassword());
					controller.login(new LoginDto(id, pw));
				} else if (target == btnClose) {
					dispose();
				}
			}
		};

		btnLogin.addActionListener(listener);
		btnClose.addActionListener(listener);
	}

	private void txtInit() {
		txtId = new JTextField();
		txtId.setBounds(150, 25, 120, 25);
		add(txtId);

		txtPw = new JPasswordField();
		txtPw.setBounds(150, 65, 120, 25);
		add(txtPw);
	}

	private void lblInit() {
		lblId = new KLabel("아이디 : ");
		lblId.setBounds(85, 10, 70, 50);
		add(lblId);

		lblPw = new KLabel("비밀번호: ");
		lblPw.setBounds(75, 50, 70, 50);
		add(lblPw);
	}

	private void init() {
		setTitle("[ 로그인 ]");
		setSize(350, 180);
		setLayout(null);
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}
}
