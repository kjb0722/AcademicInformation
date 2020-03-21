package com.acainfo.view;

import com.acainfo.controller.Controller;

import javax.swing.*;

public class MainView extends JFrame {
    Controller controller;
    public MainView(Controller controller){
        this.controller = controller;

        init();
    }
    private void init(){
        setSize(500,500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void loginLoad() {
        new LoginView(controller);
    }
}
