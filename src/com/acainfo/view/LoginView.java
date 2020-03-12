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
		// �� �ʱ�ȭ
		lblInit();

		// �ؽ�Ʈ �ʱ�ȭ
		txtInit();

		// ��ư �ʱ�ȭ
		btnInit();

		// JFrame �ʱ�ȭ
		init();

		btnLogin.setFocusable(true);
	}

	private void btnInit() {
		btnLogin = new JButton("�α���");
		btnLogin.setBounds(50, 100, 120, 50);
		add(btnLogin);

		btnClose = new JButton("������");
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
		lblId = new JLabel("���̵� : ");
		lblId.setBounds(75, 0, 70, 50);
		add(lblId);

		lblPw = new JLabel("��й�ȣ : ");
		lblPw.setBounds(60, 40, 70, 50);
		add(lblPw);
	}

	private void init() {
		setTitle("�α���");
		setSize(350, 200);
		setLayout(null);
		setResizable(false);
//		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
