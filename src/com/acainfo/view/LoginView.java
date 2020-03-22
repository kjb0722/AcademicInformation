package com.acainfo.view;

import com.acainfo.controller.Controller;
import com.acainfo.dto.LoginDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JDialog {
	Controller controller;
    JLabel lblId;
    JLabel lblPw;
    JTextField txtId;
    JPasswordField txtPw;
    JButton btnLogin;
    JButton btnClose;

    public LoginView(Controller controller) {
    	this.controller = controller;
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
        btnLogin.setBounds(20, 110, 150, 50);
        add(btnLogin);

        btnClose = new JButton("나가기");
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
                    controller.login(new LoginDto(id,pw));
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
        lblId = new JLabel("아이디 : ");
        lblId.setBounds(85, 10, 70, 50);
        add(lblId);

        lblPw = new JLabel("비밀번호 : ");
        lblPw.setBounds(75, 50, 70, 50);
        add(lblPw);
    }

    private void init() {
        setTitle("로그인");
        setSize(350, 180);
        setLayout(null);
        setResizable(false);
		setUndecorated(true);
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }
}
