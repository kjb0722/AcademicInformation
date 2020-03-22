package com.acainfo.view;

import com.acainfo.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    Controller controller;

    JPanel pnlNorth;
    JPanel pnlUserInfo;
    JPanel pnlMenu;

    JTabbedPane tab;
    public MainView(Controller controller){
        this.controller = controller;

        panelInit();

        tabInit();

        init();
    }

    private void panelInit() {
        pnlNorth = new JPanel();
        pnlNorth.setLayout(new GridLayout(1,2));
        add(pnlNorth, BorderLayout.NORTH);

        pnlUserInfo = new JPanel();
        pnlUserInfo.setBackground(Color.PINK);
        pnlNorth.add(pnlUserInfo, BorderLayout.NORTH);

        pnlMenu = new JPanel();
        pnlMenu.setBackground(Color.ORANGE);
        pnlNorth.add(pnlMenu, BorderLayout.NORTH);
    }

    private void tabInit(){
        tab = new JTabbedPane();
        tab.addTab("Tab1",new JLabel("1212"));
        tab.setBounds(0,0,100,100);
        add(tab);
    }
    private void init(){
        setSize(500,500);
//        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void loginLoad() {
        new LoginView(controller);
    }
}
