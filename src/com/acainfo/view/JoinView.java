package com.acainfo.view;

import com.acainfo.controller.Controller;

import javax.swing.*;

public class JoinView extends JDialog {
    Controller controller;
    public JoinView(Controller controller) {
        this.controller = controller;

        init();
    }
    private void init(){
        setSize(700,700);
        setTitle("회원가입");
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
