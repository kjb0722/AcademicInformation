package com.acainfo.view;

import com.acainfo.controller.Controller;

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
    JButton btnJoin;

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
        btnLogin.setBounds(25, 100, 100, 50);
        add(btnLogin);

        btnJoin = new JButton("회원가입");
        btnJoin.setBounds(125, 100, 100, 50);
        add(btnJoin);

        btnClose = new JButton("나가기");
        btnClose.setBounds(225, 100, 100, 50);
        add(btnClose);

        btnAddAction();
    }

    private void btnAddAction() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object target = e.getSource();
                if (target == btnLogin) {

                } else if (target == btnJoin) {
					new JoinView(controller);
                } else if (target == btnClose) {
                    dispose();
                }
            }
        };

        btnLogin.addActionListener(listener);
        btnJoin.addActionListener(listener);
        btnClose.addActionListener(listener);
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
        lblPw.setBounds(65, 40, 70, 50);
        add(lblPw);
    }

    private void init() {
        setTitle("로그인");
        setSize(350, 200);
        setLayout(null);
        setResizable(false);
//		setUndecorated(true);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setModal(true);
        setVisible(true);
    }
}
