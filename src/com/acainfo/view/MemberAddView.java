package com.acainfo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.acainfo.component.KButton;
import com.acainfo.component.KDialog;
import com.acainfo.component.KLabel;
import com.acainfo.component.KNumField;
import com.acainfo.component.KPanel;
import com.acainfo.controller.Controller;
import com.acainfo.dto.MemberDto;

public class MemberAddView extends KDialog {
	private KPanel pnlMemberInfo;

	private KButton btnAdd;
	private KButton btnClose;

	private KLabel lblId;
	private KLabel lblPass;
	private KLabel lblHagnyeno;
	private KLabel lblName;
	private KLabel lblEmail;
	private KLabel lblPhone;
	private KLabel lblAddr;
	private KLabel lblAuth;

	private JTextField txtId;
	private JPasswordField txtPass;
	private KNumField txtHagnyeno;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtAddr;
	private JTextField txtAuth;

	public MemberAddView(Controller controller) {
		super(controller);

		memberInfoInit();

		listenerInit();

		init();
	}

	private void listenerInit() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object target = e.getSource();
				if (target == btnAdd) {
					memberAdd();
				} else if (target == btnClose) {
					dispose();
				}
			}
		};

		btnAdd.addActionListener(listener);
		btnClose.addActionListener(listener);
	}

	private void memberAdd() {
		if (txtId.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "아이디를 입력하세요.");
			return;
		}
		if (String.valueOf(txtPass.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요.");
			return;
		}
		if (txtName.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "이름을 입력하세요.");
			return;
		}
		if (txtEmail.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "이메일 입력하세요.");
			return;
		}
		if (txtPhone.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "전화번호를 입력하세요.");
			return;
		}
		if (txtAddr.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "주소를 입력하세요.");
			return;
		}
		if (txtHagnyeno.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "학년을 입력하세요.");
			return;
		}
		if (txtAuth.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "구분을 입력하세요.");
			return;
		} else if (!txtAuth.getText().equals("10") && !txtAuth.getText().equals("50")
				&& !txtAuth.getText().equals("99")) {
			JOptionPane.showMessageDialog(this, "구분은 10(학생), 50(교수), 99(관리자)만 입력할 수 있습니다.");
			return;
		}

		String id = txtId.getText();
		String pass = String.valueOf(txtPass.getPassword());
		String name = txtName.getText();
		String email = txtEmail.getText();
		String phone = txtPhone.getText();
		String addr = txtAddr.getText();
		int hagnyeno = Integer.parseInt(txtHagnyeno.getText());
		int auth = Integer.parseInt(txtAuth.getText());
		MemberDto dto = new MemberDto(-1, id, name, email, phone, addr, hagnyeno, "Y", auth, null);
		if (controller.insertMember(dto, pass)) {
			JOptionPane.showMessageDialog(this, "[ 회원 추가 성공 ]");
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "[ 회원 추가 실패 ]");
			return;
		}
	}

	private void init() {
		setTitle("[ 회원 추가 ]");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void memberInfoInit() {
		pnlMemberInfo = new KPanel();
		pnlMemberInfo.setBounds(0, 0, this.getWidth(), this.getHeight());
		add(pnlMemberInfo);

		lblId = new KLabel("아이디 : ");
		lblId.setBounds(50, 30, 100, 30);
		pnlMemberInfo.add(lblId);

		txtId = new JTextField();
		txtId.setBounds(120, 35, 100, 25);
		pnlMemberInfo.add(txtId);

		lblPass = new KLabel("비밀번호 : ");
		lblPass.setBounds(50, 70, 100, 30);
		pnlMemberInfo.add(lblPass);

		txtPass = new JPasswordField();
		txtPass.setBounds(120, 75, 100, 25);
		pnlMemberInfo.add(txtPass);

		lblHagnyeno = new KLabel("학년 : ");
		lblHagnyeno.setBounds(50, 110, 100, 30);
		pnlMemberInfo.add(lblHagnyeno);

		txtHagnyeno = new KNumField();
		txtHagnyeno.setBounds(120, 115, 100, 25);
		pnlMemberInfo.add(txtHagnyeno);

		lblName = new KLabel("이름 : ");
		lblName.setBounds(50, 150, 100, 30);
		pnlMemberInfo.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(120, 155, 100, 25);
		pnlMemberInfo.add(txtName);

		lblEmail = new KLabel("이메일 : ");
		lblEmail.setBounds(50, 190, 100, 30);
		pnlMemberInfo.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(120, 195, 100, 25);
		pnlMemberInfo.add(txtEmail);

		lblPhone = new KLabel("휴대폰 번호 : ");
		lblPhone.setBounds(50, 230, 100, 30);
		pnlMemberInfo.add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setBounds(150, 235, 100, 25);
		pnlMemberInfo.add(txtPhone);

		lblAddr = new KLabel("주소 : ");
		lblAddr.setBounds(50, 270, 100, 30);
		pnlMemberInfo.add(lblAddr);

		txtAddr = new JTextField();
		txtAddr.setBounds(120, 275, 100, 25);
		txtAddr.setSize(250, 25);
		pnlMemberInfo.add(txtAddr);

		lblAuth = new KLabel("구분 : ");
		lblAuth.setBounds(50, 310, 100, 30);
		pnlMemberInfo.add(lblAuth);

		txtAuth = new JTextField();
		txtAuth.setBounds(120, 315, 100, 25);
		pnlMemberInfo.add(txtAuth);

		btnAdd = new KButton("저장");
		btnAdd.setLocation(95, 360);
		pnlMemberInfo.add(btnAdd);

		btnClose = new KButton("닫기");
		btnClose.setLocation(205, 360);
		pnlMemberInfo.add(btnClose);
	}
}
