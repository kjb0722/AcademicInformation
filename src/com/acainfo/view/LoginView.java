package com.acainfo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.acainfo.component.KButton;
import com.acainfo.component.KDialog;
import com.acainfo.component.KLabel;
import com.acainfo.controller.Controller;
import com.acainfo.dto.MemberDto;
import com.acainfo.dto.PassDto;

public class LoginView extends KDialog {
	MemberDto memberDto;

	KLabel lblId;
	KLabel lblPw;
	JTextField txtId;
	JPasswordField txtPw;
	KButton btnLogin;
	KButton btnClose;

	public LoginView(Controller controller) {
		super(controller);

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
					login();
				} else if (target == btnClose) {
					int result = JOptionPane.showConfirmDialog(LoginView.this, "프로그램을 종료하시겠습니까?", "[종료]",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
				} else if (target == txtPw) {
					btnLogin.doClick();
				}
			}
		};

		btnLogin.addActionListener(listener);
		btnClose.addActionListener(listener);
		txtPw.addActionListener(listener);
	}

	private void login() {
		if (txtId.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "아이디를 입력하세요.");
			return;
		}
		if (String.valueOf(txtPw.getPassword()).length() == 0) {
			JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요.");
			return;
		}
		String id = txtId.getText();
		String pw = String.valueOf(txtPw.getPassword());
		PassDto dto = new PassDto(id, pw);
		if (controller.selectLogin(dto)) {
			memberDto = controller.selectMemInfo(dto);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "아이디 혹은 비밀번호가 틀렸습니다.");
			txtPw.setText("");
			txtId.setText("");
			txtId.requestFocus();
			return;
		}
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
		setLayout(null);
		setSize(350, 180);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public MemberDto getMemberDto() {
		if (memberDto != null) {
			return memberDto;
		}
		return null;
	}
}
