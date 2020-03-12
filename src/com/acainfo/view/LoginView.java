package com.acainfo.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame {

	JLabel lblId;
	JLabel lblPw;
	JTextField txtId;
	JPasswordField txtPw;
	JButton btnLogin;
	JButton btnClose;

	public LoginView() {
		// 라벨 초기화
		lblInit();

		// 텍스트 초기화
		txtInit();

		// 버튼 초기화
		btnInit();

		// JFrame 초기화
		init();

		btnLogin.setFocusable(true);
	}

	private void btnInit() {
		btnLogin = new JButton("로그인");
		btnLogin.setBounds(50, 100, 120, 50);
		add(btnLogin);

		btnClose = new JButton("나가기");
		btnClose.setBounds(200, 100, 120, 50);
		add(btnClose);
	}

	private void txtInit() {
		txtId = new JTextField();
		txtId.setBounds(150, 15, 120, 25);
		add(txtId);

		txtPw = new JPasswordField();
		txtPw.setBounds(150, 55, 120, 25);
		add(txtPw);
	}

	private void lblInit() {
		lblId = new JLabel("아이디 : ");
		lblId.setBounds(75, 0, 70, 50);
		add(lblId);

		lblPw = new JLabel("비밀번호 : ");
		lblPw.setBounds(60, 40, 70, 50);
		add(lblPw);
	}

	private void init() {
		setTitle("로그인");
		setSize(350, 200);
		setLayout(null);
		setResizable(false);
//		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
